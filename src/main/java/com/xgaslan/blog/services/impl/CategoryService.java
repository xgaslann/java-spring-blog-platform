package com.xgaslan.blog.services.impl;

import com.xgaslan.blog.domain.entities.Category;
import com.xgaslan.blog.repositories.ICategoryRepository;
import com.xgaslan.blog.services.ICategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final ICategoryRepository repository;

    @Override
    public List<Category> getAll() {
        var all = repository.findAllWithPostCount();
        return all;
    }

    @Override
    @Transactional
    public Category create(Category category) {
        if (repository.existsByNameIgnoreCase(category.getName()))
            throw new IllegalArgumentException("Category already exists with name: " + category.getName());

        return repository.save(category);
    }

    @Override
    public void delete(UUID id) {
        Optional<Category> category = repository.findById(id);
        if (category.isPresent() && !category.get().isDeleted()) {
            if(!category.get().getPosts().isEmpty()){
                throw new IllegalStateException("Cannot delete category with existing posts");
            }
            repository.deleteById(id);
        }
    }
}
