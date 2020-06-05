package com.smarthr.employeedb.repository;

import com.smarthr.employeedb.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntityJpaRepository<E extends BaseEntity> extends JpaRepository<E, UUID> {
}
