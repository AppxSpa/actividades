package com.actividades.actividades.dto;

import java.time.LocalDate;

public class DetalleActividad {

    private Long idActividad;
    private LocalDate fehcaInicio;
    private LocalDate fechaFin;
    private String descripcion;
    private String direccion;
    private String nombreProfesor;

    public Long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public LocalDate getFehcaInicio() {
        return fehcaInicio;
    }

    public void setFehcaInicio(LocalDate fehcaInicio) {
        this.fehcaInicio = fehcaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

}
