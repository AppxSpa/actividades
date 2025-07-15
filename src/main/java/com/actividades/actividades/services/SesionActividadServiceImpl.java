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

        boolean haySesionActiva = haySesionActiva(idActividad);
        if (haySesionActiva) {
            throw new SesionActividadException("Ya existe una sesión activa para esta actividad");
        }

        if (!estadSesionActividad(idActividad)) {
            throw new SesionActividadException("No se puede volver a iniciar la misma Actividad");

        }

        SesionActividad sesion = new SesionActividad();
        sesion.setActividad(actividad);
        sesion.setInicioReal(getDateAndHourCurrent());
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

    private boolean haySesionActiva(Long idActividad){

       return sesionActividadRepository
                .findFirstByActividadIdAndFinRealIsNullOrderByInicioRealDesc(idActividad)
                .isPresent();
    }

    @Override
    public boolean estadSesionActividad(Long actividadId) {

        return sesionActividadRepository
                .findFirstByActividadIdAndFinRealIsNullOrderByInicioRealDesc(actividadId)
                .isPresent();

    }

    private LocalDateTime getDateAndHourCurrent() {

        return ZonedDateTime.now(ZoneId.of("America/Santiago")).toLocalDateTime();

    }

}
