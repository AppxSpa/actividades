
package com.actividades.actividades.services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.actividades.actividades.dto.RespuestaEncuestaRequest;
import com.actividades.actividades.dto.RespuestasDto;
import com.actividades.actividades.entities.Actividad;
import com.actividades.actividades.entities.Encuesta;
import com.actividades.actividades.entities.Persona;
import com.actividades.actividades.entities.PreguntaEncuesta;
import com.actividades.actividades.entities.RespuestaEncuesta;
import com.actividades.actividades.repositories.ActividadRepository;
import com.actividades.actividades.repositories.EncuestaRepository;
import com.actividades.actividades.repositories.PersonaRepository;
import com.actividades.actividades.repositories.PreguntasEncuestaRepository;
import com.actividades.actividades.repositories.RespuestaEncuestaRepository;
import com.actividades.actividades.services.interfaces.RespuestaEncuestaService;

@Service
public class RespuestaEncuestaServiceImpl implements RespuestaEncuestaService {

    private final EncuestaRepository encuestaRepository;
    private final PersonaRepository personaRepository;
    private final ActividadRepository actividadRepository;
    private final RespuestaEncuestaRepository respuestaEncuestaRepository;
    private final PreguntasEncuestaRepository preguntasEncuestaRepository;

    public RespuestaEncuestaServiceImpl(EncuestaRepository encuestaRepository, PersonaRepository personaRepository,
            ActividadRepository actividadRepository, RespuestaEncuestaRepository respuestaEncuestaRepository,
            PreguntasEncuestaRepository preguntasEncuestaRepository) {
        this.encuestaRepository = encuestaRepository;
        this.personaRepository = personaRepository;
        this.actividadRepository = actividadRepository;
        this.respuestaEncuestaRepository = respuestaEncuestaRepository;
        this.preguntasEncuestaRepository = preguntasEncuestaRepository;
    }

    @Override
    public void respondeEncuesta(RespuestaEncuestaRequest request) {

        Encuesta encuesta = getEncuestaById(request.getIdEncuesta());
        Persona persona = getPersonaById(request.getRutAlumno());
        Actividad actividad = getActividadById(request.getIdActividad());

        for (RespuestasDto respuestasDto : request.getRespuestas()) {

            PreguntaEncuesta preguntaEncuesta = getPreguntaEncuestaById(respuestasDto.getIdPregunta());

            Double nota = respuestasDto.getNota();

            RespuestaEncuesta respuestaEncuesta = new RespuestaEncuesta(persona, preguntaEncuesta, encuesta, actividad,
                    LocalDate.now(), nota);

            respuestaEncuestaRepository.save(respuestaEncuesta);

        }

    }

    private Encuesta getEncuestaById(Long idEncuesta) {
        return encuestaRepository.findById(idEncuesta)
                .orElseThrow(() -> new IllegalArgumentException("Encuesta no encontrada"));
    }

    private Persona getPersonaById(Integer rut) {
        return personaRepository.findByRut(rut)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada"));
    }

    private Actividad getActividadById(Long idActividad) {
        return actividadRepository.findById(idActividad)
                .orElseThrow(() -> new IllegalArgumentException("Actividad no encontrada"));
    }

    private PreguntaEncuesta getPreguntaEncuestaById(Long idPregunta) {
        return preguntasEncuestaRepository.findById(idPregunta)
                .orElseThrow(() -> new IllegalArgumentException("Pregunta no encontrada"));
    }

}
