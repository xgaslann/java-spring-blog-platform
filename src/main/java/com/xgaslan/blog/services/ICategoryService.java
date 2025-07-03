package com.xgaslan.blog.services;

import com.xgaslan.blog.domain.entities.Category;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {
    List<Category> getAll();

    Category create(Category entity);

    Category getById(UUID id);

    void delete(UUID  id);
}
