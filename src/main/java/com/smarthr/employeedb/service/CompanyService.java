package com.smarthr.employeedb.service;

import com.google.common.collect.Sets;
import com.smarthr.employeedb.domain.Company;
import com.smarthr.employeedb.domain.Employee;
import com.smarthr.employeedb.repository.CompanyRepository;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;

import static java.util.Optional.ofNullable;

@Named
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
    protected Company findByUniq(Company e) {
        return repository.findByEdrpo(e.getEdrpo()).get();
    }

//    @Override
//    @Transactional
//    public Company save(Company c) {
//        return ofNullable(c).map(company -> {
//            if (!company.getEmployees().isEmpty()) {
//                company.setEmployees(Sets.newHashSet(employeeService.get(company.getEmployees())));
//            }
//            return super.save(company);
//        }).orElse(null);
//    }

}