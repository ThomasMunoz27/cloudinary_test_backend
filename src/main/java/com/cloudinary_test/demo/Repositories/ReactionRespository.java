package com.cloudinary_test.demo.Repositories;

import com.cloudinary_test.demo.Entities.Enums.ReactionType;
import com.cloudinary_test.demo.Entities.Reaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReactionRespository extends BaseRepository<Reaction, Long> {

    Optional<Reaction> findByUserIdAndImageId(Long userId, Long ImageId);
    Long countByImageIdAndType(Long imageId, ReactionType type);
    @Modifying
    @Transactional
    @Query("DELETE FROM Reaction r WHERE r.image.id = :imageId")
    void deleteByImageId(@Param("imageId")Long imageId);
}
