package com.clothingstore.exception;

import lombok.Getter;

@Getter
public class IncorrectData extends RuntimeException{

    public IncorrectData(String message) {
        super(message);
    }
}
