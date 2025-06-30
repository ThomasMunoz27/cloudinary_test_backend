package com.cloudinary_test.demo.Entities;

import com.cloudinary_test.demo.Entities.Enums.Privileges;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class User extends Base{

    @Column(name="email", unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="username", unique = true)
    private String username;

    @Column(name="fecha_registro")
    private LocalDateTime registerDate;

    @Column(name="privilegios")
    private Privileges privileges; //admin o usuario
}
