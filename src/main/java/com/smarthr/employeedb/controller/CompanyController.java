package com.smarthr.employeedb.controller;

import com.google.common.collect.Lists;
import com.smarthr.employeedb.vo.CompanyDTO;
import com.smarthr.employeedb.domain.Company;
import com.smarthr.employeedb.service.ICompanyService;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("companies")
@Setter(onMethod = @__({@Inject}))
@FieldDefaults(level = AccessLevel.PROTECTED)
public class CompanyController extends EntityController<Company, CompanyDTO> {
    ICompanyService companyService;

    @ResponseBody
    @PostMapping("employees")
    public List<CompanyDTO> getCompaniesByEmployeeIds(@RequestBody List<UUID> ids) {
        return toDTOMapper.apply(companyService.findByEmployeeIds(ids));
    }

    @ResponseBody
    @GetMapping("employee/{id}")
    public List<CompanyDTO> getCompaniesByEmployeeId(@RequestParam UUID id) {
        return toDTOMapper.apply(companyService.findByEmployeeIds(Lists.newArrayList(id)));
    }
}