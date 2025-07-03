package com.cloudinary_test.demo.Services;

import com.cloudinary_test.demo.DTOs.CommentPostRequest;
import com.cloudinary_test.demo.Entities.Comment;
import com.cloudinary_test.demo.Entities.Image;
import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Repositories.CommentRepository;
import com.cloudinary_test.demo.Repositories.ImageRepository;
import com.cloudinary_test.demo.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService extends BaseService<Comment> {
    @Autowired
    private CommentRepository commentRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;


    public CommentService(JpaRepository<Comment, Long> baseRepository,
                          ImageRepository imageRepository,
                          UserRepository userRepository){
        super(baseRepository);
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public Comment saveComment(CommentPostRequest request){
        Comment comment = new Comment();

        comment.setContent(request.getContent());
        comment.setDate(LocalDateTime.now());

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new EntityNotFoundException("Usuario no encontrado"));
        Image image = imageRepository.findById(request.getImageId())
                .orElseThrow(()-> new EntityNotFoundException("Image noencontrada para comentar"));

        comment.setUser(user);
        comment.setImage(image);

        return commentRepository.save(comment);
    }
}
