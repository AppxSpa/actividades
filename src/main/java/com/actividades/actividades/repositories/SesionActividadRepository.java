package com.actividades.actividades.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actividades.actividades.entities.SesionActividad;

public interface SesionActividadRepository extends JpaRepository<SesionActividad,Long> {

    List<SesionActividad> findByActividadId(Long actividadId);
    
    Optional<SesionActividad> findFirstByActividadIdAndFinRealIsNullOrderByInicioRealDesc(Long actividadId);

    Optional<SesionActividad> findByIdAndInicioRealAndFinRealIsNotNull(Long id, LocalDateTime fecha);

    boolean existsByActividadIdAndInicioRealBetween(Long actividadId, LocalDateTime startOfDay, LocalDateTime endOfDay);

}
