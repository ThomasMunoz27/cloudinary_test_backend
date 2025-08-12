package com.cloudinary_test.demo.DTOs;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDTOResponse {
    private String username;
    private LocalDateTime registerDate;
    private List<ImageDTO> imagesPublished;
    private String publicIdProfileImg;
    private String linkProfileImg;
}
