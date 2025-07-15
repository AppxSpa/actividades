package com.actividades.actividades.services.interfaces;

import java.util.List;

import com.actividades.actividades.dto.PromedioAsistenciaActividad;
import com.actividades.actividades.dto.PromedioAsistenciaResponse;

public interface PromedioAsistenciaService {

    PromedioAsistenciaResponse promedioByRutAndActividad(Integer rut, Long idActividad);

    List<PromedioAsistenciaActividad> promedioByActividad(Long idActividad);
}
