package com.cloudinary_test.demo.Repositories;

import com.cloudinary_test.demo.Entities.User;

public interface UserRepository extends BaseRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
