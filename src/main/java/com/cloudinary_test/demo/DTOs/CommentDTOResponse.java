package com.cloudinary_test.demo.DTOs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTOResponse {
    private Long id;
    private String content;
    private Long userId;
    private String username;
    private LocalDateTime createdAt;

    public CommentDTOResponse(Long id, String content, Long userId, String username, LocalDateTime createdAt){
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.username = username;
        this.createdAt = createdAt;
    }
}
