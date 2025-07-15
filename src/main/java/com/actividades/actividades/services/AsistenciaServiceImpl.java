package com.actividades.actividades.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.actividades.actividades.dto.AsistenciaRequest;
import com.actividades.actividades.dto.AsistenciaResponse;
import com.actividades.actividades.entities.Actividad;
import com.actividades.actividades.entities.Asistencia;
import com.actividades.actividades.entities.Inscripcion;
import com.actividades.actividades.entities.Persona;
import com.actividades.actividades.exceptions.AsistenciaDuplicadaException;
import com.actividades.actividades.exceptions.InscripcionException;
import com.actividades.actividades.repositories.ActividadRepository;
import com.actividades.actividades.repositories.AsistenciaRepository;
import com.actividades.actividades.repositories.InscripcionRepository;
import com.actividades.actividades.repositories.PersonaRepository;
import com.actividades.actividades.services.interfaces.AsistenciaService;
import com.actividades.actividades.utils.RepositoryUtils;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    private final PersonaRepository personaRepository;
    private final ActividadRepository actividadRepository;
    private final AsistenciaRepository asistenciaRepository;
    private final InscripcionRepository inscripcionRepository;
    private final AsistenciaMapper asistenciaMapper;

    public AsistenciaServiceImpl(PersonaRepository personaRepository, ActividadRepository actividadRepository,
            AsistenciaRepository asistenciaRepository,
            InscripcionRepository inscripcionRepository, AsistenciaMapper asistenciaMapper) {
        this.personaRepository = personaRepository;
        this.actividadRepository = actividadRepository;
        this.asistenciaRepository = asistenciaRepository;
        this.inscripcionRepository = inscripcionRepository;
        this.asistenciaMapper = asistenciaMapper;
    }

    @Override
    public void createAsistencia(AsistenciaRequest request) {

        Persona persona = getPersonaByRut(request.getRut());

        Actividad actividad = getActividadBydI(request.getIdActividad());

        Optional<Inscripcion> optInscripcion = inscripcionRepository.findByPersonaAndActividad(persona, actividad);
        if (optInscripcion.isEmpty()) {
            throw new InscripcionException("No existe una inscripcion para esta actividad");
        }

        Optional<Asistencia> optAsistencia = asistenciaRepository
                .findByPersonaAndFechaAsistencia(persona, LocalDate.now());
        if (optAsistencia.isPresent()) {
            throw new AsistenciaDuplicadaException("Ya existe un asistencia para este día");

        }

        Asistencia asistencia = new Asistencia();
        asistencia.setPersona(persona);
        asistencia.setActividad(actividad);
        asistencia.setFechaAsistencia(LocalDate.now());
        asistencia.setHoraAsistencia(LocalTime.now());
        asistencia.setEstado(Asistencia.EstadoAsistencia.PRESENTE);

        asistenciaRepository.save(asistencia);

    }

    private Persona getPersonaByRut(Integer rut) {

        return RepositoryUtils.findOrThrow(personaRepository.findByRut(rut),
                String.format("No existe la persona con el rut %d", rut));

    }

    private Actividad getActividadBydI(Long id) {
        return RepositoryUtils.findOrThrow(actividadRepository.findById(id),
                String.format("No existe la actividad %d", id));

    }

    @Override
    public List<AsistenciaResponse> getAsistenciasByActividad(Long actividadId) {

        Actividad actividad = getActividadBydI(actividadId);

        List<Asistencia> asistencias = asistenciaRepository.findByActividad(actividad);

        return asistenciaMapper.mapToAsistenciaResponse(asistencias);

    }

    @Override
    public List<AsistenciaResponse> getAsistenciaByActividadIdAndFecha(Long idActividad, LocalDate fechaAsistencia) {

        Actividad actividad = getActividadBydI(idActividad);

        List<Asistencia> asistencias = asistenciaRepository.findByActividadAndFechaAsistencia(actividad, fechaAsistencia);
        

        return asistenciaMapper.mapToAsistenciaResponse(asistencias);
        
    }

}
