package edu.undac.payservice.Models;

import org.springframework.stereotype.Component;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Component
@Data
public class Voucher {

    private Integer id;
    private String codigoEstudiante;
    private String nombreEstudiante;
    private String codigoVoucher;
    private Integer conceptoId;
    private Double monto;
    private LocalDate fecha;
    private Integer estado;

    public static LocalDate dateConstructor(String date){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, format);
    }

    public String nameConstructor(String[] line, int from, int to) {
        String[] names = Arrays.copyOfRange(line, from, to);
        return String.join(" ", names);
    }
}