package com.harisspahija.cobaltwindsbackend.exception;

import com.harisspahija.cobaltwindsbackend.advice.CustomExceptionInterface;
import com.harisspahija.cobaltwindsbackend.util.BindingResultFormatter;
import org.springframework.validation.BindingResult;

import java.util.Map;

public class BadRequestBindingException extends RuntimeException implements CustomExceptionInterface {
    private final BindingResult bindingResult;

    public BadRequestBindingException(BindingResult bindingResult) {
        super();
        this.bindingResult = bindingResult;
    }

    public Map<String, String> getErrors() {
        return BindingResultFormatter.formatBindingError(bindingResult);
    }
}
