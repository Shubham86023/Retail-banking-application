package com.skjs.customerservice.Model.Mapper;

import java.util.List;

public abstract class BaseMapper<Entity, Dto> {

    public abstract Entity DtoToEntity(Dto dto);

    public abstract Dto EntityToDto(Entity entity);

    public List<Dto> EntityListToDtoList(List<Entity> entities) {
        return entities.stream().map(this::EntityToDto).toList();
    }

}
