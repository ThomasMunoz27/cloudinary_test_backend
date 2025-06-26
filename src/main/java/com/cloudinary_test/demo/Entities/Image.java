package com.cloudinary_test.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "images")
@Getter
@Setter
public class Image extends Base {

    @Column(nullable = false)
    private String link;
    @Column(name = "nombre")
    private String name;

    @Column(name = "fecha_subida")
    //private User user
    private LocalDateTime dateUploaded = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
