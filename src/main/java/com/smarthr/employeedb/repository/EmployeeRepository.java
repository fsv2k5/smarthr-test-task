package com.smarthr.employeedb.repository;

import com.smarthr.employeedb.domain.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends EntityJpaRepository<Employee> {
    @Transactional(readOnly = true)
    @Query(value = "SELECT DISTINCT e " +
            "FROM Employee e " +
            "JOIN e.companies companies " +
            "WHERE companies.id IN (:companyIds)")
    List<Employee> findByCompanyIds(@Param("companyIds") List<UUID> companyIds);
}
