package com.cloudinary_test.demo.Controllers;

import com.cloudinary_test.demo.Entities.Category;
import com.cloudinary_test.demo.Services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Categories", description = "Crud de categorias")
@RestController
@RequestMapping("/categories")
public class CategoryController extends BaseController<Category> {

    public CategoryController(CategoryService categoryService){
        super(categoryService);
    }

    @Override
    @GetMapping
    @Operation(summary = "Listar todas las categorias")
    public ResponseEntity<List<Category>> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Obtener categoria por ID")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @PostMapping
    @Operation(summary = "Crear nueva categoria")
    public ResponseEntity<Category> post(@RequestBody Category category) {
        return super.post(category);
    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar categoria")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        return super.update(id, category);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar categoria")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }

}
