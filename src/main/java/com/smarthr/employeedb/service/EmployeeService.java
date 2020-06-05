package com.smarthr.employeedb.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.smarthr.employeedb.domain.Employee;
import com.smarthr.employeedb.repository.EmployeeRepository;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;

import static java.util.Optional.ofNullable;


@Named
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
    protected  Employee findByUniq(Employee e) {
        return repository.findByInn(e.getInn()).get();
    }

//    @Override
//    @Transactional
//    public Employee save(Employee e) {
//        return ofNullable(e).map(employee -> {
//            if (!employee.getCompanies().isEmpty()) {
//                employee.setCompanies(Sets.newHashSet(companyService.get(employee.getCompanies())));
//            }
//            return super.save(employee);
//        }).orElse(null);
//    }
}