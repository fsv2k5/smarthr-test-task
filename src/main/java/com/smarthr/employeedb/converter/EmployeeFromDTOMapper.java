package com.smarthr.employeedb.converter;

import com.smarthr.employeedb.data.EmployeeDTO;
import com.smarthr.employeedb.domain.Company;
import com.smarthr.employeedb.domain.Employee;
import com.smarthr.employeedb.service.CompanyService;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Set;

@Named
@Setter(onMethod = @__({@Inject}))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeFromDTOMapper extends EntityMapper<EmployeeDTO, Employee> {
    CompanyService companyService;

    @Override
    public Employee doApply(EmployeeDTO in) {
        return Employee.builder()
                .id(in.getId())
                .fio(in.getFio())
                .inn(in.getInn())
                .birthday(in.getBirthday())
                .companies((Set<Company>) companyService.getAllByIds(in.getCompanyIds()))
                .build();
    }

}
