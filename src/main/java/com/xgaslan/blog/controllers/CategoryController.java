package com.xgaslan.blog.controllers;

import com.xgaslan.blog.domain.entities.Category;
import com.xgaslan.blog.domain.mappers.ICategoryMapper;
import com.xgaslan.blog.domain.models.category.CategoryCreateModel;
import com.xgaslan.blog.domain.models.category.CategoryViewModel;
import com.xgaslan.blog.services.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService service;

    private final ICategoryMapper mapper;

    @GetMapping
    public ResponseEntity<List<CategoryViewModel>> getAll(){
        List<CategoryViewModel> categories = service.getAll().stream().map(mapper::toViewModel).toList();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryViewModel> create(@Valid @RequestBody CategoryCreateModel model) {
        Category category = mapper.toEntity(model);
        Category createdCategory = service.create(category);
        return new ResponseEntity<>(mapper.toViewModel(createdCategory), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
