package com.actividades.actividades.services;

import org.springframework.stereotype.Service;

import com.actividades.actividades.dto.ClasificacionDto;
import com.actividades.actividades.dto.ClasificacionRequest;
import com.actividades.actividades.dto.SubclasificacionDto;
import com.actividades.actividades.entities.Clasificacion;
import com.actividades.actividades.repositories.ClasificacionRepository;
import com.actividades.actividades.services.interfaces.ClasificacionService;
import java.util.List;

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

    @Override
    public List<ClasificacionDto> getClasificacionesList() {

        List<Clasificacion> clasificaciones = clasificacionRepository.findAll();

        return clasificaciones.stream().map(clasificacion -> {

            ClasificacionDto dto = new ClasificacionDto();

            dto.setId(clasificacion.getId());
            dto.setNombre(clasificacion.getNombreClasificacion());

            List<SubclasificacionDto> subclasificaciones = clasificacion.getSubclasificaciones().stream().map(sub -> {
                SubclasificacionDto subDto = new SubclasificacionDto();
                subDto.setId(sub.getId());
                subDto.setNombre(sub.getNombreSubClasificacion());

                return subDto;
            }).toList();

            dto.setSubclasificaciones(subclasificaciones);

            return dto;

        }).toList();

    }

}
