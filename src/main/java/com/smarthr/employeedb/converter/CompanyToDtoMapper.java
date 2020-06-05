package com.smarthr.employeedb.converter;


import com.smarthr.employeedb.data.CompanyDTO;
import com.smarthr.employeedb.domain.Company;
import com.smarthr.employeedb.domain.Employee;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.stream.Collectors;

@Named
@Setter(onMethod = @__({@Inject}))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyToDtoMapper extends EntityMapper<Company, CompanyDTO> {

    @Override
    public CompanyDTO doApply(Company in) {
        return CompanyDTO.builder()
                .id(in.getId())
                .companyName(in.getCompanyName())
                .edrpo(in.getEdrpo())
                .employeeIds(in.getEmployees().stream()
                        .map(Employee::getId).collect(Collectors.toList()))
                .build();
    }
}