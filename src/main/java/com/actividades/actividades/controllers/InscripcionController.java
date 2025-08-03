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

import com.actividades.actividades.dto.InscripcionRequest;
import com.actividades.actividades.dto.InscripcionResponse;
import com.actividades.actividades.services.interfaces.InscripcionService;

@RestController
@RequestMapping("/api/actividades/inscripcion")
@CrossOrigin(origins = "https://dev.appx.cl/")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    private static final String KEYVALUE = "message";

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @PostMapping
    public ResponseEntity<Object> createInscripcion(@RequestBody InscripcionRequest request) {

        inscripcionService.createInscripcion(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(KEYVALUE, "Inscripcion creara correctamente"));

    }

    @GetMapping
    public ResponseEntity<Object> getInscritosByActividad(@RequestParam Long idActividad) {

        List<InscripcionResponse> inscripciones = inscripcionService.getInscrpcionByActividad(idActividad);
        if (inscripciones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Map.of(KEYVALUE, "No se encontraron inscripciones para la actividad"));
        }

        return ResponseEntity.ok(inscripcionService.getInscrpcionByActividad(idActividad));

    }

    @GetMapping("/rut")
    public ResponseEntity<Object> getInscritosByRut(@RequestParam Integer rut) {

        List<InscripcionResponse> inscripciones = inscripcionService.getInscrpcionByRut(rut);
        if (inscripciones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Map.of(KEYVALUE, "No se encontraron inscripciones para el rut"));
        }

        return ResponseEntity.ok(inscripcionService.getInscrpcionByRut(rut));

    }

}
