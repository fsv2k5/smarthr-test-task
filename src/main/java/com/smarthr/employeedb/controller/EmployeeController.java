package com.smarthr.employeedb.controller;

import com.smarthr.employeedb.data.EmployeeDTO;
import com.smarthr.employeedb.domain.Employee;
import com.smarthr.employeedb.service.IEmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employees")
public class EmployeeController extends EntityController<Employee, EmployeeDTO> {
    IEmployeeService employeeService;
}