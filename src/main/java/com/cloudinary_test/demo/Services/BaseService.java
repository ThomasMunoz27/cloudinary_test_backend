package com.cloudinary_test.demo.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<E> implements IBaseService<E>{
    protected JpaRepository<E, Long> baseRepository;

    public BaseService(JpaRepository<E, Long> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Transactional
    public List<E> findAll(){
            return baseRepository.findAll();

    }

    @Transactional
    public E findById(Long id) {
            return baseRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Entidad con ID " + id + " no encontrada"));
    }

    @Transactional
    public E save(E entity) {
            return baseRepository.save(entity);
    }

    @Transactional
    public E update(Long id, E entity) {
            if (baseRepository.existsById(id)) {
                return baseRepository.save(entity);
            } else {
                throw new EntityNotFoundException("Entidad con ID " + id + " no encontrada para actualizar");
            }

    }

    @Transactional
    public boolean delete(Long id){
            if (baseRepository.existsById(id)) {
                baseRepository.deleteById(id);
                return true;
            } else {
                return false;
            }

    }
}
