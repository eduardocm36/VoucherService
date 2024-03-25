package edu.undac.payservice.Exceptions;

public class VouchersException extends Exception{

    private ExceptionDetails details;

    public VouchersException(String message, ExceptionDetails details) {
        super(message);
        this.details = details;
    }

    public VouchersException(String message, Throwable cause, ExceptionDetails details) {
        super(message, cause);
        this.details = details;
    }

    public ExceptionDetails getDetails() {
        return details;
    }

    public void setDetails(ExceptionDetails details) {
        this.details = details;
    }
}
