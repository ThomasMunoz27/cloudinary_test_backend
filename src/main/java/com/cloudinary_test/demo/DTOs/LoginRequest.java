package com.cloudinary_test.demo.DTOs;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Debe ingresar nombre de usuario o email")
        String usernameOrEmail,
        @NotBlank(message = "La contraseña es obligatoria")
        String password) {

}
