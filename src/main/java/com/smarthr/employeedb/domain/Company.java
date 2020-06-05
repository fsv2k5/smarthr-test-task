package com.smarthr.employeedb.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "companies")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company extends BaseEntity {

    @Column(nullable = false, length = 8, unique = true)
    int edrpo;
    @Column(name = "company_name", nullable = false, length = 225)
    String companyName;
    @ManyToMany(mappedBy = "companies", cascade = {REFRESH}, fetch = EAGER)
    Set<Employee> employees;
}
