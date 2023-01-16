package com.castle.weatherupdater.Mappers;

public interface IMapper<TDto, TEntity> {
    TEntity map(TDto dto);
    TEntity map(TEntity entity, TDto dto);
}
