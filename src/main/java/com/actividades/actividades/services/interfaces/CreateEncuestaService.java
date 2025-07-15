package com.actividades.actividades.services.interfaces;

import com.actividades.actividades.dto.CreateEncuestaRequest;
import com.actividades.actividades.dto.UpdateEncuestaRequest;

public interface CreateEncuestaService {

    void createEncuesta(CreateEncuestaRequest request);

    void updateEncuesta(Long idEncuesta, UpdateEncuestaRequest request);


}
