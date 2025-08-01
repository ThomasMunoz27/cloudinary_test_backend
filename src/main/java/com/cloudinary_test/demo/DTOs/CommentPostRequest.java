package com.cloudinary_test.demo.DTOs;

import lombok.Data;

@Data
public class CommentPostRequest {
    private String content;
    private Long imageId;
}
