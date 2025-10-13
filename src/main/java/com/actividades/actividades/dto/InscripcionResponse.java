package com.actividades.actividades.dto;

import java.time.LocalDate;

public class InscripcionResponse {

    private Integer rut;
    private String vrut;
    private String nombreAlumno;
    private String paternoAlumno;
    private String maternoAlumno;
    private LocalDate fechaInscripcion;
    private ActividadesList detalleActividad;

    public Integer getRut() {
        return rut;
    }

    public void setRut(Integer rut) {
        this.rut = rut;
    }

    public String getVrut() {
        return vrut;
    }

    public void setVrut(String vrut) {
        this.vrut = vrut;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getPaternoAlumno() {
        return paternoAlumno;
    }

    public void setPaternoAlumno(String paternoAlumno) {
        this.paternoAlumno = paternoAlumno;
    }

    public String getMaternoAlumno() {
        return maternoAlumno;
    }

    public void setMaternoAlumno(String maternoAlumno) {
        this.maternoAlumno = maternoAlumno;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public ActividadesList getDetalleActividad() {
        return detalleActividad;
    }

    public void setDetalleActividad(ActividadesList detalleActividad) {
        this.detalleActividad = detalleActividad;
    }

}
