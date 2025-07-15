package com.actividades.actividades.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.actividades.actividades.config.ApiProperties;
import com.actividades.actividades.exceptions.UsuarioException;
import com.actividades.actividades.services.interfaces.ApiUsuarioService;

import reactor.core.publisher.Mono;

@Service
public class ApiUsuarioServiceImpl implements ApiUsuarioService {

    private final WebClient webClientUsuarios;

    public ApiUsuarioServiceImpl(WebClient.Builder webClientBuilder, ApiProperties apiProperties) {
        this.webClientUsuarios = webClientBuilder.baseUrl(apiProperties.getLoginUrl()).build();
    }

    @Override
   public void createUsuario(String rut, String password) {
    Map<String, String> request = Map.of(
        "username", rut,
        "password", password
    );

    Optional<Object> response = Optional.ofNullable(
        webClientUsuarios.post()
            .uri("/create")
            .bodyValue(request)
            .retrieve()
            .onStatus(HttpStatusCode::isError, clientResponse ->
                clientResponse.bodyToMono(String.class).flatMap(errorBody ->
                    Mono.error(new UsuarioException("Error al crear usuario: " + errorBody))
                )
            )
            .bodyToMono(Object.class) // o el tipo que devuelve tu API
            .onErrorResume(e -> Mono.empty())
            .block()
    );

    if (response.isEmpty()) {
        throw new UsuarioException("No se pudo crear el usuario (sin respuesta o con error silencioso).");
    }
}
}
