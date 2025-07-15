package com.actividades.actividades.utils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.actividades.actividades.config.JwtProperties;

@Component
public class JwtUtils {

    private final SecretKey secretKey;

    public JwtUtils(JwtProperties jwtProperties) {
        this.secretKey = new SecretKeySpec(jwtProperties.getSecret().getBytes(), "HmacSHA256");
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }
}
