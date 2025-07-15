package com.actividades.actividades.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.actividades.actividades.dto.CreateEncuestaRequest;
import com.actividades.actividades.dto.UpdateEncuestaRequest;
import com.actividades.actividades.dto.UpdatePreguntaRequest;
import com.actividades.actividades.entities.Encuesta;
import com.actividades.actividades.entities.PreguntaEncuesta;
import com.actividades.actividades.services.interfaces.CreateEncuestaService;
import com.actividades.actividades.services.interfaces.EncuestaService;
import com.actividades.actividades.services.interfaces.PreguntaEncuestaService;

@Service
public class CreateEncuestaServiceImpl implements CreateEncuestaService {

    private final EncuestaService encuestaService;
    private final PreguntaEncuestaService preguntaEncuestaService;

    public CreateEncuestaServiceImpl(EncuestaService encuestaService, PreguntaEncuestaService preguntaEncuestaService) {
        this.encuestaService = encuestaService;
        this.preguntaEncuestaService = preguntaEncuestaService;
    }

    @Override
    @Transactional
    public void createEncuesta(CreateEncuestaRequest request) {

        Encuesta encuesta = new Encuesta(request.getNombreEncuesta());

        Long idEncuesta = encuestaService.createEncuesta(encuesta).getId();

        request.getPreguntas().forEach(pregunta -> preguntaEncuestaService.createPregunta(idEncuesta, pregunta));

    }

    @Override
    @Transactional
    public void updateEncuesta(Long idEncuesta,UpdateEncuestaRequest request) {

        Encuesta encuesta = encuestaService.updateEncuesta(idEncuesta, request.getNombreEncuesta());

        for (PreguntaEncuesta preguntasEncuensta : encuesta.getPreguntas()) {
            for (UpdatePreguntaRequest pregunta : request.getPreguntas()) {
                if (preguntasEncuensta.getId().equals(pregunta.getIdPregunta())) {
                    preguntaEncuestaService.updatePregunta(pregunta.getIdPregunta(), pregunta.getPregunta());
                }
            }
        }
    }

}
