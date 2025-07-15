package com.actividades.actividades.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.actividades.actividades.dto.SubClasifacionReponse;
import com.actividades.actividades.dto.SubclasificacionRequest;
import com.actividades.actividades.entities.Clasificacion;
import com.actividades.actividades.entities.Subclasificacion;
import com.actividades.actividades.repositories.ClasificacionRepository;
import com.actividades.actividades.repositories.SubClasificacionRepository;
import com.actividades.actividades.services.interfaces.SubClasificacionService;
import com.actividades.actividades.utils.RepositoryUtils;

@Service
public class SubClasificacionServiceImpl implements SubClasificacionService {

    private final SubClasificacionRepository subclasificacionRepository;

    private final ClasificacionRepository clasificacionRepository;

    public SubClasificacionServiceImpl(SubClasificacionRepository subClasificacionRepository,
            ClasificacionRepository clasificacionRepository) {
        this.subclasificacionRepository = subClasificacionRepository;
        this.clasificacionRepository = clasificacionRepository;
    }

    @Override
    public Subclasificacion createSubClasificacion(SubclasificacionRequest request) {
        Clasificacion clasificacion = getClasificacionById(request.getClasificacionId());

        Subclasificacion sub = new Subclasificacion();
        sub.setNombreSubClasificacion(request.getNombre());
        sub.setClasificacion(clasificacion);
        return subclasificacionRepository.save(sub);
    }

    @Override
    public SubClasifacionReponse getSubclaficacionById(Long id) {
        Subclasificacion subclasificacion = getSubclasificacionById(id);

        return new SubClasifacionReponse(subclasificacion.getId(),
                subclasificacion.getNombreSubClasificacion(),
                subclasificacion.getClasificacion().getNombreClasificacion());
    }

    @Override
    public List<SubClasifacionReponse> getAllSubclasificaciones() {

        List<Subclasificacion> subclasificaciones = subclasificacionRepository.findAll();

        return subclasificaciones.stream().map(sub -> {

            SubClasifacionReponse response = new SubClasifacionReponse();
            response.setIdSubclasificacion(sub.getId());
            response.setNombreSubclasificacion(sub.getNombreSubClasificacion());
            response.setNombreClasificacion(sub.getNombreClasificacion());

            return response;

        }).toList();
    }

    private Clasificacion getClasificacionById(Long id) {
        return RepositoryUtils.findOrThrow(clasificacionRepository.findById(id),
                String.format("Clasificación %d no encontrada", id));
    }

    private Subclasificacion getSubclasificacionById(Long id) {
        return RepositoryUtils.findOrThrow(subclasificacionRepository.findById(id),
                String.format("Subclasificación %d no encontrada", id));
    }

}
