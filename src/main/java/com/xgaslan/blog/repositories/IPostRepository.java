package com.xgaslan.blog.repositories;

import com.xgaslan.blog.domain.entities.Category;
import com.xgaslan.blog.domain.entities.Post;
import com.xgaslan.blog.domain.entities.Tag;
import com.xgaslan.blog.domain.entities.User;
import com.xgaslan.blog.domain.enums.PostStatus;
import com.xgaslan.blog.repositories.base.IBaseRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IPostRepository extends IBaseRepository<Post, UUID> {

    List<Post> findAllByStatusAndCategoryAndTagsContaining(PostStatus status, Category category, Tag tag);

    List<Post> findAllByStatusAndCategory(PostStatus status, Category category);

    List<Post> findAllByStatusAndTagsContaining(PostStatus status, Tag tag);

    List<Post> findAllByStatus(PostStatus status);

    List<Post> findAllByAuthorAndStatus(User author, PostStatus status);

}
