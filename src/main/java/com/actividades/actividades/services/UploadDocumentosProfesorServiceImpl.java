package com.actividades.actividades.services;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.actividades.actividades.dto.DocumentosProfesorRequest;
import com.actividades.actividades.entities.DocumentosProfesor;
import com.actividades.actividades.entities.Profesor;
import com.actividades.actividades.entities.TipoDocProfesor;
import com.actividades.actividades.repositories.DocumentosProfesorRepository;
import com.actividades.actividades.repositories.ProfesorRepository;
import com.actividades.actividades.repositories.TipoDocProfesorRepository;
import com.actividades.actividades.services.interfaces.ArchivoService;
import com.actividades.actividades.services.interfaces.UploadDocumentosProfesorService;

@Service
public class UploadDocumentosProfesorServiceImpl implements UploadDocumentosProfesorService {

    private final ProfesorRepository profesorRepository;

    private final DocumentosProfesorRepository documentosProfesorRepository;

    private final TipoDocProfesorRepository tipoDocProfesorRepository;

    private final ArchivoService archivoService;

    public UploadDocumentosProfesorServiceImpl(ProfesorRepository profesorRepository,
            DocumentosProfesorRepository documentosProfesorRepository,
            TipoDocProfesorRepository tipoDocProfesorRepository, ArchivoService archivoService) {
        this.profesorRepository = profesorRepository;
        this.documentosProfesorRepository = documentosProfesorRepository;
        this.tipoDocProfesorRepository = tipoDocProfesorRepository;
        this.archivoService = archivoService;
    }

    @Override
    public void uploadDocumento(DocumentosProfesorRequest request) throws IOException {

        Profesor profesor = getProfesorByRut(request.getRutProfesor());
        TipoDocProfesor tipoDocProfesor = getTipoDocProfesorById(request.getTipoDoc());

        String arvhivo = archivoService.guardarArchivo(request.getFile());

        DocumentosProfesor documentosProfesor = new DocumentosProfesor();
        documentosProfesor.setProfesor(profesor);
        documentosProfesor.setTipoDocumento(tipoDocProfesor);
        documentosProfesor.setNombreDocumento(arvhivo);

        documentosProfesorRepository.save(documentosProfesor);

    }

    private Profesor getProfesorByRut(Integer rut) {
        return profesorRepository.findByRut(rut).orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
    }

    private final TipoDocProfesor getTipoDocProfesorById(Long id) {
        return tipoDocProfesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado"));
    }

}
