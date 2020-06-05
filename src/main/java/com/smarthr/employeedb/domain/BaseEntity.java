package com.smarthr.employeedb.domain;

import com.smarthr.employeedb.vo.Identified;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
public abstract class BaseEntity implements Cloneable, Serializable, Identified {
    @Id
//    @Type(type = "pg-uuid") //use for POSTGRES only!
    @Column(name = "id", columnDefinition = "uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Transient
    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    Date createdDate;

    @Transient
    @UpdateTimestamp
    @Column(name = "updated_date", insertable = false)
    Date updatedDate;

    @Transient
    boolean isDeleted;

    @PrePersist
    public void toCreate() {
        setCreatedDate(new Date());
    }

    @PreUpdate
    public void toUpdate() {
        setUpdatedDate(new Date());
    }
}
