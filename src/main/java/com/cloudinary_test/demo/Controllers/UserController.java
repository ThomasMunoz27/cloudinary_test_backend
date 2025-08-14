package com.cloudinary_test.demo.Controllers;

import com.cloudinary_test.demo.DTOs.ImageForUserDTO;
import com.cloudinary_test.demo.DTOs.ImagePageDTO;
import com.cloudinary_test.demo.DTOs.UserDTOResponse;
import com.cloudinary_test.demo.Entities.Image;
import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Services.UserService;
import com.cloudinary_test.demo.Utils.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Crud de usuarios")
@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User>{

    private final UserService userService;
    public UserController(UserService userService){
        super(userService);
        this.userService = userService;
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

    //Endpoint para el perfil del usuario proporcionado
    @GetMapping("/profile/{id}")
    public ResponseEntity<UserDTOResponse> getUserProfileById(@PathVariable Long id){

        UserDTOResponse dtoUser = userService.getProfileById(id);
        return ResponseEntity.ok(dtoUser);
    }

    //Endpoint para las imagenes paged del usuario proporcionado
    @GetMapping("/profile/images/{id}")
    public Page<ImagePageDTO> getPagedImagesByUserId(@PathVariable Long id,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size,
                                                        @RequestParam(defaultValue = "id") String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

        return userService.getPagedImagesByUser(id, pageable);
    }

    //Endpoint para el perfil del usuario Logueado
    @GetMapping("/login/profile")
    public ResponseEntity<UserDTOResponse> getUserProfileByToken(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        UserDTOResponse dtoUser = userService.getProfileById(userId);
        return ResponseEntity.ok(dtoUser);
    }

    //Endpoint para las imagenes paged del usuario logueado
    @GetMapping("/login/profile/images")
    public Page<ImagePageDTO> getPagedImagesByUserToken (@AuthenticationPrincipal CustomUserDetails userDetails,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size,
                                                            @RequestParam(defaultValue = "id") String sortBy){
        Long userId = userDetails.getUser().getId();
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

        return userService.getPagedImagesByUser(userId, pageable);


    }
}
