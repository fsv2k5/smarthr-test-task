package com.smarthr.employeedb.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@SuperBuilder
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "companies")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company extends BaseEntity {

    @Column(nullable = false, length = 8, unique = true)
    @Min(10000000)
    @Max(99999999)
    int edrpo;

    @Column(name = "company_name", nullable = false, length = 225)
    String companyName;

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "companies", cascade = {ALL}, fetch = EAGER)
    Set<Employee> employees;
}
