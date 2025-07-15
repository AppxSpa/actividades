package com.actividades.actividades.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actividades.actividades.entities.Subclasificacion;


public interface SubClasificacionRepository extends JpaRepository<Subclasificacion,Long> {

    Optional<Subclasificacion> findByNombreSubClasificacion(String nombreSubClasificacion);

}
