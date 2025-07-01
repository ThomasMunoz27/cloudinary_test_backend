package com.cloudinary_test.demo.Controllers;


import com.cloudinary_test.demo.Services.BaseService;

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
            List<E> entities = baseService.findAll();
            return ResponseEntity.ok(entities);

    }

    @GetMapping("/{id}")
    public ResponseEntity<E> getById (@PathVariable Long id){
            E entity = baseService.findById(id);
            return ResponseEntity.ok(entity);
    }

    @PostMapping
    public ResponseEntity<E> post(@RequestBody E entity){
            E newEntity = baseService.save(entity);
            return ResponseEntity.status(201).body(newEntity);

    }

    @PutMapping("/{id}")
    public ResponseEntity<E> update(@PathVariable Long id, @RequestBody E entity){
        E entityUpdated = baseService.update(id, entity);
            return ResponseEntity.ok(entityUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
            if (baseService.delete(id)){
                return ResponseEntity.ok().build();
            }else {
                return ResponseEntity.notFound().build();
            }
    }


}
