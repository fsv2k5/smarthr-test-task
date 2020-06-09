package com.smarthr.employeedb.controller;

import com.google.common.collect.Lists;
import com.smarthr.employeedb.vo.CompanyDTO;
import com.smarthr.employeedb.domain.Company;
import com.smarthr.employeedb.service.ICompanyService;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("companies")
@Setter(onMethod = @__({@Autowired}))
@FieldDefaults(level = AccessLevel.PROTECTED)
public class CompanyController extends EntityController<Company, CompanyDTO> {
    ICompanyService companyService;

    @ResponseBody
    @PostMapping(path = {"employees", "employees/"})
    public List<CompanyDTO> getCompaniesByEmployeeIds(@RequestBody List<UUID> ids) {
        return toDTOMapper.apply(companyService.findByEmployeeIds(ids));
    }

    @ResponseBody
    @GetMapping(path = {"employee/{id}", "employee/{id}/"})
    public List<CompanyDTO> getCompaniesByEmployeeId(@PathVariable UUID id) {
        return toDTOMapper.apply(companyService.findByEmployeeIds(Lists.newArrayList(id)));
    }

    @ResponseBody
    @PutMapping(path = {"{companyId}/employees", "{companyId}/employees/"})
    public CompanyDTO updateEmployeesForCompanyId(@PathVariable UUID companyId, @RequestBody List<UUID> employeeIds) {
        CompanyDTO dto = toDTOMapper.apply(service.getById(companyId));
        dto.setEmployeeIds(employeeIds);
        return toDTOMapper.apply(service.save(fromDTOMapper.apply(dto)));
    }
}