package com.smarthr.employeedb.service;

import com.google.common.collect.Lists;
import com.smarthr.employeedb.domain.BaseEntity;
import com.smarthr.employeedb.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface IEntityService<Entity extends BaseEntity> {

    void delete(UUID id);

    List<Entity> getAll(int page, int size);

    Entity getById(UUID id);

    Entity get(Entity e);

    List<Entity> getAllByIds(List<UUID> ids);

    List<Entity> get(Collection<Entity> entities);

    Entity save(Entity e);

    List<Entity> save(List<Entity> entities);

    void prepareEntityForDelete(UUID id);

    void updateDeleted(Entity entity, Entity update);
}
