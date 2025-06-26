package com.cloudinary_test.demo.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Category extends Base{
    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;
}
