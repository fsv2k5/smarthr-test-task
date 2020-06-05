package com.smarthr.employeedb.service;

import com.smarthr.employeedb.domain.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface IEntityService<Entity extends BaseEntity> {
    @Transactional
    void delete(UUID id);

    @Transactional(readOnly = true)
    List<Entity> getAll(int page, int size);

    @Transactional(readOnly = true)
    Entity get(UUID id);

    @Transactional(readOnly = true)
    Entity get(Entity e);

    @Transactional(readOnly = true)
    List<Entity> getAllByIds(List<UUID> ids);

    @Transactional(readOnly = true)
    List<Entity> get(List<Entity> entities);

    @Transactional(readOnly = true)
    List<Entity> merge(List<Entity> entities);

    @Transactional
    Entity save(Entity e);

    @Transactional
    List<Entity> save(List<Entity> entities);

    @Transactional
    void deleteAll();
}
