package com.cloudinary_test.demo.Services;

import java.util.List;

public interface IBaseService <E>{
    List<E> findAll() ;
    E findById(Long id) ;
    E save (E entity) ;
    E update(Long id, E entity) ;
    boolean delete(Long id) ;
}
