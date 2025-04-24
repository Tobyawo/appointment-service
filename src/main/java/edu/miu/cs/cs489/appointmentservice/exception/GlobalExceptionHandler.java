package edu.miu.cs.cs489.appointmentservice.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ApiError> handleNotFound(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                new ApiError(
                        Instant.now(),
                        404,
                        "Not Found",
                        ex.getMessage(),
                        request.getRequestURI()
                ),
                HttpStatus.NOT_FOUND
        );
    }

//    @ExceptionHandler(SatelliteUpdateNotAllowedException.class)
//    public ResponseEntity<ApiError> handleUpdateError(Exception ex, HttpServletRequest request) {
//        return new ResponseEntity<>(
//                new ApiError(
//                        Instant.now(),
//                        403,
//                        "Forbidden",
//                        ex.getMessage(),
//                        request.getRequestURI()
//                ),
//                HttpStatus.FORBIDDEN
//        );
//    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, IllegalStateException.class})
    public ResponseEntity<ApiError> handleEnumConversionError(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        String param = ex.getName();
        String message = "Invalid value for '" + param + "': " + ex.getValue();
        ApiError error = new ApiError(Instant.now(), 400, "Bad Request", message, request.getRequestURI());
        return ResponseEntity.badRequest().body(error);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors2(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolations2(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
                errors.put(violation.getPropertyPath().toString(), violation.getMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }
}
