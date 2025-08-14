package com.cloudinary_test.demo.DTOs;

import com.cloudinary_test.demo.Entities.Category;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ImagePageDTO {
    private Long id;
    private String publicId;
    private String link;
    private String name;
    private String description;
    private Integer likes;
    private Integer dislike;
    private LocalDateTime dateUpload;
    private UserForImageDTO userId;
    private List<Category> categories;
}
