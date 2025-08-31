package com.cloudinary_test.demo.DTOs;

import com.cloudinary_test.demo.Entities.Enums.Privileges;
import lombok.Data;
import org.springframework.data.domain.Page;


import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDTOResponse {
    private Long id;
    private String username;
    private LocalDateTime registerDate;
    private String publicIdProfileImg;
    private String linkProfileImg;
    private Integer cantImagesPublished;
    private Privileges privileges;
}
