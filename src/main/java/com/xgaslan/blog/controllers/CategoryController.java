package com.xgaslan.blog.controllers;

import com.xgaslan.blog.domain.entities.Category;
import com.xgaslan.blog.domain.mappers.ICategoryMapper;
import com.xgaslan.blog.domain.models.category.CategoryViewModel;
import com.xgaslan.blog.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService service;

    private final ICategoryMapper mapper;

    @GetMapping
    public ResponseEntity<List<CategoryViewModel>> getAll(){
        List<CategoryViewModel> categories = service.getAll().stream().map(mapper::toViewModel).toList();
        return ResponseEntity.ok().body(categories);
    }
}
