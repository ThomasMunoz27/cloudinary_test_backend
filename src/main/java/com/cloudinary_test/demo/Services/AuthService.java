package com.cloudinary_test.demo.Services;

import com.cloudinary_test.demo.DTOs.RegisterRequest;
import com.cloudinary_test.demo.Entities.Enums.Privileges;
import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("Email ya registrado");
        }
        if(userRepository.existsByUsername(request.getUsername())){
            throw new IllegalArgumentException("Nombre de usuario en uso");

        }
        User user = new User();

        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); //hasheada
        user.setRegisterDate(LocalDateTime.now());
        user.setPrivileges(Privileges.USER);
        user.setLinkProfileImg("");
        user.setPublicIdProfileImage("");

        return userRepository.save(user);
    }


}
