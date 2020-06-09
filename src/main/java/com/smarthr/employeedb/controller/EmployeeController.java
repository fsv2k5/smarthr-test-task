package com.smarthr.employeedb.controller;

import com.google.common.collect.Lists;
import com.smarthr.employeedb.vo.EmployeeDTO;
import com.smarthr.employeedb.domain.Employee;
import com.smarthr.employeedb.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("employees")
@Setter(onMethod = @__({@Autowired}))
@FieldDefaults(level = AccessLevel.PROTECTED)
public class EmployeeController extends EntityController<Employee, EmployeeDTO> {
    IEmployeeService employeeService;

    @ResponseBody
    @PostMapping(path = {"companies", "companies/"})
    public List<EmployeeDTO> getEmployeesByCompanyIds(@RequestBody List<UUID> ids) {
        return toDTOMapper.apply(employeeService.findByCompaniesIds(ids));
    }

    @ResponseBody
    @GetMapping(path = {"company/{id}", "company/{id}/"})
    public List<EmployeeDTO> getEmployeesByCompanyId(@PathVariable UUID id) {
        return toDTOMapper.apply(employeeService.findByCompaniesIds(Lists.newArrayList(id)));
    }

    @ResponseBody
    @PutMapping(path = {"{employeeId}/companies", "{employeeId}/companies/"})
    public EmployeeDTO updateCompaniesForEmployeeId(@PathVariable UUID employeeId, @RequestBody List<UUID> companyIds) {
        EmployeeDTO dto = toDTOMapper.apply(service.getById(employeeId));
        dto.setCompanyIds(companyIds);
        return toDTOMapper.apply(service.save(fromDTOMapper.apply(dto)));
    }
}