package com.cloudinary_test.demo.Repositories;

import com.cloudinary_test.demo.Entities.Comment;

import java.util.List;

public interface CommentRepository extends BaseRepository<Comment, Long> {

    List<Comment> findAllByImageId(Long imageId);
}
