package com.xgaslan.blog.repositories;

import com.xgaslan.blog.domain.entities.Category;
import com.xgaslan.blog.repositories.base.IBaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ICategoryRepository extends IBaseRepository<Category, UUID> {
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.posts")
    List<Category> findAllWithPostCount();

    boolean existsByNameIgnoreCase(String name);
}
