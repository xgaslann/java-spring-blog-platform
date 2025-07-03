package com.xgaslan.blog.services;

import com.xgaslan.blog.domain.entities.Tag;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ITagService {
    List<Tag> getAll();

    List<Tag> create(Set<String> tags);

    Tag getById(UUID id);

    List<Tag> getByIds(Set<UUID> ids);

    void delete(UUID id);
}
