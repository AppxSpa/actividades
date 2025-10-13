package com.actividades.actividades.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.actividades.actividades.dto.ActividadesList;
import com.actividades.actividades.dto.DireccionResponse;
import com.actividades.actividades.dto.HorarioDTO;
import com.actividades.actividades.dto.PersonaResponse;
import com.actividades.actividades.entities.Actividad;
import com.actividades.actividades.entities.FotoActividad;
import com.actividades.actividades.entities.HorarioActividad;
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
        actividadesList.setFotos(obtieneNombreFotos(actividad.getFotos()));

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

        if (direccion != null) {
            actividadesList.setNombreCalle(direccion.getNombreCalle());
            actividadesList.setNro(direccion.getNroCalle());
            actividadesList.setComuna(direccion.getComuna());
            actividadesList.setLongitud(direccion.getLongitud());
            actividadesList.setLatitud(direccion.getLatitud());

        } else {
            actividadesList.setNombreCalle("Sin direccion");
            actividadesList.setNro(0);
            actividadesList.setComuna("Sin direccion");
            actividadesList.setLongitud(0.0);
            actividadesList.setLatitud(0.0);

        }

        actividadesList.setSaldoCupos(actividad.getCupo() - actividad.getInscripciones().size());

        actividadesList.setNombreSubclasificacion(actividad.getNombreSubclasificacion());

        actividadesList.setHorario(obtieneHorario(actividad.getHorarios()));
        actividadesList.setEstadoActividad(getEstadoActividad(actividad.getId()));

        return actividadesList;
    }

    private String getEstadoActividad(Long idActividad) {

        return sesionActividadService.haySesionActiva(idActividad) ? "Activa" : "Inactiva";

    }

    private Optional<ProfesorActividad> getProfesorByIdActividad(Long idActividad) {
        return profesorActividadRepository.findByActividadIdAndActivoTrue(idActividad);

    }

    private List<String> obtieneNombreFotos(List<FotoActividad> fotos) {

        return fotos.stream().map(FotoActividad::getUrlFoto).toList();
    }

    private List<HorarioDTO> obtieneHorario(Set<HorarioActividad> horarios) {

        return horarios.stream()
                .map(horario -> new HorarioDTO(
                        horario.getDiaSemana().name(),
                        horario.getHoraInicio(),
                        horario.getHoraFin()))
                .toList();
    }

}
