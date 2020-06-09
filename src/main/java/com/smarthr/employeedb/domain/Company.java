package com.smarthr.employeedb.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Set;

import static com.smarthr.employeedb.util.Constraint.*;
import static javax.persistence.CascadeType.PERSIST;
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
    @Min(value = MIN_EDRPO, message = EDRPO_MESSAGE)
    @Max(value = MAX_EDRPO, message = EDRPO_MESSAGE)
    int edrpo;

    @Column(name = "company_name", nullable = false, length = 225)
    @Size(max = 225, message = EDRPO_SIZE_MESSAGE)
    String companyName;

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "companies", cascade = {PERSIST, REFRESH}, fetch = EAGER)
    Set<Employee> employees;
}
