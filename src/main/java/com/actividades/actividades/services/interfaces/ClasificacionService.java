package com.actividades.actividades.services.interfaces;

import java.util.List;

import com.actividades.actividades.dto.ClasificacionDto;
import com.actividades.actividades.dto.ClasificacionRequest;
import com.actividades.actividades.entities.Clasificacion;

public interface ClasificacionService {

    Clasificacion createClasificacion(ClasificacionRequest request);

    List<ClasificacionDto> getClasificacionesList();

}
