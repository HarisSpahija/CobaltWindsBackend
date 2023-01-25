package com.harisspahija.cobaltwindsbackend.advice;

import com.harisspahija.cobaltwindsbackend.exception.RepositoryException;
import com.harisspahija.cobaltwindsbackend.exception.RepositoryNoRecordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
}
