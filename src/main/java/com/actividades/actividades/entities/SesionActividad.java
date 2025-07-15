package com.actividades.actividades.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class SesionActividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime inicioReal;

    private LocalDateTime finReal;

    @ManyToOne
    @JoinColumn(name = "actividad_id", nullable = false)
    private Actividad actividad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getInicioReal() {
        return inicioReal;
    }

    public void setInicioReal(LocalDateTime inicioReal) {
        this.inicioReal = inicioReal;
    }

    public LocalDateTime getFinReal() {
        return finReal;
    }

    public void setFinReal(LocalDateTime finReal) {
        this.finReal = finReal;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

}
