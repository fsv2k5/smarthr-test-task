package com.smarthr.employeedb.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.smarthr.employeedb.domain.Company;
import com.smarthr.employeedb.domain.Employee;
import com.smarthr.employeedb.service.ICompanyService;
import com.smarthr.employeedb.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Setter(onMethod = @__({@Inject}))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DataSetup {
    ICompanyService companyService;
    IEmployeeService employeeService;

    @PostConstruct
    void data() {
        Company company1 = companyService.save(Company.builder()
                .edrpo(12345678)
                .companyName("company1")
                .build());
        Company company2 = companyService.save(Company.builder()
                .edrpo(12344321)
                .companyName("company2")
                .build());
        Company company3 = companyService.save(Company.builder()
                .edrpo(87654321)
                .companyName("company3")
                .build());

        Employee employee1 = employeeService.save(Employee.builder()
                .fio("employee1")
                .birthday(Date.valueOf(LocalDate.of(1980,9,9)))
                .inn(1234567890)
                .build());
        Employee employee2 = employeeService.save(Employee.builder()
                .fio("employee1")
                .birthday(Date.valueOf(LocalDate.of(1981,10,12)))
                .inn(1234554321)
                .build());
        Employee employee3 = employeeService.save(Employee.builder()
                .fio("employee1")
                .birthday(Date.valueOf(LocalDate.of(1982,1,6)))
                .inn(987654321)
                .build());

        employee1.setCompanies(Sets.newHashSet(company1));
        employee2.setCompanies(Sets.newHashSet(company1,company3));
        employee3.setCompanies(Sets.newHashSet(company2, company3, company1));
        ArrayList<Employee> employees = Lists.newArrayList(employee1, employee2, employee3);
        employeeService.save(employees);
        List<Company> c = companyService.getAll(1, 100);
        List<Company> companies = companyService.findByEmployeeIds(employees.stream()
                .map(Employee::getId).collect(Collectors.toList()));
        companies.size();
    }
}
