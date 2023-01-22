package com.castle.weatherupdater.mappers;

public interface IMapEntities<TDto, TEntity, TMapper> {
    TEntity map(TDto dto);
    TEntity map(TEntity entity, TDto dto, TMapper mapper);
}
