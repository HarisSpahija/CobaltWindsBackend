package com.harisspahija.cobaltwindsbackend.advice;

import com.harisspahija.cobaltwindsbackend.exception.PlayerHasDuplicateRoleException;
import com.harisspahija.cobaltwindsbackend.exception.PlayerHasPrimaryRoleAndSecondaryRoleIsNullException;
import com.harisspahija.cobaltwindsbackend.exception.PlayerHasPrimaryRoleFillAndSecondaryRoleNotNullException;
import com.harisspahija.cobaltwindsbackend.exception.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class PlayerExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String playerNotFoundHandler(PlayerNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PlayerHasDuplicateRoleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String, String> playerHasDuplicateRoleHandler(PlayerHasDuplicateRoleException ex) {
        return ex.getErrors();
    }

    @ResponseBody
    @ExceptionHandler(PlayerHasPrimaryRoleFillAndSecondaryRoleNotNullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String, String> playerHasPrimaryRoleFillAndSecondaryRoleNotNullHandler(PlayerHasPrimaryRoleFillAndSecondaryRoleNotNullException ex) {
        return ex.getErrors();
    }

    @ResponseBody
    @ExceptionHandler(PlayerHasPrimaryRoleAndSecondaryRoleIsNullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String, String> playerHasPrimaryRoleAndSecondaryRoleIsNullException(PlayerHasPrimaryRoleAndSecondaryRoleIsNullException ex) {
      return ex.getErrors();
    }
}
