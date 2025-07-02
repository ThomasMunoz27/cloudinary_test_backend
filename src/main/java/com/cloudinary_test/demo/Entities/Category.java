package com.cloudinary_test.demo.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Category extends Base{

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @ManyToMany
    private List<Image> images = new ArrayList<>();
}
