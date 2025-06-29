package com.xgaslan.blog.domain.mappers.impl;

import com.xgaslan.blog.domain.entities.Category;
import com.xgaslan.blog.domain.mappers.ICategoryMapper;
import com.xgaslan.blog.domain.models.category.CategoryCreateModel;
import com.xgaslan.blog.domain.models.category.CategoryUpdateModel;
import com.xgaslan.blog.domain.models.category.CategoryViewModel;
import org.mapstruct.Mapper;

public class CategoryMapper implements ICategoryMapper {

    @Override
    public CategoryViewModel toViewModel(Category category) {
        return CategoryViewModel.builder()
                .id(category.getId())
                .name(category.getName())
                .postCount(category.getPosts().size())
                .build();
    }

    @Override
    public Category toEntity(CategoryCreateModel model) {
        return null;
    }

    @Override
    public Category toEntity(CategoryUpdateModel model, Category category) {
        return null;
    }

    @Override
    public CategoryViewModel createModelToViewModel(CategoryCreateModel model) {
        return null;
    }

    @Override
    public CategoryViewModel updateModelToViewModel(CategoryUpdateModel model, Category category) {
        return null;
    }
}
