package com.actividades.actividades.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.actividades.actividades.dto.CreateEncuestaRequest;
import com.actividades.actividades.dto.EncuestaDto;
import com.actividades.actividades.dto.EncuestaDtoResponse;
import com.actividades.actividades.dto.RespuestaEncuestaRequest;
import com.actividades.actividades.dto.UpdateEncuestaRequest;
import com.actividades.actividades.services.interfaces.CreateEncuestaService;
import com.actividades.actividades.services.interfaces.EncuestaResultadosService;
import com.actividades.actividades.services.interfaces.EncuestaService;
import com.actividades.actividades.services.interfaces.RespuestaEncuestaService;

@RestController
@RequestMapping("/api/actividades/encuesta")
public class EncuestaController {

    private final CreateEncuestaService createEncuestaService;

    private final RespuestaEncuestaService respuestaEncuestaService;

    private final EncuestaResultadosService encuestaResultadosService;

    private final EncuestaService encuestaService;

    private static String keyValue = "message";

    public EncuestaController(CreateEncuestaService createEncuestaService,
            RespuestaEncuestaService respuestaEncuestaService, EncuestaResultadosService encuestaResultadosService,
            EncuestaService encuestaService) {
        this.createEncuestaService = createEncuestaService;
        this.respuestaEncuestaService = respuestaEncuestaService;
        this.encuestaResultadosService = encuestaResultadosService;
        this.encuestaService = encuestaService;
    }

    @PostMapping
    public ResponseEntity<Object> createEncuesta(@RequestBody CreateEncuestaRequest request) {

        createEncuestaService.createEncuesta(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(keyValue, "Encuesta creada correctamente"));

    }

    @PutMapping("{id}/actualizar")
    public ResponseEntity<Object> updateEncuesta(@PathVariable Long id, @RequestBody UpdateEncuestaRequest request) {

        createEncuestaService.updateEncuesta(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(keyValue, "Encuesta actualizada correctamente"));

    }

    @PostMapping("/responder")
    public ResponseEntity<Object> respondeEncuesta(@RequestBody RespuestaEncuestaRequest request) {

        respuestaEncuestaService.respondeEncuesta(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(keyValue, "Encuesta guardada correctamente"));

    }

    @GetMapping
    public ResponseEntity<Object> getResultadoEncuesta(@RequestParam Long idEncuesta, @RequestParam Long idActividad) {

        EncuestaDtoResponse response = encuestaResultadosService.getResultadoEncuesta(idEncuesta, idActividad);

        if (response == null)
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(keyValue, "No se encontron resultados"));

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping("/obtener")
    public ResponseEntity<Object> getEncuesta( @RequestParam Long idActividad) {

        EncuestaDto response = encuestaService.getEncuestaByIdActividad(idActividad);

        if (response == null)
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(keyValue, "No se encontron resultados"));

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
