package com.smarthr.employeedb.service;

import com.smarthr.employeedb.domain.BaseEntity;
import com.smarthr.employeedb.repository.EntityJpaRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Slf4j
@Getter
@Setter(onMethod = @__({@Inject}))
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class EntityService<Entity extends BaseEntity> implements IEntityService<Entity> {
    public EntityManager entityManager;
    public EntityJpaRepository<Entity> repository;

    @Override
    @Transactional
    public void delete(UUID id) {
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
    public Entity get(UUID id) {
        return ofNullable(id).flatMap(entity -> repository.findById(id)).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Entity get(Entity e) {
        if (e != null) {
            UUID id = e.getId();
            if (id != null) {
                return repository.findById(id).orElse(null);
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
    public List<Entity> get(List<Entity> entities) {
        return repository.findAllById(entities.stream()
                .map(BaseEntity::getId).collect(Collectors.toList()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entity> merge(List<Entity> entities) {
        List<Entity> existing = entities.stream().filter(e -> e.getId() != null).collect(Collectors.toList());
        entities.removeAll(existing);
        List<Entity> out = save(entities);
        out.addAll(repository.findAllById(existing.stream()
                .map(BaseEntity::getId).collect(Collectors.toList())));
        return out;
    }

    @Override
    @Transactional
    public Entity save(Entity e) {
        return ofNullable(e).map(entity -> repository.save(entity)).orElse(null);
    }

//    catch (UnexpectedRollbackException | DataIntegrityViolationException | ConstraintViolationException e)
//    {
//        log.warn("Unable to save.", e);
//    }

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

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Transactional(readOnly = true)
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}