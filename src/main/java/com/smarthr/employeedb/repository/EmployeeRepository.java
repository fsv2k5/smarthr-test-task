package com.smarthr.employeedb.repository;

import com.smarthr.employeedb.domain.Company;
import com.smarthr.employeedb.domain.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends EntityJpaRepository<Employee> {
    @Transactional(readOnly = true)
    @Query(value = "SELECT DISTINCT e " +
            "FROM Employee e " +
            "JOIN e.companies companies " +
            "WHERE companies.id IN (:companyIds)")
    List<Employee> findByCompanyIds(List<UUID> companyIds);

    Optional<Employee> findByInn(long inn);
}
