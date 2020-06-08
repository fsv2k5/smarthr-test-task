package com.smarthr.employeedb.converter;

import com.google.common.collect.Sets;
import com.smarthr.employeedb.vo.CompanyDTO;
import com.smarthr.employeedb.domain.Company;
import com.smarthr.employeedb.service.EmployeeService;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
@Setter(onMethod = @__({@Inject}))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyFromDTOMapper extends EntityMapper<CompanyDTO, Company> {
    EmployeeService employeeService;

    @Override
    public Company doApply(CompanyDTO in) {
        return Company.builder()
                .id(in.getId())
                .companyName(in.getCompanyName())
                .edrpo(in.getEdrpo())
                .employees(Sets.newHashSet(employeeService.getAllByIds(in.getEmployeeIds())))
                .build();
    }
}
