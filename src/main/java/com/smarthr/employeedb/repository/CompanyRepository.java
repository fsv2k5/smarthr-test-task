package com.smarthr.employeedb.repository;

import com.smarthr.employeedb.domain.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompanyRepository extends EntityJpaRepository<Company> {
    @Transactional(readOnly = true)
    @Query(value = "SELECT DISTINCT c " +
            "FROM Company c " +
            "JOIN c.employees employees " +
            "WHERE employees.id IN (:employeeIds)")
    List<Company> findByEmployeeIds(List<UUID> employeeIds);

}
