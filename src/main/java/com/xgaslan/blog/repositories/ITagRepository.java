package com.xgaslan.blog.repositories;

import com.xgaslan.blog.domain.entities.Category;
import com.xgaslan.blog.domain.entities.Tag;
import com.xgaslan.blog.repositories.base.IBaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ITagRepository extends IBaseRepository<Tag, UUID> {
    @Query("SELECT c FROM Tag c LEFT JOIN FETCH c.posts")
    List<Tag> findAllWithPostCount();

    List<Tag> findByNameIn(Set<String> names);
}
