package com.actividades.actividades.dto;

import java.util.List;

public class ProfesorResponse {


    private Long idProfesor;
    private Integer rut;
    private String dv;
    private String nombre;
    private String paterno;
    private String materno;
    private List<ActividadesList> actividades;

    
    public Long getIdProfesor() {
        return idProfesor;
    }
    public void setIdProfesor(Long idProfesor) {
        this.idProfesor = idProfesor;
    }
    public Integer getRut() {
        return rut;
    }
    public void setRut(Integer rut) {
        this.rut = rut;
    }
    public String getDv() {
        return dv;
    }
    public void setDv(String dv) {
        this.dv = dv;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public List<ActividadesList> getActividades() {
        return actividades;
    }
    public void setActividades(List<ActividadesList> actividades) {
        this.actividades = actividades;
    }

    




}
