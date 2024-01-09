package com.clothingstore.exception;

import lombok.Getter;

//@ControllerAdvice
@Getter
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
