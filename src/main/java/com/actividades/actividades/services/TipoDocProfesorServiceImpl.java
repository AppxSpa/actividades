package com.actividades.actividades.services;

import org.springframework.stereotype.Service;

import com.actividades.actividades.entities.TipoDocProfesor;
import com.actividades.actividades.repositories.TipoDocProfesorRepository;
import com.actividades.actividades.services.interfaces.TipoDocProfesorService;

@Service
public class TipoDocProfesorServiceImpl implements TipoDocProfesorService {

    private final TipoDocProfesorRepository tipoDocProfesorRepository;

    public TipoDocProfesorServiceImpl(TipoDocProfesorRepository tipoDocProfesorRepository) {
        this.tipoDocProfesorRepository = tipoDocProfesorRepository;
    }

    @Override
    public void createTipoDocProfesor(String nombre) {

        tipoDocProfesorRepository.save(new TipoDocProfesor(nombre));

    }

}
