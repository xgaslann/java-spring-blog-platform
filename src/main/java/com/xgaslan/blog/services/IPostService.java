package com.xgaslan.blog.services;

import com.xgaslan.blog.domain.entities.Post;
import com.xgaslan.blog.domain.entities.User;
import com.xgaslan.blog.domain.models.post.PostCreateModel;
import com.xgaslan.blog.domain.models.post.PostUpdateModel;

import java.util.List;
import java.util.UUID;

public interface IPostService {
    Post getById(UUID id,  User user);

    List<Post> getAll(UUID categoryId, UUID tagId);

    List<Post> getDrafts(User user);

    Post create(PostCreateModel model, User user);

    Post update(UUID id, PostUpdateModel model, User user);

    void delete(UUID id, User user);
}
