package com.cloudinary_test.demo.DTOs;

import com.cloudinary_test.demo.Entities.Enums.ReactionType;
import com.cloudinary_test.demo.Entities.Reaction;
import lombok.Data;

@Data
public class ReactionDTOResponse {
    private Long id;
    private ReactionType reactionType;

    private Long imageId;
    private int likes;
    private int dislikes;

    public ReactionDTOResponse() {
        // Constructor vacío para poder crear el objeto sin pasar un Reaction
    }
    public ReactionDTOResponse(Reaction reaction, int incomingLikes, int incomingDislikes){
        this.id = reaction.getId();
        this.reactionType = reaction.getType();
        this.imageId = reaction.getImage().getId();
        this.likes = incomingLikes;
        this.dislikes = incomingDislikes;
    }
}
