package com.actividades.actividades.services.interfaces;

import java.util.List;

import com.actividades.actividades.dto.ActividadRequest;
import com.actividades.actividades.dto.ActividadesList;

public interface ActividadService {

  void createActividad(ActividadRequest request);

  List<ActividadesList> getActividadesList();

  List<ActividadesList> getActividadesByRutProfesor(Integer rut);

  void assignProfesor(Integer rut, Long idActividad);

  void updateActividad(Long idActividad, ActividadRequest request);

}
