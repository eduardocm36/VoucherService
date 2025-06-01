package edu.undac.payservice.Web.Responses;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoucherResponse {

    private int id;
    private String codigoEstudiante;
    private String nombreEstudiante;
    private String codigoVoucher;
    private String concepto;
    private int idConcepto;
    private Double monto;
    private String fecha;
    private Integer estado;

    public String formatoFecha(LocalDate fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return fecha.format(formatter);
    }
    
}
