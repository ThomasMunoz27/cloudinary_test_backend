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
                reactionRespository.delete(reaction);
            }else{
                //cambiar tipo de reaccion
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
        }
    }
}
