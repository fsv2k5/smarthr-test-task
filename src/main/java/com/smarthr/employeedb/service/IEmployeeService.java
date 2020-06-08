package com.smarthr.employeedb.service;

import com.smarthr.employeedb.domain.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface IEmployeeService extends IEntityService<Employee> {
    List<Employee> findByCompaniesIds(List<UUID> ids);
    Employee updateCompanies(UUID id, List<UUID> companyIds);
}
