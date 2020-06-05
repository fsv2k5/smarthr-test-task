package com.smarthr.employeedb.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "employees")
//@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee extends BaseEntity {

    @Column(nullable = false, length = 10, unique = true)
    int inn;
    @Column(nullable = false, length = 255)
    String fio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date birthday;
    @ManyToMany(cascade = {REFRESH}, fetch = EAGER)
    @JoinTable(name = "employee_companies",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
//    @EqualsAndHashCode.Exclude
    Set<Company> companies;
}
