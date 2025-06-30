package com.cloudinary_test.demo.Services;

import com.cloudinary_test.demo.DTOs.RegisterRequest;
import com.cloudinary_test.demo.Entities.Enums.Privileges;
import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
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
        user.setPassword(request.getPassword()); //despues hay que hashearla
        user.setRegisterDate(LocalDateTime.now());
        user.setPrivileges(Privileges.USER);

        return userRepository.save(user);
    }
}
