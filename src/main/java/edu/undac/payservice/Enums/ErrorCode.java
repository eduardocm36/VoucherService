package edu.undac.payservice.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    VOUCHER_NOT_FOUND("ERROR_001", "No existen pagos para este número de voucher."),
    CONCEPTO_NOT_FOUND("ERROR_002", "No existe el concepto con el código proporcionado."),
    NO_DATA("ERROR_003", "No se encontraron pagos con los datos ingresados."),
    ESTUDIANTE_NO_VOUCHER("ERROR_004", "El estudiante no tiene pagos asociados.");

    private final String code;
    private final String message;

}
