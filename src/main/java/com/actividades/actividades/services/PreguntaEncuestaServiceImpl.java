package com.actividades.actividades.services;

import org.springframework.stereotype.Service;

import com.actividades.actividades.entities.Encuesta;
import com.actividades.actividades.entities.PreguntaEncuesta;
import com.actividades.actividades.repositories.EncuestaRepository;
import com.actividades.actividades.repositories.PreguntasEncuestaRepository;
import com.actividades.actividades.services.interfaces.PreguntaEncuestaService;
import com.actividades.actividades.utils.RepositoryUtils;

@Service
public class PreguntaEncuestaServiceImpl implements PreguntaEncuestaService {

    private final EncuestaRepository encuestaRepository;

    private final PreguntasEncuestaRepository preguntasEncuestaRepository;

    public PreguntaEncuestaServiceImpl(EncuestaRepository encuestaRepository,
            PreguntasEncuestaRepository preguntasEncuestaRepository) {
        this.encuestaRepository = encuestaRepository;
        this.preguntasEncuestaRepository = preguntasEncuestaRepository;
    }

    @Override
    public void createPregunta(Long idEncuesta, String pregunta) {
        Encuesta encuesta = getEncuestaById(idEncuesta);

        preguntasEncuestaRepository.save(new PreguntaEncuesta(pregunta, encuesta));

    }

    private Encuesta getEncuestaById(Long idEncuesta) {
        return RepositoryUtils.findOrThrow(encuestaRepository.findById(idEncuesta),
                String.format("Encuesta %d no encontrada", idEncuesta));

    }

    private PreguntaEncuesta getPreguntaEncuestaById(Long idPregunta) {
        return RepositoryUtils.findOrThrow(preguntasEncuestaRepository.findById(idPregunta),
                String.format("Pregunta %d no encontrada", idPregunta));

    }

    @Override
    public void updatePregunta(Long idPregunta, String nuevaPregunta) {

        PreguntaEncuesta preguntaEncuesta = getPreguntaEncuestaById(idPregunta);

        preguntaEncuesta.setPregunta(nuevaPregunta);

    }

}
