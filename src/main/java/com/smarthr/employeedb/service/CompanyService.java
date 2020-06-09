package com.smarthr.employeedb.service;

import com.google.common.collect.Lists;
import com.smarthr.employeedb.domain.Company;
import com.smarthr.employeedb.domain.Employee;
import com.smarthr.employeedb.repository.CompanyRepository;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@Setter(onMethod = @__({@Autowired}))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyService extends EntityService<Company> implements ICompanyService {
    CompanyRepository repository;
    IEmployeeService employeeService;

    @Override
    @Transactional(readOnly = true)
    public List<Company> findByEmployeeIds(List<UUID> ids) {
        return repository.findByEmployeeIds(ids);
    }

    @Override
    public void prepareEntityForDelete(UUID id){
        updateEmployees(id, Lists.newArrayList());
    }

    @Override
    public void updateDeleted(Company company, Company update) {
        List<Employee> newEmployees = Lists.newArrayList(update.getEmployees());
        remove(company, newEmployees);
    }

    @Transactional
    public Company updateEmployees(UUID id, List<UUID> employeeIds) {
        Optional<Company> optional = repository.findById(id);
        if (optional.isPresent()) {
            List<Employee> newEmployees = employeeService.getAllByIds(employeeIds);
            Company company = optional.get();
            remove(company, newEmployees);
            return save(company);
        } else {
            throw new EntityNotFoundException("Company not found");
        }
    }

    void remove(Company company, List<Employee> newEmployees) {
        Set<Employee> updateNeeded = company.getEmployees();
        updateNeeded.removeAll(newEmployees);
        if (CollectionUtils.isNotEmpty(updateNeeded)) {
            updateNeeded.forEach(employee -> {
                employee.getCompanies().remove(company);
                employeeService.save(employee);
            });
        }
    }
}