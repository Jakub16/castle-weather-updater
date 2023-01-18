package com.castle.weatherupdater.mappers;

public interface IMapper<TDto, TEntity> {
    TEntity map(TDto dto);
    TEntity map(TEntity entity, TDto dto);
}
