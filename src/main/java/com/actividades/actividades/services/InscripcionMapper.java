package com.actividades.actividades.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.actividades.actividades.dto.InscripcionResponse;
import com.actividades.actividades.dto.PersonaResponse;
import com.actividades.actividades.entities.Inscripcion;
import com.actividades.actividades.services.interfaces.ApiPersonaService;

@Component
public class InscripcionMapper {

    private final ApiPersonaService apiPersonaService;

    public InscripcionMapper(ApiPersonaService apiPersonaService) {
        this.apiPersonaService = apiPersonaService;
    }

    public List<InscripcionResponse> mapInscripciones(List<Inscripcion> inscripciones) {
        return inscripciones.stream().map(inscripcion -> {
            InscripcionResponse response = new InscripcionResponse();

            PersonaResponse personaResponse = apiPersonaService.getPersonaInfo(inscripcion.getRutPersona());

            response.setIdActividad(inscripcion.getActividadId());
            response.setNombreAlumno(personaResponse.getNombres());
            response.setPaternoAlumno(personaResponse.getPaterno());
            response.setMaternoAlumno(personaResponse.getMaterno());
            response.setRut(personaResponse.getRut());
            response.setVrut(personaResponse.getVrut());
            response.setNombreActividad(inscripcion.getNombreActividad());

            response.setFechaInscripcion(inscripcion.getFechaInscripcion());
            return response;
        }).toList();
    }

}
