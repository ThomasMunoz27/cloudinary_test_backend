package com.cloudinary_test.demo.Services;

import com.cloudinary_test.demo.DTOs.ImageUpdateRequest;
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
        image.setPublicId(publicId);
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

    @Transactional
    public Image updateImage(Long id, ImageUpdateRequest request){
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Imagen no encontrada para editar"));
        //si existen valores los reemplaza
        if (request.getName() != null){
            image.setName(request.getName());
        }

        if (request.getDescription() != null){
            image.setDescription(request.getDescription());
        }

        //Si viene un archivo, reemplazo la imagen en Cloudinary
        if(request.getFile() != null && !request.getFile().isEmpty()){
            Map uploadResult = null;
            //se elimina la imagen anterior
            if (image.getPublicId() != null){
                uploadResult = cloudinaryService.updateImage(image.getPublicId(), request.getFile());
            }
            image.setLink(uploadResult.get("secure_url").toString());
            image.setPublicId(uploadResult.get("public_id").toString());
        }

        //Si se envian nuevas  categorias
        if (request.getCategoryIds() != null && !request.getCategoryIds().isEmpty()){
            List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());
            if (categories.isEmpty()){
                throw new EntityNotFoundException("No se encontraron categorias validas");
            }
            image.setCategories(categories);
        }
        return imageRepository.save(image);
    }
}
