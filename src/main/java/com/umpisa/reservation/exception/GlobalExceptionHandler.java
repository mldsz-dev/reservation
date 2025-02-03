package com.umpisa.reservation.exception;

import com.umpisa.reservation.responseModel.ApiResponse;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle ResourceNotFoundExceptions. This is thrown by the service layer when 
     * a resource is not found.
     * 
     * @param ex the exception containing the error message
     * @return an ApiResponse with the error message and status code 404
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiResponse<?> handleResourceNotFound(ResourceNotFoundException ex) {
        ApiResponse<?> response = new ApiResponse<>(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return response;
    }

    /**
     * Handle MethodArgumentNotValidExceptions. This is thrown by Spring when a @Valid object in a method
     * parameter is not valid.
     * 
     * @param ex the exception containing the field errors
     * @return a map containing the error message, status code 400, and a map of the fields with errors
     *         and their corresponding error messages
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> validationErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            validationErrors.put(error.getField(), error.getDefaultMessage());
        });

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> status = new HashMap<>();

        status.put("message", "Validation failed");
        status.put("code", HttpStatus.BAD_REQUEST.value());
        status.put("validations", validationErrors);

        response.put("status", status);
        return response;
    }

}
