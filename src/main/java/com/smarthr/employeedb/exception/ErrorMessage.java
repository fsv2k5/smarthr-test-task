package com.smarthr.employeedb.exception;

import com.smarthr.employeedb.util.DateTimeUtil;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ErrorMessage extends RuntimeException{

    private HttpStatus status;
    private String timestamp;
    private String message;
    private String error;

    public ErrorMessage(HttpStatus httpStatus, String message, String error) {
        this.timestamp = DateTimeUtil.getDateTimeNowAsString();
        this.status = httpStatus;
        this.message = message;
        this.error = error;
    }
}
