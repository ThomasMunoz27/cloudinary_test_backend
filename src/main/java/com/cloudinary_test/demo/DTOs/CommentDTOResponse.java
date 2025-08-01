package com.cloudinary_test.demo.DTOs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTOResponse {
    private Long id;
    private String content;
    private String username;
    private LocalDateTime createdAt;

    public CommentDTOResponse(Long id, String content, String username, LocalDateTime createdAt){
        this.id = id;
        this.content = content;
        this.username = username;
        this.createdAt = createdAt;
    }
}
