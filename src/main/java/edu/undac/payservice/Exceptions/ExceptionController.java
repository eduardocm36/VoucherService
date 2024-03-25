package edu.undac.payservice.Exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionController.class.getName());

    @ExceptionHandler(VouchersException.class)
    public ResponseEntity<ExceptionDetails> handVoucherException(VouchersException exception){
        LOG.error(exception.getMessage());
        return new ResponseEntity<>(exception.getDetails(), HttpStatus.valueOf(exception.getDetails().getCode()));
    }

}
