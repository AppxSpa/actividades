package com.actividades.actividades.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.actividades.actividades.dto.ActividadAsignacionRequest;
import com.actividades.actividades.dto.ActividadRequest;
import com.actividades.actividades.services.interfaces.ActividadService;
import com.actividades.actividades.services.interfaces.FotoActividaService;
import com.actividades.actividades.services.interfaces.SesionActividadService;

@RestController
@RequestMapping("/api/actividades/actividad")
@CrossOrigin(origins = "https://dev.appx.cl/")
public class ActividadController {

    private final ActividadService actividadService;

    private final SesionActividadService sesionActividadService;

    private final FotoActividaService fotoActividaService;

    private static String keyValue = "message";

    public ActividadController(ActividadService actividadService, SesionActividadService sesionActividadService,
            FotoActividaService fotoActividaService) {
        this.actividadService = actividadService;
        this.sesionActividadService = sesionActividadService;
        this.fotoActividaService = fotoActividaService;
    }

    @PostMapping("/crear")
    public void createActividad(@RequestBody ActividadRequest request) {

        actividadService.createActividad(request);
        ResponseEntity.status(HttpStatus.CREATED);

    }

    @GetMapping("/list")
    public ResponseEntity<Object> getActividades() {

        return ResponseEntity.ok(actividadService.getActividadesList());

    }

    @GetMapping("/list/{rut}")
    public ResponseEntity<Object> getActividadesByProfesor(@PathVariable Integer rut) {

        return ResponseEntity.ok(actividadService.getActividadesByRutProfesor(rut));

    }

    @PostMapping("/asignar")
    public ResponseEntity<Object> assignProfesor(@RequestBody ActividadAsignacionRequest request) {

        actividadService.assignProfesor(request.getRutProfesor(), request.getIdActividad());
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(keyValue, "Profesor asignado correctamente"));

    }

    @PutMapping("/{idActividad}")
    public ResponseEntity<Object> updateActividad(@PathVariable Long idActividad,
            @RequestBody ActividadRequest request) {

        actividadService.updateActividad(idActividad, request);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(keyValue, "Actividad actualizada con éxito"));
    }

    @PostMapping("/iniciar/{actividadId}")
    public ResponseEntity<Object> iniciarSesion(@PathVariable Long actividadId) {
        return ResponseEntity.ok(sesionActividadService.iniciarSesion(actividadId));
    }

    @PostMapping("/finalizar/{actividadId}")
    public ResponseEntity<Object> finalizarSesion(@PathVariable Long actividadId) {
        return ResponseEntity.ok(sesionActividadService.finalizarSesion(actividadId));
    }

    @PostMapping(value = "/upload-foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFoto(
            @RequestParam Long idActividad,
            @RequestParam(required = true) List<MultipartFile> files) {

        try {
            fotoActividaService.uploadFoto(idActividad, files);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Foto agregada exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
