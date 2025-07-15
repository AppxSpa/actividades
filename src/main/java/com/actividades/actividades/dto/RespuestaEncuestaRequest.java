package com.actividades.actividades.dto;

import java.util.List;

public class RespuestaEncuestaRequest {

    private Integer rutAlumno;
    private Long idActividad;
    private Long idEncuesta;
    private List<RespuestasDto> respuestas;

    public Integer getRutAlumno() {
        return rutAlumno;
    }

    public void setRutAlumno(Integer rutAlumno) {
        this.rutAlumno = rutAlumno;
    }

    public Long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public List<RespuestasDto> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<RespuestasDto> respuestas) {
        this.respuestas = respuestas;
    }

    public Long getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Long idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

}
