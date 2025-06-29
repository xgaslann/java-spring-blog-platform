package com.xgaslan.blog.services;

import com.xgaslan.blog.domain.entities.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAll();
}
