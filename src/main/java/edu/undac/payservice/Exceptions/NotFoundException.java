package edu.undac.payservice.Exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{

    private final static long serialVersionUID = 1L;
    private String message;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }

}
