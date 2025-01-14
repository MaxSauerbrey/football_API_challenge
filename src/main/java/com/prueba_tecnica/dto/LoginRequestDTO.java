package com.prueba_tecnica.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class LoginRequestDTO {
        @Schema(description = "Nombre de usuario para autenticación", example = "test")
        private String username;
        @Schema(description = "Contraseña para autenticación", example = "12345")
        private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
