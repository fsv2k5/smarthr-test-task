package com.smarthr.employeedb.controller;

import com.google.common.collect.Lists;
import com.smarthr.employeedb.vo.CompanyDTO;
import com.smarthr.employeedb.vo.EmployeeDTO;
import com.smarthr.employeedb.domain.Employee;
import com.smarthr.employeedb.service.IEmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("employees")
public class EmployeeController extends EntityController<Employee, EmployeeDTO> {
    IEmployeeService employeeService;

    @ResponseBody
    @GetMapping("company/{id}")
    public List<EmployeeDTO> getEmployeesByCompanyId(@RequestParam UUID id) {
        return toDTOMapper.apply(employeeService.findByCompaniesIds(Lists.newArrayList(id)));
    }

//1.	Получить полную информацию о сотруднике (его данные и ИД компаний, где он работает).
//      GET /employees/{id}
//2.	Получить список всех сотрудников (их данные и ИД компаний, где они работают). Желательно с поддержкой пагинации, но не обязательно.
//      GET /employees
//3.	Получить список всех сотрудников, работающих в компании по ее ИД.
//      GET /employees/company/{id}
//5.	Добавить нового сотрудника.
//      POST /employees
//6.	Редактировать сотрудника (ИНН, ФИО, дата рождения).
//      PUT /employees/ (body)
//7.	Удалить сотрудника.
//      DELETE /employees/{id}
//8.	Добавить сотрудника, работающего в одной или нескольких компаниях.
//      POST /employees
//9.	Редактировать для существующего сотрудника его места работы (добавить новое, удалить имеющееся). Сделать это при помощи одного запроса.
//      PUT /employees/{id}/replaceCompanies (body)
}