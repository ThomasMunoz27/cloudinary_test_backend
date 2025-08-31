package com.cloudinary_test.demo.Services;

import com.cloudinary_test.demo.DTOs.ImagePageDTO;
import com.cloudinary_test.demo.DTOs.UserDTOResponse;
import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Repositories.ImageRepository;
import com.cloudinary_test.demo.Repositories.UserRepository;
import com.cloudinary_test.demo.mappers.ImageMapper;
import com.cloudinary_test.demo.mappers.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class UserService extends BaseService<User> {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final CloudinaryService cloudinaryService;
    private final UserMapper userMapper;
    private final ImageMapper imageMapper;
    private final PasswordEncoder passwordEncoder;
    public UserService(JpaRepository<User, Long> baseRepository,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       ImageRepository imageRepository,
                       CloudinaryService cloudinaryService,
                       ImageMapper imageMapper,
                       UserMapper userMapper){
        super((baseRepository));
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
        this.cloudinaryService = cloudinaryService;
        this.userMapper= userMapper;
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

    public UserDTOResponse getProfileById(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new EntityNotFoundException("Usuario no encontrado"));

        return userMapper.toUserDTOResponse(user);
    }


    public Page<ImagePageDTO> getPagedImagesByUser(Long userId, Pageable pageable){

        return imageRepository.findByUserId(userId, pageable)
                .map(imageMapper::toImagePageDTO);
    }

    public User updateProfilePhoto(User user, MultipartFile file){
        Map uploadResult;

        if (user.getPublicIdProfileImage() != null){
            //actualizar imagen existente
            uploadResult = cloudinaryService.updateImage(user.getPublicIdProfileImage(), file);
        }else{
            //subir nueva imagen
            uploadResult = cloudinaryService.uploadImage(file);
        }

        String publicId = (String) uploadResult.get("public_id");
        String url = (String) uploadResult.get("secure_url");

        user.setPublicIdProfileImage(publicId);
        user.setLinkProfileImg(url);

        return userRepository.save(user);
    }
}
