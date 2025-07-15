package com.actividades.actividades.config;

import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Component
@ConfigurationProperties(prefix = "api")
public class ApiProperties {

    private String personaUrl;

    private String direccionUrl;

    private String loginUrl;

    public String getPersonaUrl() {
        return personaUrl;
    }

    public void setPersonaUrl(String personaUrl) {
        this.personaUrl = personaUrl;
    }

    public String getDireccionUrl() {
        return direccionUrl;
    }

    public void setDireccionUrl(String direccionUrl) {
        this.direccionUrl = direccionUrl;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

}
