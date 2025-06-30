package com.cloudinary_test.demo.Controllers;

import com.cloudinary_test.demo.Entities.Image;
import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Crud de usuarios")
@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User>{
    public UserController(UserService userService){
        super(userService);
    }

    @Override
    @GetMapping
    @Operation(summary = "Listar todos los usuarios")
    public ResponseEntity<List<User>> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @PostMapping
    @Operation(summary = "Crear nuevo usuario")
    public ResponseEntity<User> post(@RequestBody User user) {
        return super.post(user);
    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        return super.update(id, user);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }

}
