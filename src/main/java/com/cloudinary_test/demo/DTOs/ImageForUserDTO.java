package com.cloudinary_test.demo.DTOs;

import com.cloudinary_test.demo.Entities.Category;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ImageForUserDTO {
    private Long id;
    private Integer likes;
    private Integer dislike;
    private LocalDateTime dateUpload;

}
