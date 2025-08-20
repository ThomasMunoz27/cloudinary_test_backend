package com.cloudinary_test.demo.DTOs;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ChangeUserProfileDTO {
    private MultipartFile file;
}
