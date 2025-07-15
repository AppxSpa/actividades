package com.actividades.actividades.services.interfaces;

import com.actividades.actividades.entities.Actividad;
import com.actividades.actividades.entities.Profesor;
import com.actividades.actividades.entities.ProfesorActividad;

import java.time.LocalDate;
import java.util.List;

public interface ProfesorActividadService {

    void create(Profesor profesor, Actividad actividad);

    void changeProfesor(Long idActividad, Profesor profesor, LocalDate fechaInicio);

     List<ProfesorActividad> findByProfesorActivo(Profesor profesor);

}
