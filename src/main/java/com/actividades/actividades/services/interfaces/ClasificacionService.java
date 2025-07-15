package com.actividades.actividades.services.interfaces;

import com.actividades.actividades.dto.ClasificacionRequest;
import com.actividades.actividades.entities.Clasificacion;

public interface ClasificacionService {


    Clasificacion createClasificacion(ClasificacionRequest request);

}
