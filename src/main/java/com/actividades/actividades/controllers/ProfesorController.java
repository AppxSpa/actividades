package com.actividades.actividades.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.actividades.actividades.dto.DocumentosProfesorRequest;
import com.actividades.actividades.dto.ProfesorRequest;
import com.actividades.actividades.dto.ProfesorResponse;
import com.actividades.actividades.entities.TipoDocProfesor;
import com.actividades.actividades.services.interfaces.ProfesorService;
import com.actividades.actividades.services.interfaces.TipoDocProfesorService;
import com.actividades.actividades.services.interfaces.UploadDocumentosProfesorService;
import com.actividades.actividades.services.interfaces.UploadFotoProfesorService;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/actividades/profesor")
@CrossOrigin(origins = "https://dev.appx.cl/")
public class ProfesorController {

    private final ProfesorService profesorService;

    private final UploadDocumentosProfesorService uploadDocumentosProfesorService;

    private final UploadFotoProfesorService uploadFotoProfesorService;

    private final TipoDocProfesorService tipoDocProfesorService;

    private static String keyValue = "Message";

    public ProfesorController(ProfesorService profesorService,
            UploadDocumentosProfesorService uploadDocumentosProfesorService,
            UploadFotoProfesorService uploadFotoProfesorService, TipoDocProfesorService tipoDocProfesorService) {
        this.profesorService = profesorService;
        this.uploadDocumentosProfesorService = uploadDocumentosProfesorService;
        this.uploadFotoProfesorService = uploadFotoProfesorService;
        this.tipoDocProfesorService = tipoDocProfesorService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Object> createProfesor(@RequestBody ProfesorRequest request) {

        profesorService.createProfesor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Profesor creado correctamente"));

    }

    @GetMapping("/list")
    public ResponseEntity<Object> listProfesores() {
        List<ProfesorResponse> profesores = profesorService.getProfesorList();

        if (profesores.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(keyValue, "No se encontraron resultados"));
        }
        return ResponseEntity.ok(profesores);

    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadDocumentos(@RequestParam Long tipoDoc,
            @RequestParam Integer rutProfesor,
            @RequestParam(required = true) MultipartFile file) {

        DocumentosProfesorRequest request = new DocumentosProfesorRequest(rutProfesor, file, tipoDoc);

        try {
            uploadDocumentosProfesorService.uploadDocumento(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(keyValue, "archivos subidos con exito"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/upload-foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFoto(
            @RequestParam Integer rutProfesor,
            @RequestParam(required = true) MultipartFile file) {

        try {
            uploadFotoProfesorService.uploadFoto(rutProfesor, file);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(keyValue, "Foto agregada exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<Object> createTipoDoc(@RequestBody TipoDocProfesor request) {
        try {

            tipoDocProfesorService.createTipoDocProfesor(request.getNombreDocumento());
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(keyValue, "Documeento agregado exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
