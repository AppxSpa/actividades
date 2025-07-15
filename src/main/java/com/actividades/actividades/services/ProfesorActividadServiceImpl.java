package com.actividades.actividades.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.actividades.actividades.entities.Actividad;
import com.actividades.actividades.entities.Profesor;
import com.actividades.actividades.entities.ProfesorActividad;
import com.actividades.actividades.repositories.ActividadRepository;
import com.actividades.actividades.repositories.ProfesorActividadRepository;
import com.actividades.actividades.services.interfaces.ProfesorActividadService;

@Service
public class ProfesorActividadServiceImpl implements ProfesorActividadService {

    private final ProfesorActividadRepository profesorActividadRepository;
    private final ActividadRepository actividadRepository;

    public ProfesorActividadServiceImpl(ProfesorActividadRepository profesorActividadRepository,
            ActividadRepository actividadRepository) {
        this.profesorActividadRepository = profesorActividadRepository;
        this.actividadRepository = actividadRepository;
    }

    @Override
    public void create(Profesor profesor, Actividad actividad) {

        profesorActividadRepository.save(mapEntityCreate(profesor, actividad));

    }

    private ProfesorActividad mapEntityCreate(Profesor profesor, Actividad actividad) {
        ProfesorActividad profesorActividad = new ProfesorActividad();
        profesorActividad.setProfesor(profesor);
        profesorActividad.setActividad(actividad);
        profesorActividad.setActivo(true);
        profesorActividad.setFechaInicio(LocalDate.now());

        return profesorActividad;
    }

    @Override
    public void changeProfesor(Long idActividad, Profesor profesor, LocalDate fechaInicio) {

        // buscar profesor activo actual
        Optional<ProfesorActividad> actual = profesorActividadRepository.findByActividadIdAndActivoTrue(idActividad);

        actual.ifPresent(ap -> {
            ap.setFechaFin(LocalDate.now());
            ap.setActivo(false);
            profesorActividadRepository.save(ap);
        });

        Actividad actividad = actividadRepository.findById(idActividad).orElseThrow();

        ProfesorActividad nuevo = new ProfesorActividad();
        nuevo.setActividad(actividad);
        nuevo.setProfesor(profesor);
        nuevo.setFechaInicio(fechaInicio);
        nuevo.setActivo(true);

        profesorActividadRepository.save(nuevo);

    }

    @Override
    public List<ProfesorActividad> findByProfesorActivo(Profesor profesor) {
        return profesorActividadRepository.findByProfesorAndActivoTrue(profesor);
    }

}
