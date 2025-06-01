package edu.undac.payservice.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import edu.undac.payservice.Enums.ExceptionEnum;
import edu.undac.payservice.Logs.EventsLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler {

    private final EventsLogger eventLogger;

    @ExceptionHandler({ BadRequestException.class })
    public final ResponseEntity<ExceptionType> handleBadRequestException(BadRequestException ex, HttpServletRequest httpServletRequest){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionType exceptionType = new ExceptionType();
        // String format = "(" + ExceptionEnum.BAD_REQUEST.getType() + ") %s";

        exceptionType.setExceptionId(ExceptionEnum.BAD_REQUEST.getCode());
        // exceptionType.setExceptionMessage(format(format, ex.getMessage()));
        exceptionType.setExceptionMessage(ex.getMessage());

        eventLogger.printErrorResponse(httpServletRequest, exceptionType, ex, status.value());
        
        return new ResponseEntity<>(exceptionType, status);
    }

    @ExceptionHandler({ NotFoundException.class })
    public final ResponseEntity<ExceptionType> handleNotFoundException(NotFoundException ex, HttpServletRequest httpServletRequest){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionType exceptionType = new ExceptionType();
        // String format = "(" + ExceptionEnum.NOT_FOUND.getType() + ") %s";

        exceptionType.setExceptionId(ExceptionEnum.NOT_FOUND.getCode());
        // exceptionType.setExceptionMessage(format(format, ex.getMessage()));
        exceptionType.setExceptionMessage(ex.getMessage());

        eventLogger.printErrorResponse(httpServletRequest, exceptionType, ex, status.value());
        
        return new ResponseEntity<>(exceptionType, status);
    }

    // private String format(String format, String Message){
    //     return String.format(format, Message);
    // }

}
