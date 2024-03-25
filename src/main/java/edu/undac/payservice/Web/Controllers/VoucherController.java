package edu.undac.payservice.Web.Controllers;

import edu.undac.payservice.Exceptions.ExceptionDetails;
import edu.undac.payservice.Exceptions.VouchersException;
import edu.undac.payservice.Web.Requests.FileRequest;
import edu.undac.payservice.Web.Responses.ResponseException;
import edu.undac.payservice.Services.VoucherService;
import edu.undac.payservice.Web.Responses.VoucherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/voucher")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;
    @Autowired
    private ResponseException responseException;

    @GetMapping("/{id}")
    public ResponseEntity getByIdVoucher(@PathVariable("id") String idVoucher) throws VouchersException{
        Optional<List<VoucherResponse>> voucherResponses = voucherService.findByIdVoucher(idVoucher);
        return voucherResponses.filter(vouchers -> !vouchers.isEmpty())
                .map(responses -> new ResponseEntity<>(responses, HttpStatus.OK))
                .orElseThrow(() -> new VouchersException("El ID ingresado no existe",
                        new ExceptionDetails("No se encontraron coincidencias", HttpStatus.NOT_FOUND.value())));
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<List<VoucherResponse>> getByIdAlumno(@PathVariable("id") String id) throws VouchersException {
        Optional<List<VoucherResponse>> voucherResponse = voucherService.findByIdAlumno(id);
        return voucherResponse.filter(voucherResponses -> !voucherResponses.isEmpty())
                .map(voucherResponses -> new ResponseEntity(voucherResponses, HttpStatus.OK))
                .orElseThrow(() -> new VouchersException("El ID ingresado es incorrecto",
                        new ExceptionDetails("No se encontraron coincidencias", HttpStatus.NOT_FOUND.value())));
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
    public ResponseEntity insertFromFiles(@RequestBody FileRequest files){
        voucherService.insertFromFiles(files);
        return new ResponseEntity(HttpStatus.CREATED);
    }


}
