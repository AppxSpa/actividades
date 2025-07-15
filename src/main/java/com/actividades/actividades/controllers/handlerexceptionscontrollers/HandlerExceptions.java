package com.actividades.actividades.controllers.handlerexceptionscontrollers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.actividades.actividades.errors.ErrorResponse;
import com.actividades.actividades.exceptions.AsistenciaDuplicadaException;
import com.actividades.actividades.exceptions.AsistenciaException;
import com.actividades.actividades.exceptions.HorarioExceptions;
import com.actividades.actividades.exceptions.InscripcionException;
import com.actividades.actividades.exceptions.InscripcionExceptionDuplicada;
import com.actividades.actividades.exceptions.NotFounException;
import com.actividades.actividades.exceptions.SesionActividadException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(AsistenciaException.class)
    public ResponseEntity<Object> handlerAsistenciaException(AsistenciaException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (e instanceof AsistenciaException) {
            status = HttpStatus.NOT_FOUND;
        } else if (e instanceof AsistenciaDuplicadaException) {
            status = HttpStatus.CONFLICT;
        }

        ErrorResponse error = maptoErrorResponse(e, request, status);

        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(NotFounException.class)
    public ResponseEntity<Object> handlerNotFoundException(NotFounException e, HttpServletRequest request) {

        ErrorResponse error = maptoErrorResponse(e, request, HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(HorarioExceptions.class)
    public ResponseEntity<Object> handlerHorarioExceptions(HorarioExceptions e, HttpServletRequest request) {

        ErrorResponse error = maptoErrorResponse(e, request, HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(InscripcionException.class)
    public ResponseEntity<Object> handlerInscripcionException(InscripcionException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (e instanceof InscripcionException) {
            status = HttpStatus.NOT_FOUND;
        } else if (e instanceof InscripcionExceptionDuplicada) {
            status = HttpStatus.CONFLICT;
        }

        ErrorResponse error = maptoErrorResponse(e, request, status);

        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(SesionActividadException.class)
    public ResponseEntity<Object> handlerSesionActividadException(SesionActividadException e,
            HttpServletRequest request) {

        ErrorResponse error = maptoErrorResponse(e, request, HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handlerSesionActividadException(NullPointerException e,
            HttpServletRequest request) {

        ErrorResponse error = maptoErrorResponse(e, request, HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }


    private <T extends Exception> ErrorResponse maptoErrorResponse(T e, HttpServletRequest request, HttpStatus status) {

        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .mensaje(e.getMessage())
                .ruta(request.getRequestURI())
                .build();

    }

}
