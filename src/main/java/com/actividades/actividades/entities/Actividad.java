package com.actividades.actividades.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaTermino;
    private Integer cupo;
    private Long dirId;

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HorarioActividad> horarios;

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Inscripcion> inscripciones;

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RestriccionActividad> restricciones;

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Asistencia> asistencias;

    @ManyToOne(optional = true)
    @JoinColumn(name = "encuesta_id")
    private Encuesta encuesta;

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProfesorActividad> profesores;

    @ManyToOne
    @JoinColumn(name = "subclasificacion_id")
    private Subclasificacion subclasificacion;

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SesionActividad> sesiones;

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FotoActividad> fotos;

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public List<ProfesorActividad> getProfesores() {
        return profesores;
    }

    public void setProfesores(List<ProfesorActividad> profesores) {
        this.profesores = profesores;
    }

    public List<SesionActividad> getSesiones() {
        return sesiones;
    }

    public void setSesiones(List<SesionActividad> sesiones) {
        this.sesiones = sesiones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(LocalDate fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public Integer getCupo() {
        return cupo;
    }

    public void setCupo(Integer cupo) {
        this.cupo = cupo;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public List<RestriccionActividad> getRestricciones() {
        return restricciones;
    }

    public void setRestricciones(List<RestriccionActividad> restricciones) {
        this.restricciones = restricciones;
    }

    public List<Asistencia> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(List<Asistencia> asistencias) {
        this.asistencias = asistencias;
    }

    public Long getDirId() {
        return dirId;
    }

    public void setDirId(Long dirId) {
        this.dirId = dirId;
    }

    public Set<HorarioActividad> getHorarios() {
        return horarios;
    }

    public void setHorarios(Set<HorarioActividad> horarios) {
        this.horarios = horarios;
    }

    public String getNombreSubclasificacion() {
        return this.subclasificacion.getNombreSubClasificacion();
    }

    public Subclasificacion getSubclasificacion() {
        return subclasificacion;
    }

    public void setSubclasificacion(Subclasificacion subclasificacion) {
        this.subclasificacion = subclasificacion;
    }

    public List<FotoActividad> getFotos() {
        return fotos;
    }

    public void setFotos(List<FotoActividad> fotos) {
        this.fotos = fotos;
    }

    public Long getIdEncuesta() {
        return this.encuesta != null ? this.encuesta.getId() : null;
    }

}
