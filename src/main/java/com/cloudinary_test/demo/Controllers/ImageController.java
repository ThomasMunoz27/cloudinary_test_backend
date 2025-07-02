package com.cloudinary_test.demo.Controllers;

import com.cloudinary_test.demo.DTOs.ImageUpdateRequest;
import com.cloudinary_test.demo.DTOs.ImageUploadRequest;
import com.cloudinary_test.demo.Entities.Image;
import com.cloudinary_test.demo.Repositories.ImageRepository;
import com.cloudinary_test.demo.Services.CloudinaryService;
import com.cloudinary_test.demo.Services.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Images", description = "Crud de imagenes")
@RestController
@RequestMapping("/images")
@CrossOrigin(origins ="*")
public class ImageController extends BaseController<Image>{

    private final CloudinaryService cloudinaryService;
    public ImageController(ImageService imageService, CloudinaryService cloudinaryService){
        super(imageService);
        this.cloudinaryService = cloudinaryService;
    }
    @Override
    @GetMapping
    @Operation(summary = "Listar todas las imágenes")
    public ResponseEntity<List<Image>> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Obtener imagen por ID")
    public ResponseEntity<Image> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @PostMapping
    @Operation(summary = "Crear nueva imagen")
    public ResponseEntity<Image> post(@RequestBody Image image) {
        return super.post(image);
    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar imagen")
    public ResponseEntity<Image> update(@PathVariable Long id, @RequestBody Image image) {
        return super.update(id, image);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar imagen")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ((ImageService)baseService).deleteImage(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/upload")
    public ResponseEntity<Image> upload(@ModelAttribute ImageUploadRequest request) {
        Image image = ((ImageService)baseService).uploadImage(request);
        return ResponseEntity.ok(image);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Image> updateImage(@PathVariable Long id, @ModelAttribute ImageUpdateRequest request){
        Image updatedImage = ((ImageService)baseService).updateImage(id, request);
        return ResponseEntity.ok(updatedImage);
    }
}
