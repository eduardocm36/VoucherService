package edu.undac.payservice.Exceptions;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException{

    private final static long serialVersionUID = 1L;
    private final String message;

    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }

}
