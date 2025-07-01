package com.cloudinary_test.demo.DTOs;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadRequest {
    private MultipartFile file;
    private String name;
    private String description;
    private Long userId;
    private Long categoryId;
}
