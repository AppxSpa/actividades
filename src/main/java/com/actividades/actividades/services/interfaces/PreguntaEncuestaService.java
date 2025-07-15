package com.actividades.actividades.services.interfaces;


public interface PreguntaEncuestaService {

    void createPregunta(Long idEncuesta, String pregunta);

    void updatePregunta(Long idPregunta, String nuevaPregunta);

}
