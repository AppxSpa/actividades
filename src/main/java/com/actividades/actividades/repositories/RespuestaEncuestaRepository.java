package com.actividades.actividades.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actividades.actividades.entities.Actividad;
import com.actividades.actividades.entities.Encuesta;
import com.actividades.actividades.entities.RespuestaEncuesta;

public interface RespuestaEncuestaRepository extends JpaRepository<RespuestaEncuesta, Long> {

    List<RespuestaEncuesta> findByEncuestaAndActividad(Encuesta encuesta, Actividad actividad);

}
