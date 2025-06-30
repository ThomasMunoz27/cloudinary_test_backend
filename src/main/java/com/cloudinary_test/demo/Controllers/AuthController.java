package com.cloudinary_test.demo.Controllers;

import com.cloudinary_test.demo.DTOs.RegisterRequest;
import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@Tag(name = "Auth", description = "Auth para registro y login")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar usuario")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest request){
            User user = authService.register(request);
            return ResponseEntity.status(201).body(user);
    }
}
