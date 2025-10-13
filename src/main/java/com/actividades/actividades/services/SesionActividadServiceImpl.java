package com.actividades.actividades.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

import com.actividades.actividades.entities.Actividad;
import com.actividades.actividades.entities.SesionActividad;
import com.actividades.actividades.exceptions.SesionActividadException;
import com.actividades.actividades.repositories.ActividadRepository;
import com.actividades.actividades.repositories.SesionActividadRepository;
import com.actividades.actividades.services.interfaces.SesionActividadService;
import com.actividades.actividades.utils.RepositoryUtils;

@Service
public class SesionActividadServiceImpl implements SesionActividadService {

    private final ActividadRepository actividadRepository;
    private final SesionActividadRepository sesionActividadRepository;

    public SesionActividadServiceImpl(ActividadRepository actividadRepository,
            SesionActividadRepository sesionActividadRepository) {
        this.actividadRepository = actividadRepository;
        this.sesionActividadRepository = sesionActividadRepository;
    }

    @Override
    public SesionActividad iniciarSesion(Long idActividad) {
        Actividad actividad = RepositoryUtils.findOrThrow(actividadRepository.findById(idActividad),
                String.format("Actividad %d no encontrada", idActividad));

        // 1. Validar que no hay sesión activa
        if (haySesionActiva(idActividad)) {
            throw new SesionActividadException("Ya existe una sesión activa para esta actividad");
        }

        // Obtener la fecha y hora actual
        LocalDateTime now = getDateAndHourCurrent();

        // 2. Validar que no puede iniciar la misma actividad en el mismo día
        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = now.toLocalDate().atTime(23, 59, 59, 999999999);

        if (sesionActividadRepository.existsByActividadIdAndInicioRealBetween(idActividad, startOfDay, endOfDay)) {
            throw new SesionActividadException("Ya se ha iniciado una sesión para esta actividad hoy.");
        }

        // 3. Validar que no puede iniciar antes de la fecha de la actividad
        if (actividad.getFechaInicio() != null && now.toLocalDate().isBefore(actividad.getFechaInicio())) {
            throw new SesionActividadException("No se puede iniciar la actividad antes de su fecha de inicio programada.");
        }

        SesionActividad sesion = new SesionActividad();
        sesion.setActividad(actividad);
        sesion.setInicioReal(now);
        sesion.setFinReal(null);

        return sesionActividadRepository.save(sesion);
    }
                               
    @Override
    public SesionActividad finalizarSesion(Long actividadId) {
        SesionActividad sesion = sesionActividadRepository
                .findFirstByActividadIdAndFinRealIsNullOrderByInicioRealDesc(actividadId)
                .orElseThrow(() -> new SesionActividadException("No hay sesión activa para esta actividad"));

        sesion.setFinReal(getDateAndHourCurrent());
        return sesionActividadRepository.save(sesion);
    }

    public boolean haySesionActiva(Long idActividad){

       return sesionActividadRepository
                .findFirstByActividadIdAndFinRealIsNullOrderByInicioRealDesc(idActividad)
                .isPresent();
    }

    

    private LocalDateTime getDateAndHourCurrent() {

        return ZonedDateTime.now(ZoneId.of("America/Santiago")).toLocalDateTime();

    }

}
