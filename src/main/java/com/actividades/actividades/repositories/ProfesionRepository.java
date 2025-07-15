package com.actividades.actividades.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actividades.actividades.entities.Profesion;

public interface ProfesionRepository extends JpaRepository<Profesion, Long> {

    Optional<Profesion> findByNombreProfesion(String nombreProfesion);

}
