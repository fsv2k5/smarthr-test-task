package com.smarthr.employeedb.converter;

import com.smarthr.employeedb.vo.EmployeeDTO;
import com.smarthr.employeedb.domain.Company;
import com.smarthr.employeedb.domain.Employee;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.stream.Collectors;

@Component
@Setter(onMethod = @__({@Inject}))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeToDtoMapper extends EntityMapper<Employee, EmployeeDTO> {

    @Override
    public EmployeeDTO doApply(Employee in) {
        return EmployeeDTO.builder()
                .id(in.getId())
                .fio(in.getFio())
                .inn(in.getInn())
                .birthday(in.getBirthday())
                .companyIds(in.getCompanies().stream()
                        .map(Company::getId).collect(Collectors.toList()))
                .build();
    }
}