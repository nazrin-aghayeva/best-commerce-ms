package org.signup.ms.exception;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;

@Log4j2
@ControllerAdvice
@ResponseBody
public class GlobalExceptionController {


    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);

    @ExceptionHandler(value = {InvalidLoginException.class})
    @ResponseBody
    public ErrorMessage handleUserAlreadyExistException(InvalidLoginException ex) {

        logger.error("User Already Exist Exception: " + ex.getMessage());
        return new ErrorMessage(
                HttpStatus.UNAUTHORIZED.value(),
                new Timestamp(System.currentTimeMillis()),
                ex.getMessage(),
                "");

    }

    @ExceptionHandler(value = {InvalidInputException.class})
    @ResponseBody
    public ErrorMessage handleInvalidInputException(InvalidInputException ex) {

        logger.error("Invalid Input Exception: " + ex.getMessage());
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Timestamp(System.currentTimeMillis()),
                ex.getMessage(),
                "");

    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    @ResponseBody
    public ErrorMessage handleInvalidJwtAuthenticationException(InvalidJwtAuthenticationException ex) {
        logger.error("Invalid Jwt Authentication Exception: " + ex.getMessage());
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Timestamp(System.currentTimeMillis()),
                ex.getMessage(),
                "");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ErrorMessage handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        logger.error("Resource Not Found Exception: " + ex.getMessage());
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Timestamp(System.currentTimeMillis()),
                ex.getMessage(),
                "");
    }


    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ErrorMessage handleException(Exception ex) {
        logger.error("Exception: " + ex.getMessage());
        return new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Timestamp(System.currentTimeMillis()),
                ex.getMessage(),
                "");
    }


}
