package edu.undac.payservice.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {

    BAD_REQUEST("400", "Bad Request"),
    NOT_FOUND("404", "Not Found");

    private final String code;
    private final String type;

}