package com.cloudinary_test.demo.DTOs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTOResponse {
    private Long id;
    private String content;
    private Long userId;
    private String username;
    private String linkProfileImg;
    private LocalDateTime createdAt;

    public CommentDTOResponse(Long id, String content, Long userId, String username, String linkProfileImg, LocalDateTime createdAt){
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.username = username;
        this.linkProfileImg = linkProfileImg;
        this.createdAt = createdAt;
    }
}
