package com.cloudinary_test.demo.Services;

import com.cloudinary_test.demo.Entities.Category;
import com.cloudinary_test.demo.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseService<Category>{

     @Autowired
    private CategoryRepository categoryRepository;

     public CategoryService(JpaRepository<Category, Long> baseRepository){
         super(baseRepository);
     }
}
