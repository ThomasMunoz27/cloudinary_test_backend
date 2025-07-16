package com.cloudinary_test.demo.Controllers;

import com.cloudinary_test.demo.DTOs.AuthResponse;
import com.cloudinary_test.demo.DTOs.LoginRequest;
import com.cloudinary_test.demo.DTOs.RegisterRequest;
import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Services.AuthService;
import com.cloudinary_test.demo.Services.UserService;
import com.cloudinary_test.demo.Utils.JwtUtil;
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
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthService authService, JwtUtil jwtUtil, UserService userService){
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar usuario")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest request){
            User user = authService.register(request);
            return ResponseEntity.status(201).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String identifier = loginRequest.usernameOrEmail();
        String password = loginRequest.password();

        User user;
        if(identifier.contains("@")){
            user = userService.authenticateByEmail(identifier, password);
        }else{
            user = userService.authenticateByUsername(identifier, password);
        }

        String token = jwtUtil.generateToken(user.getId());
        return ResponseEntity.ok(new AuthResponse(token));

    }
}
