package com.harisspahija.cobaltwindsbackend.advice;

import com.harisspahija.cobaltwindsbackend.exception.BadRequestBindingException;
import com.harisspahija.cobaltwindsbackend.exception.BadRequestCustomException;
import com.harisspahija.cobaltwindsbackend.exception.RepositoryException;
import com.harisspahija.cobaltwindsbackend.exception.RepositoryNoRecordException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(RepositoryException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String repositoryExceptionHandler(RepositoryException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(RepositoryNoRecordException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String repositoryNoRecordExceptionHandler(RepositoryException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(BadRequestCustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String badRequestCustomException (BadRequestCustomException ex) { return ex.getMessage(); }

    @ResponseBody
    @ExceptionHandler(BadRequestBindingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String, String> badRequestExceptionHandler(BadRequestBindingException ex) { return ex.getErrors(); }

    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String dataIntegrityViolationExceptionHandler(DataIntegrityViolationException ex) {
        return ex.getMessage();
    }
}
