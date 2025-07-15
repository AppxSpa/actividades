package com.actividades.actividades.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.actividades.actividades.dto.EncuestaDto;
import com.actividades.actividades.dto.PreguntaDto;
import com.actividades.actividades.entities.Actividad;
import com.actividades.actividades.entities.Encuesta;
import com.actividades.actividades.repositories.ActividadRepository;
import com.actividades.actividades.repositories.EncuestaRepository;
import com.actividades.actividades.services.interfaces.EncuestaService;
import com.actividades.actividades.utils.RepositoryUtils;

@Service
public class EncuestaServiceImpl implements EncuestaService {

    private final EncuestaRepository encuestaRepository;

    private final ActividadRepository actividadRepository;

    public EncuestaServiceImpl(EncuestaRepository encuestaRepository, ActividadRepository actividadRepository) {
        this.encuestaRepository = encuestaRepository;
        this.actividadRepository = actividadRepository;
    }

    @Override
    @Transactional
    public Encuesta createEncuesta(Encuesta encuesta) {

        return encuestaRepository.save(new Encuesta(encuesta.getNombreEncuesta()));

    }

    @Override
    public Encuesta updateEncuesta(Long id, String nombre) {

        Encuesta encuesta = getEncuestaById(id);

        encuesta.setNombreEncuesta(nombre);

        return encuestaRepository.save(encuesta);

    }

    private Encuesta getEncuestaById(Long id) {
        return RepositoryUtils.findOrThrow(encuestaRepository.findById(id),
                String.format("Encuesta %d no encontrada", id));
    }

    @Override
    public EncuestaDto getEncuestaByIdActividad(Long idActividad) {

        Actividad actividad = geActividadById(idActividad);

        Encuesta encuesta = getEncuestaById(actividad.getIdEncuesta());

        EncuestaDto dto = new EncuestaDto();
        dto.setIdEncuesta(encuesta.getId());
        dto.setNombreEncuesta(encuesta.getNombreEncuesta());

        Set<PreguntaDto> preguntas = encuesta.getPreguntas().stream().map(pregunta -> {
            PreguntaDto preguntaDto = new PreguntaDto();
            preguntaDto.setIdPregunta(pregunta.getId());
            preguntaDto.setPregunta(pregunta.getPregunta());

            return preguntaDto;

        }).collect(Collectors.toSet());

        dto.setPreguntas(preguntas);

        return dto;
    }

    private Actividad geActividadById(Long idActividad) {
        return RepositoryUtils.findOrThrow(actividadRepository.findById(idActividad),
                String.format("Actividadi %d no encontrada", idActividad));
    }

}
