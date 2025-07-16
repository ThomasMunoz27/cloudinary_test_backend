package com.cloudinary_test.demo.Repositories;

import com.cloudinary_test.demo.Entities.Enums.ReactionType;
import com.cloudinary_test.demo.Entities.Reaction;

import java.util.Optional;

public interface ReactionRespository extends BaseRepository<Reaction, Long> {

    Optional<Reaction> findByUserIdAndImageId(Long userId, Long ImageId);
    Long countByImageIdAndType(Long imageId, ReactionType type);
}
