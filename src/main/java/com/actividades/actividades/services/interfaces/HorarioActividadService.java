package com.actividades.actividades.services.interfaces;

import java.util.List;
import java.util.Set;

import com.actividades.actividades.dto.HorarioDTO;
import com.actividades.actividades.entities.Actividad;
import com.actividades.actividades.entities.HorarioActividad;

public interface HorarioActividadService {

    Set<HorarioActividad> convertHorarios(List<HorarioDTO> horariosDTO, Actividad actividad);

}
