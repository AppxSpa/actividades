package com.actividades.actividades.dto;

import java.util.List;

public class ActividadesList {

    private Long idActividad;
    private String nombreActividad;
    private String descripcion;
    private String nombreProfesor;
    private String fechaInicio;
    private String fechaTermino;
    private String nombreCalle;
    private Double longitud;
    private Double latitud;
    private String estadoActividad;

    private List<HorarioDTO> horario;

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    private Integer nro;

    private String comuna;

    private Integer cupos;
    private Integer saldoCupos;

    private String nombreSubclasificacion;

    public Integer getNro() {
        return nro;
    }

    public void setNro(Integer nro) {
        this.nro = nro;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getNombreCalle() {
        return nombreCalle;
    }

    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }

    public Integer getCupos() {
        return cupos;
    }

    public void setCupos(Integer cupos) {
        this.cupos = cupos;
    }

    public Integer getSaldoCupos() {
        return saldoCupos;
    }

    public void setSaldoCupos(Integer saldoCupos) {
        this.saldoCupos = saldoCupos;
    }

    public String getNombreSubclasificacion() {
        return nombreSubclasificacion;
    }

    public void setNombreSubclasificacion(String nombreSubclasificacion) {
        this.nombreSubclasificacion = nombreSubclasificacion;
    }

    public List<HorarioDTO> getHorario() {
        return horario;
    }

    public void setHorario(List<HorarioDTO> horario) {
        this.horario = horario;
    }

    public Long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public String getEstadoActividad() {
        return estadoActividad;
    }

    public void setEstadoActividad(String estadoActividad) {
        this.estadoActividad = estadoActividad;
    }

}
