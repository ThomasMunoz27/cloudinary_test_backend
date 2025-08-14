package com.cloudinary_test.demo.Services;

import com.cloudinary_test.demo.DTOs.ImageForUserDTO;
import com.cloudinary_test.demo.DTOs.ImagePageDTO;
import com.cloudinary_test.demo.DTOs.UserDTOResponse;
import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Repositories.ImageRepository;
import com.cloudinary_test.demo.Repositories.UserRepository;
import com.cloudinary_test.demo.mappers.ImageMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService<User> {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    private final ImageMapper imageMapper;
    private final PasswordEncoder passwordEncoder;
    public UserService(JpaRepository<User, Long> baseRepository,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       ImageRepository imageRepository,
                       ImageMapper imageMapper){
        super((baseRepository));
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
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

        UserDTOResponse dto = new UserDTOResponse();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRegisterDate(user.getRegisterDate());
        dto.setPublicIdProfileImg(user.getPublicIdProfileImage());
        dto.setLinkProfileImg(user.getLinkProfileImg());

        return dto;
    }


    public Page<ImagePageDTO> getPagedImagesByUser(Long userId, Pageable pageable){

        return imageRepository.findByUserId(userId, pageable)
                .map(imageMapper::toImagePageDTO);
    }
}
