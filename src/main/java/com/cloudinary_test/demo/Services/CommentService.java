package com.cloudinary_test.demo.Services;

import com.cloudinary_test.demo.Entities.Comment;
import com.cloudinary_test.demo.Repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends BaseService<Comment> {
    @Autowired
    private CommentRepository commentRepository;

    public CommentService(JpaRepository<Comment, Long> baseRepository){
        super(baseRepository);
    }
}
