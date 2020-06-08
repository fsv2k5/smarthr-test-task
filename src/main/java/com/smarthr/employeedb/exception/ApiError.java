package com.smarthr.employeedb.exception;

import com.smarthr.employeedb.util.DateTimeUtil;
import lombok.*;

@Setter
@AllArgsConstructor
public class ApiError extends RuntimeException{

    private String status;
    private String timestamp;
    private String message;

    public ApiError(String httpStatus, String message) {
        this.timestamp = DateTimeUtil.getDateTimeNowAsString();
        this.status = httpStatus;
        this.message = message;
    }
}
