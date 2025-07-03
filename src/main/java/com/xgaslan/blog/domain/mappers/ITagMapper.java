package com.xgaslan.blog.domain.mappers;

import com.xgaslan.blog.domain.entities.Category;
import com.xgaslan.blog.domain.entities.Post;
import com.xgaslan.blog.domain.entities.Tag;
import com.xgaslan.blog.domain.enums.PostStatus;
import com.xgaslan.blog.domain.models.category.CategoryCreateModel;
import com.xgaslan.blog.domain.models.category.CategoryUpdateModel;
import com.xgaslan.blog.domain.models.tag.TagCreateModel;
import com.xgaslan.blog.domain.models.tag.TagViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ITagMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    TagViewModel toViewModel(Tag entity);

    @Named("calculatePostCount")
    default Integer calculatePostCount(Set<Post> posts) {
        if (posts == null) {
            return 0;
        }
        return (int) posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }

    Tag toEntity(TagCreateModel model);

//    Tag toEntity(TagUpdateModel model, Tag entity);
}