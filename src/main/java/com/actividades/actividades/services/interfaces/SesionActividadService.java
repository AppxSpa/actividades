package com.actividades.actividades.services.interfaces;

import com.actividades.actividades.entities.SesionActividad;

public interface SesionActividadService {

    SesionActividad iniciarSesion(Long actividadId);

    SesionActividad finalizarSesion(Long actividadId);

    

boolean haySesionActiva(Long idActividad);

}
