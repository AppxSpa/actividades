package com.actividades.actividades.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.actividades.actividades.entities.FotoProfesor;
import com.actividades.actividades.entities.Profesor;
import com.actividades.actividades.repositories.FotoProfesorRepository;
import com.actividades.actividades.repositories.ProfesorRepository;
import com.actividades.actividades.services.interfaces.ArchivoService;
import com.actividades.actividades.services.interfaces.UploadFotoProfesorService;

@Service
public class UploadFotoProfesorServiceImpl implements UploadFotoProfesorService {

    private final ProfesorRepository profesorRepository;
    private final ArchivoService archivoService;
    private final FotoProfesorRepository fotoProfesorRepository;

    public UploadFotoProfesorServiceImpl(ProfesorRepository profesorRepository, ArchivoService archivoService,
            FotoProfesorRepository fotoProfesorRepository) {
        this.profesorRepository = profesorRepository;
        this.archivoService = archivoService;
        this.fotoProfesorRepository = fotoProfesorRepository;
    }

    @Override
    public void uploadFoto(Integer rut, MultipartFile file) throws IOException {

        Profesor profesor = getProfesorByRut(rut);

        String nombreGuardado = archivoService.guardarArchivo(file);

        fotoProfesorRepository.save(new FotoProfesor(profesor, nombreGuardado));

    }

    private Profesor getProfesorByRut(Integer rut) {
        return profesorRepository.findByRut(rut).orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
    }

}
