package com.smarthr.employeedb.service;

import com.smarthr.employeedb.domain.Employee;
import com.smarthr.employeedb.repository.EmployeeRepository;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.inject.Inject;
import javax.inject.Named;


@Named
@Setter(onMethod = @__({@Inject}))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeService extends EntityService<Employee> implements IEmployeeService {
    EmployeeRepository employeeRepository;

}