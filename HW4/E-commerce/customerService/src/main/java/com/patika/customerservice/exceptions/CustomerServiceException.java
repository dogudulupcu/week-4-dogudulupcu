package com.patika.customerservice.exceptions;




public class CustomerServiceException extends RuntimeException {
    private String message;

    public CustomerServiceException(String message) {
        super(message);
        this.message=message;
    }

}
