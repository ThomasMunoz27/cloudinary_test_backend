package com.cloudinary_test.demo.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Comment extends Base{
    @Column(name="contenido", nullable = false)
    private String content;

    @Column(name="user_id", nullable = false)
    private Long user_id;

    @Column(name="image_id", nullable = false)
    private Long image_id;

    @Column(name="fecha", nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;
}
