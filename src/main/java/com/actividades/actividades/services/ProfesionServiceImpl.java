package com.actividades.actividades.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.actividades.actividades.dto.ProfesionResponse;
import com.actividades.actividades.entities.Profesion;
import com.actividades.actividades.repositories.ProfesionRepository;
import com.actividades.actividades.services.interfaces.ProfesionService;

@Service
public class ProfesionServiceImpl implements ProfesionService {

    private final ProfesionRepository profesionRepository;

    public ProfesionServiceImpl(ProfesionRepository profesionRepository) {
        this.profesionRepository = profesionRepository;
    }

    @Override
    public Profesion createProfesion(String nombre) {

        return profesionRepository.save(new Profesion(nombre));

    }

    @Override
    public List<ProfesionResponse> getProfesiones() {

        List<Profesion> profesiones = profesionRepository.findAll();

        return profesiones.stream().map(profesion -> ProfesionResponse.builder()
                .idProfesion(profesion.getId())
                .nombreProfesion(profesion.getNombreProfesion())
                .build()).toList();
    }

}
