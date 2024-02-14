package edu.undac.payservice.Models;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VoucherResponse {

    private int id;
    private String codigoEstudiante;
    private String nombreEstudiante;
    private String codigoVoucher;
    private String concepto;
    private Double monto;
    private String fecha;
    private Integer estado;

    public String formatoFecha(LocalDate fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return fecha.format(formatter);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getCodigoVoucher() {
        return codigoVoucher;
    }

    public void setCodigoVoucher(String codigoVoucher) {
        this.codigoVoucher = codigoVoucher;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
