package com.smarthr.employeedb.model;

import com.smarthr.employeedb.util.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@AllArgsConstructor
public class ApiError {

    private String status;
    private String timestamp;
    private String message;

    public ApiError(String httpStatus, String message) {
        this.timestamp = DateTimeUtil.getDeteTimeNowAsString();
        this.status = httpStatus;
        this.message = message;
    }
}
