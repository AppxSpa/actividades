package com.actividades.actividades.dto;

public class ActividadAsignacionRequest {

    private Long idActividad;
    private Integer rutProfesor;

    public Long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public Integer getRutProfesor() {
        return rutProfesor;
    }

    public void setRutProfesor(Integer rut) {
        this.rutProfesor = rut;
    }

}
