package com.actividades.actividades.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actividades.actividades.entities.Persona;

public interface PersonaRepository extends JpaRepository<Persona,Long> {

    Optional<Persona> findByRut(Integer rut);

}
