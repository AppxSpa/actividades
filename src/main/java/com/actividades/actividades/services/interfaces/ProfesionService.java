package com.actividades.actividades.services.interfaces;

import java.util.List;

import com.actividades.actividades.dto.ProfesionResponse;
import com.actividades.actividades.entities.Profesion;

public interface ProfesionService {

    Profesion createProfesion(String nombre);

    List<ProfesionResponse> getProfesiones();

}
