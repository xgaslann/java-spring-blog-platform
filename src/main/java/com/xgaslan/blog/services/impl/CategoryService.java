package com.xgaslan.blog.services.impl;

import com.xgaslan.blog.domain.entities.Category;
import com.xgaslan.blog.repositories.ICategoryRepository;
import com.xgaslan.blog.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final ICategoryRepository repository;

    @Override
    public List<Category> getAll() {
        return repository.findAllWithPostCount();
    }
}
