package com.xgaslan.blog.domain.models.post;

import com.xgaslan.blog.domain.enums.PostStatus;
import com.xgaslan.blog.domain.models.category.CategoryViewModel;
import com.xgaslan.blog.domain.models.tag.TagViewModel;
import com.xgaslan.blog.domain.models.user.UserViewModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostViewModel {
    private UUID id;

    private String title;

    private String content;

    private UserViewModel author;

    private CategoryViewModel category;

    private Set<TagViewModel> tags;

    private Integer readingTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private PostStatus status;
}
