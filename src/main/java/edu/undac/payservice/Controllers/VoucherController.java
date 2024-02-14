package edu.undac.payservice.Controllers;

import edu.undac.payservice.Models.Files;
import edu.undac.payservice.Models.ResponseException;
import edu.undac.payservice.Models.Voucher;
import edu.undac.payservice.Services.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/voucher")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;
    @Autowired
    private ResponseException responseException;

    @GetMapping("/{id}")
    public ResponseEntity getByIdVoucher(@PathVariable("id") String idVoucher) {
        if (voucherService.findByIdVoucher(idVoucher).isEmpty()) {
            responseException.setStatus(HttpStatus.NOT_FOUND.value());
            responseException.setMessage("Valor no encontrado");
            return new ResponseEntity<>(responseException, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(voucherService.findByIdVoucher(idVoucher), HttpStatus.OK);
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity getByIdAlumno(@PathVariable("id") String id){
        if (!voucherService.findByIdAlumno(id).isEmpty()) {
            return new ResponseEntity<>(voucherService.findByIdAlumno(id), HttpStatus.OK);
        }
        responseException.setStatus(HttpStatus.NOT_FOUND.value());
        responseException.setMessage("Valor no encontrado");
        return new ResponseEntity<>(responseException, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/alumno/{alumno}/{concepto}")
    public ResponseEntity getByAlumnoAndConcepto(@PathVariable("alumno") String alumno,
                                                                @PathVariable("concepto") int concepto){
        if (voucherService.findByAlumnoAndConcepto(alumno, concepto).isEmpty()) {
            responseException.setStatus(HttpStatus.NOT_FOUND.value());
            responseException.setMessage("Valor no encontrado");
            return new ResponseEntity<>(responseException, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(voucherService.findByAlumnoAndConcepto(alumno, concepto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEstado(@PathVariable("id") int id){
        voucherService.updateVoucher(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity insertFromFiles(@RequestBody Files files){
        voucherService.insertFromFiles(files);
        return new ResponseEntity(HttpStatus.CREATED);
    }


}
