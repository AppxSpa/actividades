package com.actividades.actividades.dto;

public class PreguntaDtoResponse {

    private String pregunta;
    private Double promedioPregunta;

    public PreguntaDtoResponse() {
    }

    public PreguntaDtoResponse(String pregunta, Double promedioPregunta) {
        this.pregunta = pregunta;
        this.promedioPregunta = promedioPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public Double getPromedioPregunta() {
        return promedioPregunta;
    }

    public void setPromedioPregunta(Double promedioPregunta) {
        this.promedioPregunta = promedioPregunta;
    }

}
