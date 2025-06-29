package com.xgaslan.blog.domain.mappers.base;

public interface IBaseMapper<TEntity, TCreateModel, TViewModel, TUpdateModel> {

    TViewModel toViewModel(TEntity entity);

    TEntity toEntity(TCreateModel model);

    TEntity toEntity(TUpdateModel model, TEntity entity);

    TViewModel createModelToViewModel(TCreateModel model);

    TViewModel updateModelToViewModel(TUpdateModel model, TEntity entity);
}
