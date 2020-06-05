package com.smarthr.employeedb.controller;

import com.smarthr.employeedb.data.CompanyDTO;
import com.smarthr.employeedb.domain.Company;
import com.smarthr.employeedb.service.CompanyService;
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

}