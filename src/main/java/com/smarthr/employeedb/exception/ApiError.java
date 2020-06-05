package com.smarthr.employeedb.exception;

import com.smarthr.employeedb.util.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class ApiError extends RuntimeException{

    private String status;
    private String timestamp;
    private String message;

    public ApiError(String httpStatus, String message) {
        this.timestamp = DateTimeUtil.getDeteTimeNowAsString();
        this.status = httpStatus;
        this.message = message;
    }
}
