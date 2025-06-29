package com.xgaslan.blog.repositories.base.impl;

import com.xgaslan.blog.domain.entities.BaseEntity;
import com.xgaslan.blog.repositories.base.IBaseRepository;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;
import java.util.Optional;

public class BaseRepository<T, ID extends Serializable> extends SimpleJpaRepository<T,ID> implements IBaseRepository<T, ID> {

    private final EntityManager entityManager;

    public BaseRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public void delete(T entity) {
        if (entity instanceof BaseEntity baseEntity) {
            baseEntity.setDeleted(true);
            baseEntity.setActive(false);
            entityManager.merge(entity); // veya save(entity)
        } else {
            super.delete(entity);
        }
    }

    @Override
    public void deleteById(ID id) {
        Optional<T> entityOpt = findById(id);
        entityOpt.ifPresent(this::delete);
    }
}
