package com.smarthr.employeedb.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@SuperBuilder
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "employees")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee extends BaseEntity {

    @Column(nullable = false, length = 10, unique = true)
    @Min(1000000000L)
    @Max(9999999999L)
    long inn;

    @Column(nullable = false, length = 255)
    String fio;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date birthday;

    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {PERSIST, REFRESH}, fetch = EAGER)
    @JoinTable(name = "employee_companies",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    Set<Company> companies;
}
