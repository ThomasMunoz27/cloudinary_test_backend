package com.cloudinary_test.demo.Repositories;

import com.cloudinary_test.demo.Entities.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
