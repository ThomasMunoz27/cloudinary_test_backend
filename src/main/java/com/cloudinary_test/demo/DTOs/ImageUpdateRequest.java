package com.cloudinary_test.demo.DTOs;

import com.cloudinary_test.demo.Entities.Category;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ImageUpdateRequest {
    private String name;
    private String description;
    private MultipartFile  file;
    private List<Long> categoryIds;
}
