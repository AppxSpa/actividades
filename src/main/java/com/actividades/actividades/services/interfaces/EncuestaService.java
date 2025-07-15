package com.actividades.actividades.services.interfaces;

import com.actividades.actividades.dto.EncuestaDto;
import com.actividades.actividades.entities.Encuesta;

public interface EncuestaService {

    Encuesta createEncuesta(Encuesta encuesta);

    Encuesta updateEncuesta(Long id, String nombre);

     EncuestaDto getEncuestaByIdActividad(Long idActividad);

}
