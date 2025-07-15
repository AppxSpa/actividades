package com.actividades.actividades.services.interfaces;

import com.actividades.actividades.dto.ApiPersonaRequest;
import com.actividades.actividades.dto.PersonaResponse;

public interface ApiPersonaService {

     PersonaResponse getPersonaInfo(Integer rut);

     PersonaResponse createPersona(ApiPersonaRequest request);

}
