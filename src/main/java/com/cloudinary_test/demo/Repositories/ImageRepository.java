package com.cloudinary_test.demo.Repositories;

import com.cloudinary_test.demo.Entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends BaseRepository<Image, Long> {

}
