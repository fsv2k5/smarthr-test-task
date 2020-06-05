package com.smarthr.employeedb.service;

import com.smarthr.employeedb.domain.Company;
import com.smarthr.employeedb.repository.CompanyRepository;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;

@Named
@Setter(onMethod = @__({@Inject}))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyService extends EntityService<Company> implements ICompanyService {
    CompanyRepository companyRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Company> findByEmployeeIds(List<UUID> ids) {
        return companyRepository.findByEmployeeIds(ids);

    }
}