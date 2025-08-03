package com.actividades.actividades.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actividades.actividades.entities.Actividad;
import com.actividades.actividades.entities.Inscripcion;
import com.actividades.actividades.entities.Persona;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    Optional<Inscripcion> findByPersonaAndActividad(Persona persona, Actividad actividad);

    Integer countByActividad(Actividad actividad);

    List<Inscripcion> findByActividad(Actividad actividad);

    List<Inscripcion> findByPersona(Persona persona);

}
