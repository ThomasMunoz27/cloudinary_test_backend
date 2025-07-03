package com.cloudinary_test.demo.Controllers;

import com.cloudinary_test.demo.DTOs.CommentPostRequest;
import com.cloudinary_test.demo.Entities.Comment;
import com.cloudinary_test.demo.Entities.Image;
import com.cloudinary_test.demo.Services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Comments", description = "Crud de comentarios")
@RestController
@RequestMapping("/comments")
public class CommentController extends BaseController<Comment> {
    public CommentController(CommentService commentService){
        super(commentService);
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
    public ResponseEntity<Comment> postComment(@RequestBody CommentPostRequest request){
        Comment newComment = ((CommentService)baseService).saveComment(request);
        return ResponseEntity.ok(newComment);
    }
}
