package com.actividades.actividades.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.actividades.actividades.dto.ActividadesList;
import com.actividades.actividades.dto.InscripcionResponse;
import com.actividades.actividades.dto.PersonaResponse;
import com.actividades.actividades.entities.Actividad;
import com.actividades.actividades.entities.Inscripcion;
import com.actividades.actividades.services.interfaces.ApiPersonaService;

@Component
public class InscripcionMapper {

    private final ApiPersonaService apiPersonaService;

    private final ActividadMapper actividadMapper;

    public InscripcionMapper(ApiPersonaService apiPersonaService, ActividadMapper actividadMapper) {
        this.apiPersonaService = apiPersonaService;
        this.actividadMapper = actividadMapper;
    }

    public List<InscripcionResponse> mapInscripciones(List<Inscripcion> inscripciones) {
        return inscripciones.stream().map(inscripcion -> {
            InscripcionResponse response = new InscripcionResponse();

            PersonaResponse personaResponse = apiPersonaService.getPersonaInfo(inscripcion.getRutPersona());

            response.setNombreAlumno(personaResponse.getNombres());
            response.setPaternoAlumno(personaResponse.getPaterno());
            response.setMaternoAlumno(personaResponse.getMaterno());
            response.setRut(personaResponse.getRut());
            response.setVrut(personaResponse.getVrut());

            response.setFechaInscripcion(inscripcion.getFechaInscripcion());

            response.setDetalleActividad(maptToActividadesList(inscripcion.getActividad()));
            return response;
        }).toList();
    }

    private ActividadesList maptToActividadesList(Actividad actividad) {

        return actividadMapper.convertActividad(actividad);

    }

}
