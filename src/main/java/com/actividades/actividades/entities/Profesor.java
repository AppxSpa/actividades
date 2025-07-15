package com.actividades.actividades.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Profesor extends Persona {

    private LocalDate fechaIngreso;

    private LocalDate fechaTermino;
    private boolean activo;

    @OneToOne(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
    private FotoProfesor fotoProfesor;

    public FotoProfesor getFotoProfesor() {
        return fotoProfesor;
    }

    public void setFotoProfesor(FotoProfesor fotoProfesor) {
        this.fotoProfesor = fotoProfesor;
    }

    public List<DocumentosProfesor> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentosProfesor> documentos) {
        this.documentos = documentos;
    }

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentosProfesor> documentos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "profesor_profesion", joinColumns = @JoinColumn(name = "profesor_id"), inverseJoinColumns = @JoinColumn(name = "profesion_id"))
    private List<Profesion> profesiones;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProfesorActividad> actividadesAsignadas;

    @ManyToMany
    @JoinTable(
        name = "profesor_subclasificacion",
        joinColumns = @JoinColumn(name = "profesor_id"),
        inverseJoinColumns = @JoinColumn(name = "subclasificacion_id")
    )
    private List<Subclasificacion> subclasificaciones;

    public List<Profesion> getProfesiones() {
        return profesiones;
    }

    public void setProfesiones(List<Profesion> profesiones) {
        this.profesiones = profesiones;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(LocalDate fechaTermiano) {
        this.fechaTermino = fechaTermiano;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void addProfesion(Profesion profesion) {
        if (profesiones == null) {
            profesiones = new ArrayList<>();
        }
        profesiones.add(profesion);
    }

    public void removeProfesion(Profesion profesion) {
        if (profesiones != null) {
            profesiones.remove(profesion);
        }
    }

    public void addDocumento(DocumentosProfesor documento) {
        documentos.add(documento);
        documento.setProfesor(this);
    }

    public void removeDocumento(DocumentosProfesor documento) {
        documentos.remove(documento);
        documento.setProfesor(null);
    }

    public List<ProfesorActividad> getActividadesAsignadas() {
        return actividadesAsignadas;
    }

    public void setActividadesAsignadas(List<ProfesorActividad> actividadesAsignadas) {
        this.actividadesAsignadas = actividadesAsignadas;
    }

    public List<Subclasificacion> getSubclasificaciones() {
        return subclasificaciones;
    }

    public void setSubclasificaciones(List<Subclasificacion> subclasificaciones) {
        this.subclasificaciones = subclasificaciones;
    }

    

}