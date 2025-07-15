package com.actividades.actividades.services.interfaces;

import com.actividades.actividades.dto.SubClasifacionReponse;
import com.actividades.actividades.dto.SubclasificacionRequest;
import com.actividades.actividades.entities.Subclasificacion;
import java.util.List;

public interface SubClasificacionService {


    Subclasificacion createSubClasificacion(SubclasificacionRequest request);

    SubClasifacionReponse getSubclaficacionById(Long id);

    List<SubClasifacionReponse> getAllSubclasificaciones();

}
