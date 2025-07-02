package com.cloudinary_test.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "images")
@Getter
@Setter
public class Image extends Base {

    @Column(nullable = false)
    private String link;

    @Column(name = "public_id")
    private String publicId;

    @Column(name = "nombre")
    private String name;
    @Column(name = "descripcion")
    private String description;

    @Column(name = "likes", nullable = false)
    private Integer likes = 0;
    @Column(name = "dislikes", nullable = false)
    private Integer dislike = 0;

    @Column(name = "fecha_subida")
    private LocalDateTime dateUploaded;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToMany
    @JoinTable(
            name = "image_categories",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
}
