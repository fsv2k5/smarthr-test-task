package com.smarthr.employeedb.service;

import com.google.common.collect.Lists;
import com.smarthr.employeedb.domain.Company;
import com.smarthr.employeedb.domain.Employee;
import com.smarthr.employeedb.repository.EmployeeRepository;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.*;



@Service
@Setter(onMethod = @__({@Inject}))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeService extends EntityService<Employee> implements IEmployeeService {
    EmployeeRepository repository;
    ICompanyService companyService;

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findByCompaniesIds(List<UUID> ids) {
        return repository.findByCompanyIds(ids);
    }

    @Override
    protected void updateDeleted(Employee employee, Employee update) {
        List<Company> newCompanies = Lists.newArrayList(update.getCompanies());
        remove(employee, newCompanies);
    }

    @Override
    public Employee updateCompanies(UUID id, List<UUID> companyIds) {
        Optional<Employee> optional = repository.findById(id);
        if (optional.isPresent()) {
            List<Company> newCompanies = companyService.getAllByIds(companyIds);
            Employee employee = optional.get();
            remove(employee, newCompanies);
            return save(employee);
        } else {
            throw new EntityNotFoundException();
        }
    }

    private void remove(Employee employee, List<Company> newCompanies) {
        Set<Company> updateNeeded = employee.getCompanies();
        updateNeeded.removeAll(newCompanies);
        if (CollectionUtils.isNotEmpty(updateNeeded)) {
            updateNeeded.forEach(company -> {
                company.getEmployees().remove(employee);
                companyService.save(company);
            });
        }
    }
}