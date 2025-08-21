package com.cloudinary_test.demo.Controllers;

import com.cloudinary_test.demo.DTOs.CommentDTOResponse;
import com.cloudinary_test.demo.DTOs.CommentPostRequest;
import com.cloudinary_test.demo.Entities.Comment;

import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Services.CommentService;

import com.cloudinary_test.demo.Utils.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Comments", description = "Crud de comentarios")
@RestController
@RequestMapping("/comments")
public class CommentController extends BaseController<Comment> {

    private final CommentService commentService;
    public CommentController(CommentService commentService, CommentService commentService1){
        super(commentService);
        this.commentService = commentService1;
    }

    @Override
    @GetMapping
    @Operation(summary = "Listar todos los comentarios")
    public ResponseEntity<List<Comment>> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Obtener comentario por ID")
    public ResponseEntity<Comment> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @PostMapping
    @Operation(summary = "Crear nuevo comentario")
    public ResponseEntity<Comment> post(@RequestBody Comment comment) {
        comment.setDate(LocalDateTime.now());
        return super.post(comment);
    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar comentario")
    public ResponseEntity<Comment> update(@PathVariable Long id, @RequestBody Comment comment) {
        return super.update(id, comment);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar comentario")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }


    @PostMapping("/post")
    public ResponseEntity<CommentDTOResponse> postComment(@RequestBody CommentPostRequest request, @AuthenticationPrincipal CustomUserDetails userDetails){

        User user = userDetails.getUser();

        CommentDTOResponse newComment = ((CommentService)baseService).saveComment(request, user);
        return ResponseEntity.ok(newComment);
    }

    @GetMapping("/image/{imageId}")
    public ResponseEntity<List<CommentDTOResponse>> getCommentsByImageId(@PathVariable Long imageId){
        System.out.println("Buscando comentarios para imagen ID: " + imageId);

        return ResponseEntity.ok(commentService.getCommentsByImageId(imageId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserComment (@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails){
        User user = userDetails.getUser();
        try {
            commentService.deleteComment(id, user);
            return ResponseEntity.noContent().build(); // 204
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Comment not found")) {
                return ResponseEntity.notFound().build(); // 404
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403
        }
    }
}
