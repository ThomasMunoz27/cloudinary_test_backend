package com.cloudinary_test.demo.Services;

import com.cloudinary_test.demo.DTOs.*;
import com.cloudinary_test.demo.Entities.Category;
import com.cloudinary_test.demo.Entities.Image;
import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Repositories.*;
import com.cloudinary_test.demo.mappers.ImageMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final ImageMapper imageMapper;
    private final ReactionRespository reactionRespository;
    private final CommentRepository commentRepository;

    public ImageService(JpaRepository<Image, Long> baseRepository,
                        ImageRepository imageRepository,
                        CloudinaryService cloudinaryService,
                        UserRepository userRepository,
                        CategoryRepository categoryRepository,
                        ImageMapper imageMapper,
                        ReactionRespository reactionRespository,
                        CommentRepository commentRepository){
        super(baseRepository);
        this.imageRepository = imageRepository;
        this.cloudinaryService = cloudinaryService;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.imageMapper = imageMapper;
        this.reactionRespository = reactionRespository;
        this.commentRepository = commentRepository;
    }
    @Transactional
    public Image uploadImage(ImageUploadRequest request, Long userId){
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

        User user = userRepository.findById(userId)
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


    @Transactional
    public void deleteImage(Long id){
        Image image = imageRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("Imagen no  encontrada para eliminar"));

        //Eliminar de cloudinary
        if (image.getPublicId() != null){
            cloudinaryService.deleteImage(image.getPublicId());
        }
        commentRepository.deleteByImageId(id);

        reactionRespository.deleteByImageId(id);

        imageRepository.deleteById(id);
    }


    public Page<ImagePageDTO> findAllPaged(Pageable pageable){
        Page<Image> images;

        images = imageRepository.findAll(pageable);

        //mapeo de image ->ImagePageDto
        return images.map(imageMapper::toImagePageDTO);
    }

    public Page<ImagePageDTO> findPagedAndFiltered(Long categoryId, Pageable pageable){
        Page<Image> images;
        if (categoryId != null){
            images = imageRepository.findByCategoryId(categoryId, pageable);
        }else{
            images = imageRepository.findAll(pageable);
        }

        //mapeo de image ->ImagePageDto
        return images.map(image -> {
            ImagePageDTO imgDto = new ImagePageDTO();
            imgDto.setId(image.getId());
            imgDto.setPublicId(image.getPublicId());
            imgDto.setLink(image.getLink());
            imgDto.setName(image.getName());
            imgDto.setDescription(image.getDescription());
            imgDto.setLikes(image.getLikes());
            imgDto.setDislike(image.getDislike());
            imgDto.setDateUpload(image.getDateUploaded());


            UserForImageDTO userDto = new UserForImageDTO();
            userDto.setId(image.getUserId().getId());
            userDto.setUsername(image.getUserId().getUsername());
            userDto.setLinkProfileImg(image.getUserId().getLinkProfileImg());

            imgDto.setUserId(userDto);
            imgDto.setCategories(image.getCategories());

            return imgDto;
        });
    }

    public List<ImageForUserDTO> getImagesForUserStats(Long userId){
        return imageRepository.findAllByUserId(userId)
                .stream()
                .map(imageMapper::toImageForUserDTO)
                .toList();
    }
}
