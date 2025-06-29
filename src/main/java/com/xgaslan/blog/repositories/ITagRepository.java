package com.xgaslan.blog.repositories;

import com.xgaslan.blog.domain.entities.Tag;
import com.xgaslan.blog.repositories.base.IBaseRepository;

import java.util.UUID;

public interface ITagRepository extends IBaseRepository<Tag, UUID> {
}
