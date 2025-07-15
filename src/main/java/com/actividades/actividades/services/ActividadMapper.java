package com.actividades.actividades.services;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.actividades.actividades.dto.ActividadesList;
import com.actividades.actividades.dto.DireccionResponse;
import com.actividades.actividades.dto.HorarioDTO;
import com.actividades.actividades.dto.PersonaResponse;
import com.actividades.actividades.entities.Actividad;
import com.actividades.actividades.entities.ProfesorActividad;
import com.actividades.actividades.repositories.ProfesorActividadRepository;
import com.actividades.actividades.services.interfaces.ApiGetDireccionService;
import com.actividades.actividades.services.interfaces.ApiPersonaService;
import com.actividades.actividades.services.interfaces.SesionActividadService;

@Component
public class ActividadMapper {

    private final ApiPersonaService apiPersonaService;

    private final ApiGetDireccionService apiGetDireccionService;

    private final ProfesorActividadRepository profesorActividadRepository;

    private final SesionActividadService sesionActividadService;

    public ActividadMapper(ApiPersonaService apiPersonaService, ApiGetDireccionService apiGetDireccionService,
            ProfesorActividadRepository profesorActividadRepository, SesionActividadService sesionActividadService) {
        this.apiPersonaService = apiPersonaService;
        this.apiGetDireccionService = apiGetDireccionService;
        this.profesorActividadRepository = profesorActividadRepository;
        this.sesionActividadService = sesionActividadService;

    }

    public ActividadesList convertActividad(Actividad actividad) {

        ActividadesList actividadesList = new ActividadesList();
        actividadesList.setIdActividad(actividad.getId());
        actividadesList.setNombreActividad(actividad.getNombre());
        actividadesList.setDescripcion(actividad.getDescripcion());
        actividadesList.setFechaInicio(actividad.getFechaInicio().toString());
        actividadesList.setFechaTermino(actividad.getFechaTermino().toString());

        Optional<ProfesorActividad> optProfesorActividad = getProfesorByIdActividad(actividad.getId());
        if (optProfesorActividad.isEmpty()) {
            actividadesList.setNombreProfesor("Sin profesor asignado");
        } else {
            ProfesorActividad profesorActividad = optProfesorActividad.get();
            PersonaResponse profesor = apiPersonaService.getPersonaInfo(profesorActividad.getRutProfesor());

            if (profesor == null) {
                actividadesList.setNombreProfesor("Sin profesor asignado");
            } else {
                actividadesList.setNombreProfesor(profesor.getNombreCompleto());
            }

        }

        actividadesList.setCupos(actividad.getCupo());
        DireccionResponse direccion = apiGetDireccionService.getDireccion(actividad.getDirId());

        actividadesList.setNombreCalle(direccion.getNombreCalle());
        actividadesList.setNro(direccion.getNroCalle());
        actividadesList.setComuna(direccion.getComuna());
        actividadesList.setSaldoCupos(actividad.getCupo() - actividad.getInscripciones().size());

        actividadesList.setNombreSubclasificacion(actividad.getNombreSubclasificacion());
        actividadesList.setLongitud(direccion.getLongitud());
        actividadesList.setLatitud(direccion.getLatitud());
        actividadesList.setHorario(actividad.getHorarios().stream()
                .map(horario -> new HorarioDTO(
                        horario.getDiaSemana().name(),
                        horario.getHoraInicio(),
                        horario.getHoraFin()))
                .toList());
        actividadesList.setEstadoActividad(getEstadoActividad(actividad.getId()));

        return actividadesList;
    }

    private String getEstadoActividad(Long idActividad) {

        return sesionActividadService.estadSesionActividad(idActividad) ? "Activa" : "Inactiva";

    }

    private Optional<ProfesorActividad> getProfesorByIdActividad(Long idActividad) {
        return profesorActividadRepository.findByActividadIdAndActivoTrue(idActividad);

    }

}
