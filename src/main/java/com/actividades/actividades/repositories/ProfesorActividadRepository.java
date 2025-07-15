package com.actividades.actividades.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actividades.actividades.entities.Profesor;
import com.actividades.actividades.entities.ProfesorActividad;

public interface ProfesorActividadRepository extends JpaRepository<ProfesorActividad,Long> {

    Optional<ProfesorActividad> findByActividadIdAndActivoTrue(Long idActividad);

    List<ProfesorActividad> findByProfesorAndActivoTrue(Profesor profesor);

}
