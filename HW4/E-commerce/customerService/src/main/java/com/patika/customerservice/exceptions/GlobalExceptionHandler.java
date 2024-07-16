package com.patika.customerservice.exceptions;


import com.patika.customerservice.exceptions.dto.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerServiceException.class)
    public ResponseEntity<ExceptionResponse> handleCustomerServiceException(CustomerServiceException exception){
        log.error("exception occurred. {}",exception.getMessage());

       return ResponseEntity
               .status(HttpStatus.NOT_FOUND)
               .body(prepareExceptionResponse(exception, HttpStatus.NOT_FOUND));
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception exception){
        log.error("exception occurred. {}",exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(prepareExceptionResponse(exception, HttpStatus.BAD_REQUEST));
    }

    private ExceptionResponse prepareExceptionResponse(Exception exception, HttpStatus httpStatus) {
        return ExceptionResponse.builder()
                .message(exception.getMessage())
                .httpStatus(httpStatus)
                .build();
    }
}
