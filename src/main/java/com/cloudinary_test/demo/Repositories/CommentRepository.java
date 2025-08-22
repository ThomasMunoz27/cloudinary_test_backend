package com.cloudinary_test.demo.Repositories;

import com.cloudinary_test.demo.Entities.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends BaseRepository<Comment, Long> {

    List<Comment> findAllByImageId(Long imageId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Comment c WHERE c.image.id = :imageId")
    void deleteByImageId(@Param("imageId") Long imageId);
}
