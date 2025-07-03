package com.xgaslan.blog.domain.mappers;

import com.xgaslan.blog.domain.entities.Post;
import com.xgaslan.blog.domain.models.post.PostCreateModel;
import com.xgaslan.blog.domain.models.post.PostViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface IPostMapper {
    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    PostViewModel toViewModel(Post entity);

    Post toEntity(PostCreateModel model);

    PostCreateModel toCreateModel(Post entity);
}
