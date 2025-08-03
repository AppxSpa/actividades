package com.actividades.actividades.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.actividades.actividades.dto.InscripcionRequest;
import com.actividades.actividades.dto.InscripcionResponse;
import com.actividades.actividades.entities.Actividad;
import com.actividades.actividades.entities.Inscripcion;
import com.actividades.actividades.entities.Persona;
import com.actividades.actividades.exceptions.InscripcionException;
import com.actividades.actividades.exceptions.InscripcionExceptionDuplicada;
import com.actividades.actividades.repositories.ActividadRepository;
import com.actividades.actividades.repositories.InscripcionRepository;
import com.actividades.actividades.services.interfaces.InscripcionService;
import com.actividades.actividades.services.interfaces.PersonaService;
import com.actividades.actividades.utils.RepositoryUtils;

@Service
public class InscripcionServiceImpl implements InscripcionService {

    private final PersonaService personaService;

    private final ActividadRepository actividadRepository;

    private final InscripcionRepository inscripcionRepository;

    private final InscripcionMapper inscripcionMapper;

    public InscripcionServiceImpl(PersonaService personaService,
            ActividadRepository actividadRepository, InscripcionMapper inscripcionMapper,
            InscripcionRepository inscripcionRepository) {
        this.actividadRepository = actividadRepository;
        this.inscripcionRepository = inscripcionRepository;
        this.personaService = personaService;
        this.inscripcionMapper = inscripcionMapper;
    }

    @Override
    public void createInscripcion(InscripcionRequest request) {

        Persona persona = personaService.getOrCreatePersona(request.getRut());

        Actividad actividad = geActividadById(request.getIdActividad());

        Optional<Inscripcion> optInscripcion = inscripcionRepository.findByPersonaAndActividad(persona, actividad);

        if (optInscripcion.isPresent()) {
            throw new InscripcionExceptionDuplicada("Ya existe una inscripcion para este rut en esta actividad");
        }

        if (hasCupos(actividad)) {
            throw new InscripcionException("No hay cupos disponibles");
        }
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setPersona(persona);
        inscripcion.setActividad(actividad);
        inscripcion.setFechaInscripcion(request.getFechaInscripcion());

        inscripcionRepository.save(inscripcion);

    }

    private boolean hasCupos(Actividad actividad) {
        return (actividad.getCupo() - getQuantityInscripciones(actividad)) == 0;
    }

    @Override
    public Integer getQuantityInscripciones(Actividad actividad) {

        return inscripcionRepository.countByActividad(actividad);

    }

    private Actividad geActividadById(Long idActividad) {
        return RepositoryUtils.findOrThrow(actividadRepository.findById(idActividad),
                String.format("Actividad con id %d no encontrada", idActividad));
    }

    @Override
    public List<InscripcionResponse> getInscrpcionByActividad(Long idActidad) {

        Actividad actividad = geActividadById(idActidad);

        List<Inscripcion> inscripciones = inscripcionRepository.findByActividad(actividad);

        return inscripcionMapper.mapInscripciones(inscripciones);

    }

    @Override
    public List<InscripcionResponse> getInscrpcionByRut(Integer rut) {

        Persona persona = personaService.getOrCreatePersona(rut);

        List<Inscripcion> inscripcion = inscripcionRepository.findByPersona(persona);

        return inscripcionMapper.mapInscripciones(inscripcion);

    }

}
