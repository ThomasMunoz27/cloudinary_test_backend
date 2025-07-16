package com.cloudinary_test.demo.Entities;

import com.cloudinary_test.demo.Entities.Enums.ReactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Reaction extends Base{

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReactionType type; //like o dislike
}
