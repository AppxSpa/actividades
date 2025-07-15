package com.actividades.actividades.services;

import org.springframework.stereotype.Service;

import com.actividades.actividades.dto.ClasificacionRequest;
import com.actividades.actividades.entities.Clasificacion;
import com.actividades.actividades.repositories.ClasificacionRepository;
import com.actividades.actividades.services.interfaces.ClasificacionService;

@Service
public class ClasificacioniServiceImpl implements ClasificacionService {

    private final ClasificacionRepository clasificacionRepository;

    public ClasificacioniServiceImpl(ClasificacionRepository clasificacionRepository) {
        this.clasificacionRepository = clasificacionRepository;
    }

    @Override
    public Clasificacion createClasificacion(ClasificacionRequest request) {
        Clasificacion clasificacion = new Clasificacion();
        clasificacion.setNombreClasificacion(request.getNombre());
        return clasificacionRepository.save(clasificacion);
    }

}
