package com.actividades.actividades.dto;

import java.time.LocalTime;

public class HorarioDTO {

    private String dia; // "LUNES", "MARTES", etc.
    private LocalTime horaInicio;
    private LocalTime horaTermino;


    

    public HorarioDTO() {
    }


    
    public HorarioDTO(String dia, LocalTime horaInicio, LocalTime horaTermino) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaTermino = horaTermino;
    }



    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(LocalTime horaTermino) {
        this.horaTermino = horaTermino;
    }

}
