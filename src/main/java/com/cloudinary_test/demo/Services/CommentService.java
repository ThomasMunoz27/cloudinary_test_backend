package com.cloudinary_test.demo.Services;

import com.cloudinary_test.demo.DTOs.CommentPostRequest;
import com.cloudinary_test.demo.DTOs.CommentDTOResponse;
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
import java.util.List;

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
    public Comment saveComment(CommentPostRequest request, User user){
        Comment comment = new Comment();

        comment.setContent(request.getContent());
        comment.setDate(LocalDateTime.now());


        Image image = imageRepository.findById(request.getImageId())
                .orElseThrow(()-> new EntityNotFoundException("Image no encontrada para comentar"));

        comment.setUser(user);
        comment.setImage(image);

        return commentRepository.save(comment);
    }

    public List<CommentDTOResponse> getCommentsByImageId(Long imageId) {
        System.out.println("Obteniendo comentarios de imagen: " + imageId);

        List<Comment> comments = commentRepository.findAllByImageId(imageId);
        System.out.println("Comentarios encontrados: " + comments.size());

        return comments.stream()
                .map(comment -> new CommentDTOResponse(
                        comment.getId(),
                        comment.getContent(),
                        comment.getUser().getUsername(),
                        comment.getDate()
                ))
                .toList();
    }
}
