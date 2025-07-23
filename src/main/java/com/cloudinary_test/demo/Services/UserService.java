package com.cloudinary_test.demo.Services;

import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    public UserService(JpaRepository<User, Long> baseRepository,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder){
        super((baseRepository));
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticateByEmail(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas (email)"));
    }

    public User authenticateByUsername(String username, String password){
        return userRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas (usuario)"));
    }
}
