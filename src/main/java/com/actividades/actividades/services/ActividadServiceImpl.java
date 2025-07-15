package com.actividades.actividades.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.actividades.actividades.dto.ActividadRequest;
import com.actividades.actividades.dto.ActividadesList;
import com.actividades.actividades.dto.CreateDireccionResponse;
import com.actividades.actividades.dto.DireccionRequest;
import com.actividades.actividades.entities.Actividad;
import com.actividades.actividades.entities.HorarioActividad;
import com.actividades.actividades.entities.Profesor;
import com.actividades.actividades.entities.ProfesorActividad;
import com.actividades.actividades.entities.Subclasificacion;
import com.actividades.actividades.repositories.ActividadRepository;
import com.actividades.actividades.repositories.ProfesorRepository;
import com.actividades.actividades.repositories.SubClasificacionRepository;
import com.actividades.actividades.services.interfaces.ActividadService;
import com.actividades.actividades.services.interfaces.ApiDireccionService;
import com.actividades.actividades.services.interfaces.HorarioActividadService;
import com.actividades.actividades.services.interfaces.ProfesorActividadService;
import com.actividades.actividades.utils.RepositoryUtils;

@Service
public class ActividadServiceImpl implements ActividadService {

    private final ApiDireccionService apiDireccionService;

    private final ActividadRepository actividadRepository;

    private final HorarioActividadService horarioActividadService;

    private final SubClasificacionRepository subClasificacionRepository;

    private final ProfesorRepository profesorRepository;

    private final ProfesorActividadService profesorActividadService;

    private final ActividadMapper actividadMapper;

    public ActividadServiceImpl(ApiDireccionService apiDireccionService, ActividadRepository actividadRepository,
            HorarioActividadService horarioActividadService,

            SubClasificacionRepository subClasificacionRepository,
            ProfesorRepository profesorRepository,
            ProfesorActividadService profesorActividadService,
            ActividadMapper actividadMapper) {
        this.apiDireccionService = apiDireccionService;
        this.actividadRepository = actividadRepository;
        this.horarioActividadService = horarioActividadService;
        this.subClasificacionRepository = subClasificacionRepository;
        this.profesorRepository = profesorRepository;
        this.profesorActividadService = profesorActividadService;
        this.actividadMapper = actividadMapper;
    }

    @Override
    public void createActividad(ActividadRequest request) {

        Actividad actividad = new Actividad();

        actividad.setNombre(request.getNombreActividad());
        actividad.setDescripcion(request.getDescripcion());
        actividad.setFechaInicio(request.getFechaInicio());
        actividad.setFechaTermino(request.getFechaTermino());
        actividad.setCupo(request.getCupos());

        DireccionRequest direccionRequest = new DireccionRequest.Builder()
                .nombreCalle(request.getCalle())
                .numeroCalle(request.getNro())
                .nombreComuna(request.getComuna())
                .longitud(request.getLongitud())
                .latitud(request.getLatitud())
                .build();

        CreateDireccionResponse direccionResponse = apiDireccionService
                .createDireccion(direccionRequest);

        actividad.setDirId(direccionResponse.getDirId());

        Subclasificacion subCl = getSubclasificacionById(request.getIdSubClasificacion());

        Set<HorarioActividad> horarios = horarioActividadService.convertHorarios(request.getHorarios(), actividad);

        actividad.setHorarios(horarios);
        actividad.setSubclasificacion(subCl);
        actividadRepository.save(actividad);

    }

    @Override
    public List<ActividadesList> getActividadesList() {

        List<Actividad> actividades = actividadRepository.findAll();

        return actividades.stream().map(actividadMapper::convertActividad).toList();
    }

    @Override
    public List<ActividadesList> getActividadesByRutProfesor(Integer rut) {

        Profesor profesor = getProfesorByRut(rut);

        List<ProfesorActividad> asignaciones = profesorActividadService.findByProfesorActivo(profesor);

        return asignaciones.stream()
                .map(ProfesorActividad::getActividad)
                .map(actividadMapper::convertActividad)
                .toList();

    }

    @Override
    public void assignProfesor(Integer rut, Long idActividad) {

        Profesor profesor = getProfesorByRut(rut);

        Actividad actividad = getActividadById(idActividad);

        profesorActividadService.create(profesor, actividad);
        actividadRepository.save(actividad);

    }

    private Profesor getProfesorByRut(Integer rut) {
        return RepositoryUtils.findOrThrow(profesorRepository.findByRut(rut),
                String.format("No existe el profesor con el rut  %d", rut));
    }

    private Actividad getActividadById(Long idActividad) {
        return RepositoryUtils.findOrThrow(actividadRepository.findById(idActividad),
                String.format("No existe la actividad %d", idActividad));
    }

    private Subclasificacion getSubclasificacionById(Long subClasificacionId) {

        return RepositoryUtils.findOrThrow(subClasificacionRepository.findById(subClasificacionId),
                String.format("No existe la subclasificacion %d", subClasificacionId));

    }

    @Override
    public void updateActividad(Long idActividad, ActividadRequest request) {

        Actividad actividad = getActividadById(idActividad);

        actividad.setNombre(request.getNombreActividad());
        actividad.setDescripcion(request.getDescripcion());
        actividad.setFechaInicio(request.getFechaInicio());
        actividad.setFechaTermino(request.getFechaTermino());
        actividad.setCupo(request.getCupos());
        actividad.setSubclasificacion(getSubclasificacionById(request.getIdSubClasificacion()));

        actividadRepository.save(actividad);

    }

}
