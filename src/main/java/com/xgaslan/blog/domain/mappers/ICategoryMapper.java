package com.xgaslan.blog.domain.mappers;

import com.xgaslan.blog.domain.entities.Category;
import com.xgaslan.blog.domain.entities.Post;
import com.xgaslan.blog.domain.enums.PostStatus;
import com.xgaslan.blog.domain.models.category.CategoryCreateModel;
import com.xgaslan.blog.domain.models.category.CategoryUpdateModel;
import com.xgaslan.blog.domain.models.category.CategoryViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ICategoryMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryViewModel toViewModel(Category entity);

    Category toEntity(CategoryCreateModel model);

    Category toEntity(CategoryUpdateModel model, Category entity);

    CategoryViewModel createModelToViewModel(CategoryCreateModel model);

    CategoryViewModel updateModelToViewModel(CategoryUpdateModel model, Category entity);

    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts) {
        return posts != null ? posts.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus())).count() : 0;
    }
}