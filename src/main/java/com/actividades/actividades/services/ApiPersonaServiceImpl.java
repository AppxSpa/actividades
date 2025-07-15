package com.actividades.actividades.services;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.actividades.actividades.config.ApiProperties;
import com.actividades.actividades.dto.ApiPersonaRequest;
import com.actividades.actividades.dto.PersonaResponse;
import com.actividades.actividades.services.interfaces.ApiPersonaService;

import reactor.core.publisher.Mono;

@Service
public class ApiPersonaServiceImpl implements ApiPersonaService {

    private final WebClient webClientPersonas;

    public ApiPersonaServiceImpl(WebClient.Builder webClientBuilder, ApiProperties apiProperties) {
        this.webClientPersonas = webClientBuilder.baseUrl(apiProperties.getPersonaUrl()).build();
    }

    @Override
    public PersonaResponse getPersonaInfo(Integer rut) {
        return webClientPersonas.get()
                .uri("/{rut}", rut)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                .bodyToMono(PersonaResponse.class)
                .onErrorResume(Exception.class, e -> Mono.empty())
                .block();
    }

    @Override
    public PersonaResponse createPersona(ApiPersonaRequest request) {

         return webClientPersonas.post()
                .uri("/create")
                .bodyValue(request)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                .bodyToMono(PersonaResponse.class)
                .onErrorResume(Exception.class, e -> Mono.empty())
                .block();

    }
}
