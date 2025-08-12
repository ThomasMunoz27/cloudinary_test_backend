package com.cloudinary_test.demo.Services;

import com.cloudinary_test.demo.DTOs.ImageDTO;
import com.cloudinary_test.demo.DTOs.UserDTOResponse;
import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService<User> {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    public UserService(JpaRepository<User, Long> baseRepository,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder){
        super((baseRepository));
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
        dto.setUsername(user.getUsername());
        dto.setRegisterDate(user.getRegisterDate());
        dto.setPublicIdProfileImg(user.getPublicIdProfileImage());
        dto.setLinkProfileImg(user.getLinkProfileImg());

        List<ImageDTO> imageDTOList = user.getImagesPublished()
                .stream()
                .map(img -> {
                    ImageDTO imgDTO = new ImageDTO();
                    imgDTO.setLink(img.getLink());
                    imgDTO.setPublicId(img.getPublicId());
                    imgDTO.setName(img.getName());
                    imgDTO.setLikes(img.getLikes());
                    imgDTO.setDislike(img.getDislike());
                    imgDTO.setDateUploaded(img.getDateUploaded());
                    return imgDTO;
                })
                .toList();
        dto.setImagesPublished(imageDTOList);

        return dto;
    }
}
