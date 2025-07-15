package com.actividades.actividades.controllers;

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

import com.actividades.actividades.dto.InscripcionRequest;
import com.actividades.actividades.services.interfaces.InscripcionService;

@RestController
@RequestMapping("/api/actividades/inscripcion")
@CrossOrigin(origins = "https://dev.appx.cl/")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @PostMapping
    public ResponseEntity<Object> createInscripcion(@RequestBody InscripcionRequest request) {

        inscripcionService.createInscripcion(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Inscripcion creara correctamente"));

    }

    @GetMapping
    public ResponseEntity<Object> getInscritosByActividad(@RequestParam Long idActividad) {

        return ResponseEntity.ok(inscripcionService.getInscrpcionByActividad(idActividad));

    }

}
