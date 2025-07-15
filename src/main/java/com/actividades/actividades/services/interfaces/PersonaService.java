package com.actividades.actividades.services.interfaces;

import com.actividades.actividades.entities.Persona;

public interface PersonaService {


    Persona getOrCreatePersona(Integer rut);

}
