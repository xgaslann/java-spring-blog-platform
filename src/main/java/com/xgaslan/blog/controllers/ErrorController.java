package com.xgaslan.blog.controllers;

import com.xgaslan.blog.domain.models.result.ResultModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultModel> handleException(Exception e) {
        ResultModel result = ResultModel.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An unexpected error occurred. Please try again later.")
                .build();

        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResultModel> handleIllegalArgumentException(IllegalArgumentException e) {
        ResultModel result = ResultModel.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ResultModel> handleIllegalStateException(IllegalArgumentException e) {
        ResultModel result = ResultModel.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(result, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResultModel> handleBadCredentialException(BadCredentialsException e) {
        ResultModel result = ResultModel.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("Invalid credentials. Please check your email and password.")
                .build();

        return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResultModel> handleEntityNotFoundException(EntityNotFoundException e) {
        ResultModel result = ResultModel.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message("Resource not found: " + e.getMessage())
                .build();

        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }
}
