package com.actividades.actividades.services.interfaces;

import com.actividades.actividades.dto.AsistenciaRequest;
import com.actividades.actividades.dto.AsistenciaResponse;

import java.time.LocalDate;
import java.util.List;

public interface AsistenciaService {

    void createAsistencia(AsistenciaRequest asistencia);

    List<AsistenciaResponse> getAsistenciasByActividad(Long actividadId);

    List<AsistenciaResponse> getAsistenciaByActividadIdAndFecha(Long idActividad, LocalDate fechaAsistencia);

}
