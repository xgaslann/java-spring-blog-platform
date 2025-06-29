package com.xgaslan.blog.repositories;

import com.xgaslan.blog.domain.entities.Post;
import com.xgaslan.blog.repositories.base.IBaseRepository;

import java.util.UUID;

public interface IPostRepository extends IBaseRepository<Post, UUID> {
}
