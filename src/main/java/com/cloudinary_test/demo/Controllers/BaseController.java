package com.cloudinary_test.demo.Controllers;

import com.cloudinary_test.demo.Entities.Image;
import com.cloudinary_test.demo.Repositories.ImageRepository;
import com.cloudinary_test.demo.Services.BaseService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

public class BaseController<E> {

    protected BaseService<E> baseService;

    public BaseController(BaseService<E> baseService){
        this.baseService = baseService;
    }

    @GetMapping
    public ResponseEntity<List<E>> getAll(){
        try{
            List<E> entities = baseService.findAll();
            return ResponseEntity.ok(entities);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<E> getById (@PathVariable Long id){
        try{
            E entity = baseService.findById(id);
            if (entity != null){
                return ResponseEntity.ok(entity);
            }
            return ResponseEntity.notFound().build();

        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<E> post(@RequestBody E entity){
        try{
            E newEntity = baseService.save(entity);
            return ResponseEntity.status(201).body(newEntity);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<E> update(@PathVariable Long id, @RequestBody E entity){
        try{
            E entityFind = baseService.findById(id);
            System.out.println(entityFind);
            E entityToUpdate = baseService.update(id, entityFind);
            if(entityToUpdate == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(baseService.update(id, entity));
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            if (baseService.delete(id)){
                return ResponseEntity.ok().build();
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }


}
