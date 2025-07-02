package com.cloudinary_test.demo.Services;

import com.cloudinary_test.demo.DTOs.ImageUploadRequest;
import com.cloudinary_test.demo.Entities.Category;
import com.cloudinary_test.demo.Entities.Image;
import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Repositories.CategoryRepository;
import com.cloudinary_test.demo.Repositories.ImageRepository;
import com.cloudinary_test.demo.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ImageService extends BaseService<Image> {
    private final ImageRepository imageRepository;
    private final CloudinaryService cloudinaryService;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ImageService(JpaRepository<Image, Long> baseRepository,
                        ImageRepository imageRepository,
                        CloudinaryService cloudinaryService,
                        UserRepository userRepository,
                        CategoryRepository categoryRepository){
        super(baseRepository);
        this.imageRepository = imageRepository;
        this.cloudinaryService = cloudinaryService;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }
    @Transactional
    public Image uploadImage(ImageUploadRequest request){
        Map uploadResult = cloudinaryService.uploadImage(request.getFile());
        String imageUrl = uploadResult.get("secure_url").toString();
        String publicId = uploadResult.get("public_id").toString();
        Image image = new Image();

        image.setLink(imageUrl);
        image.setPublic_id(publicId);
        image.setName(request.getName());
        image.setDescription(request.getDescription());
        image.setDateUploaded(LocalDateTime.now());
        image.setLikes(0);
        image.setDislike(0);

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

       List<Category> categories = categoryRepository.findAllById(request.getCategoryId());
       if (categories.isEmpty()){
           throw new EntityNotFoundException("No se encotraron categorias válidas");
       }


        image.setUserId(user);
        image.setCategories(categories);

        return imageRepository.save(image);

    }
}
