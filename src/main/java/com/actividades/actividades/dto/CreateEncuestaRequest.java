package com.actividades.actividades.dto;

import java.util.Set;

public class CreateEncuestaRequest {

    private String nombreEncuesta;
    private Set<String> preguntas;

    public String getNombreEncuesta() {
        return nombreEncuesta;
    }

    public void setNombreEncuesta(String nombreEncuesta) {
        this.nombreEncuesta = nombreEncuesta;
    }

    public Set<String> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Set<String> preguntas) {
        this.preguntas = preguntas;
    }

}
