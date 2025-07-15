package com.actividades.actividades.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.actividades.actividades.dto.AsistenciaResponse;
import com.actividades.actividades.dto.PersonaResponse;
import com.actividades.actividades.entities.Asistencia;
import com.actividades.actividades.services.interfaces.ApiPersonaService;

@Component
public class AsistenciaMapper {

    private final ApiPersonaService apiPersonaService;

    public AsistenciaMapper(ApiPersonaService apiPersonaService) {
        this.apiPersonaService = apiPersonaService;
    }

    public List<AsistenciaResponse> mapToAsistenciaResponse(List<Asistencia> asistencias) {

        return asistencias.stream().map(asistencia -> {

            AsistenciaResponse response = new AsistenciaResponse();
            PersonaResponse persona = apiPersonaService.getPersonaInfo(asistencia.getRutPersona());
            response.setRut(persona.getRut());
            response.setVrut(persona.getVrut());
            response.setNombres(persona.getNombres());
            response.setPaterno(persona.getPaterno());
            response.setMaterno(persona.getMaterno());

            response.setFechaAsistencia(asistencia.getFechaAsistencia());
            response.setHoraAsistencia(asistencia.getHoraAsistencia());

            return response;
        }).toList();
    }

}
