package com.actividades.actividades.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actividades.actividades.entities.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor,Long> {

    Optional<Profesor> findByRut(Integer rut);

}
