package com.actividades.actividades.dto;

import java.util.Set;

public class UpdateEncuestaRequest {

    private String nombreEncuesta;
    private Set<UpdatePreguntaRequest> preguntas;

    public String getNombreEncuesta() {
        return nombreEncuesta;
    }

    public void setNombreEncuesta(String nombreEncuesta) {
        this.nombreEncuesta = nombreEncuesta;
    }

    public Set<UpdatePreguntaRequest> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Set<UpdatePreguntaRequest> preguntas) {
        this.preguntas = preguntas;
    }

}
