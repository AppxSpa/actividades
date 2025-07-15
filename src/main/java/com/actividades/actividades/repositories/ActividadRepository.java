package com.actividades.actividades.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actividades.actividades.entities.Actividad;



public interface ActividadRepository extends JpaRepository<Actividad, Long> {

    Optional<Actividad> findByNombre(String nombre);

    Optional<Actividad> findById(Long id);




}
