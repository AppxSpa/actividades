package com.actividades.actividades.services.interfaces;

import java.util.List;

import com.actividades.actividades.dto.ProfesorRequest;
import com.actividades.actividades.dto.ProfesorResponse;
import com.actividades.actividades.entities.Profesor;

public interface ProfesorService {

    Profesor createProfesor(ProfesorRequest request);

    List<ProfesorResponse> getProfesorList();

}
