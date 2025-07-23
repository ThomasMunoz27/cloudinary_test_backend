package com.cloudinary_test.demo.Services;

import com.cloudinary_test.demo.Entities.Enums.ReactionType;
import com.cloudinary_test.demo.Entities.Image;
import com.cloudinary_test.demo.Entities.Reaction;
import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Repositories.CategoryRepository;
import com.cloudinary_test.demo.Repositories.ImageRepository;
import com.cloudinary_test.demo.Repositories.ReactionRespository;
import com.cloudinary_test.demo.Repositories.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class ReactionService extends BaseService<Reaction> {

    private final ReactionRespository reactionRespository;

    private final ImageService imageService;

    public ReactionService(JpaRepository<Reaction, Long> baseRepository,
                           ReactionRespository reactionRespository,
                           ImageService imageService){
        super(baseRepository);
        this.reactionRespository = reactionRespository;
        this.imageService = imageService;
    }

    public void reactToImage(Long imageId, User user, ReactionType type){
        Image image = imageService.findById(imageId);

        Optional<Reaction> existingReaction = reactionRespository.findByUserIdAndImageId(user.getId(), imageId);

        if(existingReaction.isPresent()){
            Reaction reaction = existingReaction.get();

            if (reaction.getType() == type){
                //Ya reaccionó con ese tipo -> deshacer reacción
                removeReactionCount(image, type);
                reactionRespository.delete(reaction);
            }else{
                //cambiar tipo de reaccion -> actualizar conteos
                removeReactionCount(image, reaction.getType());
                addReactionCount(image, type);
                reaction.setType(type);
                reactionRespository.save(reaction);
            }
        }else{
            //No reaccionó aún -> crear nueva reacción
            Reaction newReaction = new Reaction();
            newReaction.setUser(user);
            newReaction.setImage(image);
            newReaction.setType(type);
            reactionRespository.save(newReaction);

            addReactionCount(image, type);
        }
        imageService.save(image);
    }

    private void addReactionCount(Image image, ReactionType type){
        switch (type){
            case LIKE -> image.setLikes(image.getLikes() + 1);
            case DISKLIKE -> image.setDislike(image.getDislike() + 1);
        }
    }

    private void removeReactionCount(Image image, ReactionType type){
        switch (type){
            case LIKE -> image.setLikes(Math.max(image.getLikes() - 1, 0));
            case DISKLIKE -> image.setDislike(Math.max(image.getDislike() - 1, 0));
        }
    }
}
