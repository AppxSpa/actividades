package com.actividades.actividades.dto;

import java.util.Set;

public class EncuestaDtoResponse {

    private Long idEncuesta;
    private String nombreEncuesta;
    private Set<PreguntaDtoResponse> preguntas;
    private Double promedioEncuesta;

    public Long getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Long idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getNombreEncuesta() {
        return nombreEncuesta;
    }

    public void setNombreEncuesta(String nombreEncuesta) {
        this.nombreEncuesta = nombreEncuesta;
    }

    public Set<PreguntaDtoResponse> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Set<PreguntaDtoResponse> preguntas) {
        this.preguntas = preguntas;
    }

    public Double getPromedioEncuesta() {
        return promedioEncuesta;
    }

    public void setPromedioEncuesta(Double promedioEncuesta) {
        this.promedioEncuesta = promedioEncuesta;
    }

}
