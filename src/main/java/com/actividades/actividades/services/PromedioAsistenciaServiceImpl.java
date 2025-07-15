package com.actividades.actividades.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.actividades.actividades.dto.PersonaResponse;
import com.actividades.actividades.dto.PromedioAsistenciaActividad;
import com.actividades.actividades.dto.PromedioAsistenciaResponse;
import com.actividades.actividades.entities.Actividad;
import com.actividades.actividades.entities.Asistencia;
import com.actividades.actividades.entities.Persona;
import com.actividades.actividades.exceptions.AsistenciaException;
import com.actividades.actividades.repositories.ActividadRepository;
import com.actividades.actividades.repositories.AsistenciaRepository;
import com.actividades.actividades.repositories.PersonaRepository;
import com.actividades.actividades.repositories.SesionActividadRepository;
import com.actividades.actividades.services.interfaces.ApiPersonaService;
import com.actividades.actividades.services.interfaces.PromedioAsistenciaService;
import com.actividades.actividades.utils.RepositoryUtils;

@Service
public class PromedioAsistenciaServiceImpl implements PromedioAsistenciaService {

    private final AsistenciaRepository asistenciaRepository;
    private final SesionActividadRepository sesionActividadRepository;
    private final PersonaRepository personaRepository;
    private final ActividadRepository actividadRepository;
    private final ApiPersonaService apiPersonaService;

    public PromedioAsistenciaServiceImpl(AsistenciaRepository asistenciaRepository,
            SesionActividadRepository sesionActividadRepository, PersonaRepository personaRepository,
            ActividadRepository actividadRepository, ApiPersonaService apiPersonaService) {
        this.asistenciaRepository = asistenciaRepository;
        this.sesionActividadRepository = sesionActividadRepository;
        this.personaRepository = personaRepository;
        this.actividadRepository = actividadRepository;
        this.apiPersonaService = apiPersonaService;
    }

    @Override
    public PromedioAsistenciaResponse promedioByRutAndActividad(Integer rut, Long idActividad) {
        Persona persona = getPersonaBRut(rut);

        Actividad actividad = geActividadById(idActividad);

        PromedioAsistenciaResponse promedioAsistenciaPro = new PromedioAsistenciaResponse();

        List<Asistencia> asistencias = asistenciaRepository.findByActividad(actividad);

        if (asistencias.isEmpty()) {
            throw new AsistenciaException("No existe asistencia para esta actividad ");
        }

        promedioAsistenciaPro.setDiasAsitentica(getDiasAsistidos(persona, actividad));
        promedioAsistenciaPro.setDiasTotales(getDiasTotales(actividad));
        promedioAsistenciaPro
                .setDiasInasistencia(diasInasistencia(getDiasTotales(actividad), getDiasAsistidos(persona, actividad)));
        promedioAsistenciaPro.setPromedioAsistencia(calculatePromedio(persona, actividad));

        return promedioAsistenciaPro;

    }

    private Persona getPersonaBRut(Integer rut) {
        return RepositoryUtils.findOrThrow(personaRepository.findByRut(rut),
                String.format("No esiste el rut %d", rut));

    }

    private Actividad geActividadById(Long id) {
        return RepositoryUtils.findOrThrow(actividadRepository.findById(id),
                (String.format("Actividad %d no existe", id)));

    }

    private double calculatePromedio(Persona persona, Actividad actividad) {
        int diasAsistidos = getDiasAsistidos(persona, actividad);

        int diasTotales = getDiasTotales(actividad);

        double promedio = (diasAsistidos * 100);

        return diasAsistidos > 0 ? promedio / diasTotales : 0;
    }

    private int getDiasAsistidos(Persona persona, Actividad actividad) {
        return asistenciaRepository.findByPersonaAndActividad(persona, actividad).size();
    }

    private int getDiasTotales(Actividad actividad) {
        return sesionActividadRepository.findByActividadId(actividad.getId()).size();
    }

    private int diasInasistencia(int diasTotales, int diasAsistidos) {
        return diasTotales - diasAsistidos;
    }

    @Override
    public List<PromedioAsistenciaActividad> promedioByActividad(Long idActividad) {

        Actividad actividad = geActividadById(idActividad);

        List<Asistencia> asistencias = asistenciaRepository.findByActividad(actividad);

        return mapPromedioAsistenciaActividad(asistencias, actividad);

    }

    private PersonaResponse getPersonaResponse(Integer rut) {

        return apiPersonaService.getPersonaInfo(rut);

    }

    private List<PromedioAsistenciaActividad> mapPromedioAsistenciaActividad(List<Asistencia> asistencias,
            Actividad actividad) {
        return asistencias.stream().map(asistencia -> {
            PromedioAsistenciaActividad promedioAsistenciaActividad = new PromedioAsistenciaActividad();
            promedioAsistenciaActividad.setRut(asistencia.getRutPersona());

            PersonaResponse personaResponse = getPersonaResponse(asistencia.getRutPersona());

            promedioAsistenciaActividad.setVrut(personaResponse.getVrut());
            promedioAsistenciaActividad.setNombres(personaResponse.getNombres());
            promedioAsistenciaActividad.setPaterno(personaResponse.getPaterno());
            promedioAsistenciaActividad.setMaterno(personaResponse.getMaterno());

            PromedioAsistenciaResponse promedios = new PromedioAsistenciaResponse();

            promedios.setDiasAsitentica(getDiasAsistidos(asistencia.getPersona(), actividad));
            promedios.setDiasTotales(getDiasTotales(actividad));
            promedios.setDiasInasistencia(
                    diasInasistencia(getDiasTotales(actividad), getDiasAsistidos(asistencia.getPersona(), actividad)));
            promedios.setPromedioAsistencia(calculatePromedio(asistencia.getPersona(), actividad));

            promedioAsistenciaActividad.setPromedios(promedios);
            return promedioAsistenciaActividad;
        }).toList();
    }

}
