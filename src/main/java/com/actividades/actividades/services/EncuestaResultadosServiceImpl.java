package com.actividades.actividades.services;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.actividades.actividades.dto.EncuestaDtoResponse;
import com.actividades.actividades.dto.PreguntaDtoResponse;
import com.actividades.actividades.entities.Actividad;
import com.actividades.actividades.entities.Encuesta;
import com.actividades.actividades.entities.RespuestaEncuesta;
import com.actividades.actividades.repositories.ActividadRepository;
import com.actividades.actividades.repositories.EncuestaRepository;
import com.actividades.actividades.repositories.RespuestaEncuestaRepository;
import com.actividades.actividades.services.interfaces.EncuestaResultadosService;
import com.actividades.actividades.utils.RepositoryUtils;

@Service
public class EncuestaResultadosServiceImpl implements EncuestaResultadosService {

    private final EncuestaRepository encuestaRepository;

    private final RespuestaEncuestaRepository respuestaEncuestaRepository;

    private final ActividadRepository actividadRepository;

    public EncuestaResultadosServiceImpl(EncuestaRepository encuestaRepository,
            RespuestaEncuestaRepository respuestaEncuestaRepository, ActividadRepository actividadRepository) {
        this.encuestaRepository = encuestaRepository;
        this.respuestaEncuestaRepository = respuestaEncuestaRepository;
        this.actividadRepository = actividadRepository;
    }

    @Override
    public EncuestaDtoResponse getResultadoEncuesta(Long idEncuesta, Long idActividad) {

        Encuesta encuesta = getIdEncuestaById(idEncuesta);

        Actividad actividad = getActividadById(idActividad);

        EncuestaDtoResponse response = new EncuestaDtoResponse();

        response.setIdEncuesta(encuesta.getId());
        response.setNombreEncuesta(encuesta.getNombreEncuesta());

        List<RespuestaEncuesta> respuestas = respuestaEncuestaRepository.findByEncuestaAndActividad(encuesta,
                actividad);

        

        Map<String, Double> promedios = calcularPromedioPregunta(respuestas);

        Set<PreguntaDtoResponse> preguntas = maptoPreguntaDtoResponse(promedios);

        response.setPreguntas(preguntas);

        response.setPromedioEncuesta(calcularPromedioEncuesta(respuestas));

        return response;

    }

    private Set<PreguntaDtoResponse> maptoPreguntaDtoResponse(Map<String, Double> promedios) {
        return promedios.entrySet().stream()
                .map(entry -> new PreguntaDtoResponse(entry.getKey(), entry.getValue()))
                .collect(Collectors.toSet());
    }

    private Encuesta getIdEncuestaById(Long idEncuesta) {
        return RepositoryUtils.findOrThrow(encuestaRepository.findById(idEncuesta),
                String.format("Encuesta %d no existe", idEncuesta));

    }

    private Actividad getActividadById(Long id) {
        return RepositoryUtils.findOrThrow(actividadRepository.findById(id),
                String.format("Actividad %d no existe", id));

    }

    private Map<String, Double> calcularPromedioPregunta(List<RespuestaEncuesta> respuestas) {

        return respuestas.stream()
                .filter(r -> r.getNota() != null && r.getPregunta() != null)
                .collect(Collectors.groupingBy(
                        r -> r.getPregunta().getPregunta(),
                        Collectors.averagingDouble(RespuestaEncuesta::getNota)));

    }

    private double calcularPromedioEncuesta(List<RespuestaEncuesta> respuestas) {

        return respuestas.stream()
                .filter(r -> r.getNota() != null && r.getEncuesta() != null)
                .mapToDouble(RespuestaEncuesta::getNota)
                .average()
                .orElse(0.0);

    }

}
