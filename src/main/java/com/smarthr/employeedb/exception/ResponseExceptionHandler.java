package com.smarthr.employeedb.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.SizeException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UnsatisfiedServletRequestParameterException.class,
            ValidationException.class,
            SizeException.class,
            HttpServerErrorException.class,
            SomeRequestException.class})
    public ResponseEntity<ApiError> exception(Exception e, Object body, WebRequest request) {
        return error(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UnexpectedRollbackException.class,
            DataIntegrityViolationException.class,
            ConstraintViolationException.class})
    public ResponseEntity<ApiError> dataException(Exception e, Object body, WebRequest request) {
        return error(e, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ApiError> error(final Exception exception, final HttpStatus httpStatus) {
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new ApiError(httpStatus.getReasonPhrase(), message), httpStatus);
    }
}