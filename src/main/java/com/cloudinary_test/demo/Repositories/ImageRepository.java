package com.cloudinary_test.demo.Repositories;

import com.cloudinary_test.demo.Entities.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends BaseRepository<Image, Long> {
    @Query("""
            SELECT DISTINCT i FROM Image i
            JOIN i.categories c
            WHERE (:categoryId is NUll OR c.id = :categoryId)
            """)
    Page<Image> findByCategoryId(@Param("categoryId")Long categoryId, Pageable pageable);

    @Query("SELECT i FROM Image i WHERE i.userId.id = :userId")
    Page<Image> findByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT i FROM Image i WHERE i.userId.id = :userId")
    List<Image> findAllByUserId(@Param("userId") Long userId);
}
