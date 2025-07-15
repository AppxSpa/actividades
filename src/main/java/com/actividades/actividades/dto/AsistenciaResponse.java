package com.actividades.actividades.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AsistenciaResponse {

    private Integer rut;
    private String vrut;
    private String nombres;
    private String paterno;
    private String materno;
    private LocalDate fechaAsistencia;
    private LocalTime horaAsistencia;

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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public LocalDate getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(LocalDate fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public LocalTime getHoraAsistencia() {
        return horaAsistencia;
    }

    public void setHoraAsistencia(LocalTime horaAsistencia) {
        this.horaAsistencia = horaAsistencia;
    }

}
