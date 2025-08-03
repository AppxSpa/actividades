package com.actividades.actividades.services.interfaces;

import java.util.List;

import com.actividades.actividades.dto.InscripcionRequest;
import com.actividades.actividades.dto.InscripcionResponse;
import com.actividades.actividades.entities.Actividad;

public interface InscripcionService {

    void createInscripcion(InscripcionRequest request);

    Integer getQuantityInscripciones(Actividad actividad);

    List<InscripcionResponse> getInscrpcionByActividad(Long idActidad);

    List<InscripcionResponse> getInscrpcionByRut(Integer rut);

}
