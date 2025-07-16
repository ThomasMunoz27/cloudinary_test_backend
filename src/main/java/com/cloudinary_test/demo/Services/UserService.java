package com.cloudinary_test.demo.Services;

import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> {

    private final UserRepository userRepository;

    public UserService(JpaRepository<User, Long> baseRepository,
                       UserRepository userRepository){
        super((baseRepository));
        this.userRepository = userRepository;
    }

    public User authenticateByEmail(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> user.getPassword().equals(password))
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas (email)"));
    }

    public User authenticateByUsername(String username, String password){
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals((password)))
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas (usuario)"));
    }
}
