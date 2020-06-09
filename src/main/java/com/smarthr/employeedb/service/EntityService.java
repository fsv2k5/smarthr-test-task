package com.smarthr.employeedb.service;

import com.smarthr.employeedb.domain.BaseEntity;
import com.smarthr.employeedb.repository.EntityJpaRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Slf4j
@Getter
@Setter(onMethod = @__({@Autowired}))
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class EntityService<Entity extends BaseEntity> implements IEntityService<Entity> {
    public EntityManager entityManager;
    public EntityJpaRepository<Entity> repository;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        prepareEntityForDelete(id);
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entity> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return repository.findAll(pageable).getContent();
    }

    @Override
    @Transactional(readOnly = true)
    public Entity getById(UUID id) {
        return ofNullable(id).flatMap(entity -> repository.findById(id)).orElseThrow(() ->
                        new EntityNotFoundException("Object with ID " + id + " not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public Entity get(Entity e) {
        if (e != null) {
            UUID id = e.getId();
            if (id != null) {
                return getById(id);
            }
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entity> getAllByIds(List<UUID> ids) {
        return repository.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entity> get(Collection<Entity> entities) {
        return repository.findAllById(entities.stream()
                .map(BaseEntity::getId).collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public Entity save(Entity e) {
        if (Objects.nonNull(e.getId())) {
            Optional<Entity> exist = repository.findById(e.getId());
            if (exist.isPresent())  {
                updateDeleted(exist.get(), e);
            }
        }
        return ofNullable(e).map(entity -> repository.save(entity)).orElse(null);
    }

    @Override
    @Transactional
    public List<Entity> save(List<Entity> entities) {
        if (!ObjectUtils.isEmpty(entities)) {
            List<Entity> entityList = new ArrayList();
            for (Entity e : entities) {
                entityList.add(save(e));
            }
            return entityList;
        }
        return entities;
    }
}