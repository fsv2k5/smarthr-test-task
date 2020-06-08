package com.smarthr.employeedb.service;

import com.google.common.collect.Lists;
import com.smarthr.employeedb.domain.Company;
import com.smarthr.employeedb.domain.Employee;
import com.smarthr.employeedb.repository.CompanyRepository;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.*;

@Service
@Setter(onMethod = @__({@Inject}))
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
    protected void updateDeleted(Company exist, Company e) {
        Set<Employee> needToUpdate = exist.getEmployees();
        needToUpdate.removeAll(e.getEmployees());
        if (CollectionUtils.isNotEmpty(needToUpdate)) {
            needToUpdate.forEach(employee -> {
                employee.getCompanies().remove(e);
                employeeService.save(employee);
            });
        }
    }
}