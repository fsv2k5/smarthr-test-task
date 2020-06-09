package com.smarthr.employeedb.exception;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.RollbackException;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({HttpResponseException.class})
    public ResponseEntity<ErrorMessage> responseException(HttpResponseException e, Object body, WebRequest request) {
        log.info("Response exception handling");
        return error(e, HttpStatus.BAD_REQUEST, StringUtils.EMPTY);
    }

    @ExceptionHandler({UnexpectedRollbackException.class,
            DataIntegrityViolationException.class})
    public ResponseEntity<ErrorMessage> dataException(Exception e, Object body, WebRequest request) {
        log.error("Data exception handling");
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR, StringUtils.EMPTY);
    }

    @ExceptionHandler({TransactionSystemException.class, RollbackException.class})
    public ResponseEntity<ErrorMessage> persistenceException(final Exception ex, final WebRequest request) {
        log.info(ex.getClass().getName());
        Throwable cause = ((TransactionSystemException) ex).getRootCause();
        String message = StringUtils.EMPTY;
        if (cause instanceof ConstraintViolationException) {
            ConstraintViolationException consEx= (ConstraintViolationException) cause;
            ConstraintViolation<?> violation = consEx.getConstraintViolations().stream().findFirst().get();
            message = violation.getPropertyPath() + ": " + violation.getMessage();
        }
        return error(ex, HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorMessage> anyException(Exception e, Object body, WebRequest request) {
        log.error("Internal exception handling");
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR, StringUtils.EMPTY);
    }

    private ResponseEntity<ErrorMessage> error(final Exception exception, final HttpStatus httpStatus, String message) {
        if (StringUtils.isEmpty(message)) {
            message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        }
        log.error(message);
        final ErrorMessage errorMessage = new ErrorMessage(httpStatus, message, httpStatus.getReasonPhrase());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), httpStatus);
    }
}