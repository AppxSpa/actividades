package com.actividades.actividades.services.interfaces;

import com.actividades.actividades.dto.EncuestaDtoResponse;

public interface EncuestaResultadosService {

    EncuestaDtoResponse getResultadoEncuesta(Long idEncuesta, Long idActividad);
}
