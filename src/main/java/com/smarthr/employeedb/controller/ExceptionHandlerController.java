package com.smarthr.employeedb.controller;

import com.smarthr.employeedb.model.ApiError;
import org.apache.tomcat.util.http.fileupload.impl.SizeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.Optional;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({UnsatisfiedServletRequestParameterException.class,
            ValidationException.class,
            SizeException.class})
    public ResponseEntity<ApiError> exception(Exception e) {
        return error(e, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ApiError> error(
            final Exception exception, final HttpStatus httpStatus) {
        final String message =
                Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new ApiError(httpStatus.getReasonPhrase(), message), httpStatus);
    }
}