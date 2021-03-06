package com.smarthr.employeedb.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class HttpResponseException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;
}
