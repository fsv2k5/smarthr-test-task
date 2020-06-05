package com.smarthr.employeedb.data;

import java.util.UUID;

public interface Identified {
    UUID getId();
    void setId(UUID id);
}
