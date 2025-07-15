package com.actividades.actividades.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actividades.actividades.entities.Actividad;
import com.actividades.actividades.entities.Asistencia;
import com.actividades.actividades.entities.Persona;

import java.time.LocalDate;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    Optional<Asistencia> findByPersonaAndFechaAsistencia(Persona persona, LocalDate fechaAsistencia);

    List<Asistencia> findByActividad(Actividad actividad);

    List<Asistencia> findByPersonaAndActividad(Persona persona, Actividad actividad);

    List<Asistencia> findByActividadAndFechaAsistencia(Actividad actividad, LocalDate fechaAsistencia);

}
