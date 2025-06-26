package com.cloudinary_test.demo.Services;

import com.cloudinary_test.demo.Entities.Image;
import com.cloudinary_test.demo.Repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService extends BaseService<Image> {
    @Autowired
    private ImageRepository imageRepository;

    public ImageService(JpaRepository<Image, Long> baseRepository){
        super(baseRepository);
    }
}
