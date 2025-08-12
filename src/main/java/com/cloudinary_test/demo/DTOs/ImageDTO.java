package com.cloudinary_test.demo.DTOs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageDTO {
    private String link;
    private String publicId;
    private String name;
    private String description;
    private Integer likes;
    private Integer dislike;
    private LocalDateTime dateUploaded;
}
