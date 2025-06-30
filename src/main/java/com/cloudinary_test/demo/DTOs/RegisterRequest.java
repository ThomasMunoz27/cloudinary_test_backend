package com.cloudinary_test.demo.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message =  "El  email no puede esta vacio")
    @Email(message = "El email no es válido")
    private String email;

    @NotBlank(message =  "Se requiere  un nombre de  usuario")
    private String username;

    @NotBlank(message =  "La contraseña no puede esta vacio")
    private String password;
}
