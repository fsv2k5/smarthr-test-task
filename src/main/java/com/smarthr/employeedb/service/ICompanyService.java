package com.smarthr.employeedb.service;

import com.smarthr.employeedb.domain.Company;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface ICompanyService extends IEntityService<Company> {
    List<Company> findByEmployeeIds(List<UUID> ids);
}
