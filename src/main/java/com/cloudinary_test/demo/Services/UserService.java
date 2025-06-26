package com.cloudinary_test.demo.Services;

import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserRepository userRepository;

    public UserService(JpaRepository<User, Long> baseRepository){
        super((baseRepository));
    }
}
