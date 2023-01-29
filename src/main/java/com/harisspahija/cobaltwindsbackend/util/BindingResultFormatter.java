package com.harisspahija.cobaltwindsbackend.util;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class BindingResultFormatter {
    public static Map<String, String> formatBindingError(BindingResult bindingResult) {
        HashMap<String, String> errors = new HashMap<>();
        for (FieldError fieldError :  bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }
}
