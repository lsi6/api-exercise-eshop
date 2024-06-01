package com.example.eshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Class for handling exceptions.
 */
@ControllerAdvice
public class DefaultExceptionHandler
{
    /**
     * Method to handle a NotFoundException.
     *
     * @param notFoundException - The thrown exception to handle
     * @return - The ResponseEntity object to respond with
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException notFoundException)
    {
        String message = String.format("Could not find a '%s' with the ID '%s'",
                notFoundException.getType(), notFoundException.getId());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    /**
     * Method to handle a BadRequestException.
     *
     * @param badRequestException - The thrown exception to handle
     * @return - The ResponseEntity object to respond with
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException badRequestException)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badRequestException.getErrorMessage());
    }
}
