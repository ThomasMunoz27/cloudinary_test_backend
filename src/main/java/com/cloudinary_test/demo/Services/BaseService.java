package com.cloudinary_test.demo.Services;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class BaseService<E> {
    protected JpaRepository<E, Long> baseRepository;

    public BaseService(JpaRepository<E, Long> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Transactional
    public List<E> findAll() throws Exception {
        try {
            return baseRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public E findById(Long id) throws Exception {
        try {
            Optional<E> entity = baseRepository.findById(id);
            if (entity.isPresent()) {
                return entity.get();
            } else {
                throw new Exception("Entidad no encontrada");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public E save(E entity) throws Exception {
        try {
            return baseRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public E update(Long id, E entity) throws Exception {
        try {
            if (baseRepository.existsById(id)) {
                return baseRepository.save(entity);
            } else {
                throw new Exception("Entidad no encontrada para actualizar");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public boolean delete(Long id) throws Exception {
        try {
            if (baseRepository.existsById(id)) {
                baseRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
