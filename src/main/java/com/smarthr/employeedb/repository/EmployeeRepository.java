package com.smarthr.employeedb.repository;

import com.smarthr.employeedb.domain.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends EntityJpaRepository<Employee> {

}
