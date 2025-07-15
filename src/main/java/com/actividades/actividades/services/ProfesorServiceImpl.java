package com.actividades.actividades.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.actividades.actividades.dto.ApiPersonaRequest;
import com.actividades.actividades.dto.CreateDireccionResponse;
import com.actividades.actividades.dto.PersonaResponse;
import com.actividades.actividades.dto.ProfesorRequest;
import com.actividades.actividades.dto.ProfesorResponse;
import com.actividades.actividades.entities.Profesion;
import com.actividades.actividades.entities.Profesor;
import com.actividades.actividades.entities.Subclasificacion;
import com.actividades.actividades.repositories.ProfesionRepository;
import com.actividades.actividades.repositories.ProfesorRepository;
import com.actividades.actividades.repositories.SubClasificacionRepository;
import com.actividades.actividades.services.interfaces.ApiDireccionService;
import com.actividades.actividades.services.interfaces.ApiPersonaService;
import com.actividades.actividades.services.interfaces.ApiUsuarioService;
import com.actividades.actividades.services.interfaces.ProfesorService;
import com.actividades.actividades.utils.RepositoryUtils;

import jakarta.transaction.Transactional;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;

    private final ApiPersonaService apiPersonaService;

    private final ApiDireccionService apiDireccionService;

    private final ProfesionRepository profesionRepository;

    private final ApiUsuarioService apiUsuarioService;

    private final ProfesorMapper profesorMapper;

    private final SubClasificacionRepository subClasificacionRepository;

    public ProfesorServiceImpl(ProfesorRepository profesorRepository, ApiPersonaService apiPersonaService,
            ProfesionRepository profesionRepository, ApiUsuarioService apiUsuarioService,
            ApiDireccionService apiDireccionService,
            ProfesorMapper profesorMapper, SubClasificacionRepository subClasificacionRepository) {
        this.profesorRepository = profesorRepository;
        this.apiPersonaService = apiPersonaService;
        this.apiDireccionService = apiDireccionService;
        this.profesionRepository = profesionRepository;
        this.apiUsuarioService = apiUsuarioService;
        this.profesorMapper = profesorMapper;
        this.subClasificacionRepository = subClasificacionRepository;
    }

    @Override
    @Transactional
    public Profesor createProfesor(ProfesorRequest request) {

        PersonaResponse personaResponse = apiPersonaService.getPersonaInfo(request.getRut());

        if (personaResponse == null) {

            createPersonaWithAddress(request);

        }

        createUser(request);

        return profesorRepository.save(getOrCreateProfesor(request));

    }

    private void createPersonaWithAddress(ProfesorRequest request) {
        CreateDireccionResponse direccionResponse = createDireccion(request);

        ApiPersonaRequest personaRequest = profesorMapper.mapToApiPersonaRequest(request);
        personaRequest.setDirId(direccionResponse.getDirId());

        apiPersonaService.createPersona(personaRequest);
    }

    private void createUser(ProfesorRequest request) {
        apiUsuarioService.createUsuario(request.getRut().toString(), getPassworRut(request.getRut()));
    }

    private CreateDireccionResponse createDireccion(ProfesorRequest request) {

        return apiDireccionService.createDireccion(profesorMapper.mapToDireccionRequest(request));
    }

    private String getPassworRut(Integer rut) {
        return String.valueOf(rut).substring(0, 4);
    }

    private Profesor getOrCreateProfesor(ProfesorRequest request) {
        return profesorRepository.findByRut(request.getRut())
                .orElseGet(() -> {

                    Profesion profesion = getProfesion(request.getProfesion());
                    Profesor profesor = createProfesorObject(request);

                    profesor.addProfesion(profesion);
                    return profesor;
                });

    }

    private Profesion getProfesion(String nombreProfesion) {
        return RepositoryUtils.findOrThrow(profesionRepository.findByNombreProfesion(nombreProfesion),
                String.format("No exite la profesion %s", nombreProfesion));

    }

    private Profesor createProfesorObject(ProfesorRequest request) {
        Profesor profesor = new Profesor();
        profesor.setRut(request.getRut());
        profesor.setFechaIngreso(LocalDate.now());
        profesor.setActivo(true);
        profesor.setSubclasificaciones(mapToSubclasificacion(request.getSubclasificaciones()));

        return profesor;
    }

    @Override
    public List<ProfesorResponse> getProfesorList() {
        List<Profesor> profesores = profesorRepository.findAll();

        return profesores.stream().map(profesorMapper::mapProfesorToResponse).toList();
    }

    private List<Subclasificacion> mapToSubclasificacion(List<String> subclasifiacion) {

        List<Subclasificacion> subclasificaciones = new ArrayList<>();

        subclasifiacion.forEach(sub -> {
            Subclasificacion subclasificacion = RepositoryUtils.findOrThrow(
                    subClasificacionRepository.findByNombreSubClasificacion(sub),
                    String.format("No exista la clasificaicon %s ", sub));

            subclasificaciones.add(subclasificacion);

        });

        return subclasificaciones;

    }

}
