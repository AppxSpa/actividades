package com.actividades.actividades.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.actividades.actividades.dto.ActividadesList;
import com.actividades.actividades.dto.ApiPersonaRequest;
import com.actividades.actividades.dto.DireccionRequest;
import com.actividades.actividades.dto.PersonaResponse;
import com.actividades.actividades.dto.ProfesorRequest;
import com.actividades.actividades.dto.ProfesorResponse;
import com.actividades.actividades.entities.Profesor;
import com.actividades.actividades.services.interfaces.ActividadService;
import com.actividades.actividades.services.interfaces.ApiPersonaService;

@Component
public class ProfesorMapper {

     private final ApiPersonaService apiPersonaService;

     private final ActividadService actividadService;

    public ProfesorMapper(ApiPersonaService apiPersonaService,ActividadService actividadService) {
        this.apiPersonaService = apiPersonaService;
        this.actividadService = actividadService;
    }

    public DireccionRequest mapToDireccionRequest(ProfesorRequest request) {
        return new DireccionRequest.Builder()
                .nombreCalle(request.getNombreCalle())
                .numeroCalle(request.getNumeroCalle())
                .nombreComuna(request.getNombreComuna())
                .longitud(request.getLongitud())
                .latitud(request.getLatitud())
                .build();
    }

    public ApiPersonaRequest mapToApiPersonaRequest(ProfesorRequest request) {
        return new ApiPersonaRequest.Builder()
                .rut(request.getRut())
                .vrut(request.getvRut())
                .nombres(request.getNombres())
                .paterno(request.getPaterno())
                .materno(request.getMaterno())
                .fechaNac(request.getFechaNac())
                .fono(request.getFono())
                .email(request.getEmail())
                .estadoCivil(request.getEstadoCivil())
                .genero(request.getGenero())
                .nacionalidad(request.getNacionalidad())
                .build();
    }

    public  ProfesorResponse mapProfesorToResponse(Profesor profesor) {
        ProfesorResponse response = new ProfesorResponse();
        PersonaResponse personaResponse = apiPersonaService.getPersonaInfo(profesor.getRut());
        List<ActividadesList> actividades = actividadService.getActividadesByRutProfesor(profesor.getRut());
        response.setIdProfesor(profesor.getId());
        response.setRut(profesor.getRut());
        response.setDv(personaResponse.getVrut());
        response.setNombre(personaResponse.getNombres());
        response.setPaterno(personaResponse.getPaterno());
        response.setMaterno(personaResponse.getMaterno());
        response.setActividades(actividades);

        return response;
    }

    

}
