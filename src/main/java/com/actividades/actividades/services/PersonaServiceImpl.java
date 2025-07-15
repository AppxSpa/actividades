package com.actividades.actividades.services;

import org.springframework.stereotype.Service;

import com.actividades.actividades.entities.Persona;
import com.actividades.actividades.repositories.PersonaRepository;
import com.actividades.actividades.services.interfaces.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Persona getOrCreatePersona(Integer rut) {
        return  personaRepository.findByRut(rut)
        .orElseGet(()->createPersona(rut));

    }

    private Persona createPersona(Integer rut) {
        Persona persona = new Persona();
        persona.setRut(rut);
        return personaRepository.save(persona);
    }

}
