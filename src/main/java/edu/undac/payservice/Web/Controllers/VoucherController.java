package edu.undac.payservice.Web.Controllers;

import edu.undac.payservice.Web.Requests.FileRequest;
import edu.undac.payservice.Services.VoucherService;
import edu.undac.payservice.Web.Responses.VoucherResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voucher")
@RequiredArgsConstructor
public class VoucherController {

    private final VoucherService voucherService;

    @GetMapping("/{id}")
    public ResponseEntity<List<VoucherResponse>> getByIdVoucher(@PathVariable("id") String idVoucher) {
        List<VoucherResponse> voucherResponses = voucherService.findByIdVoucher(idVoucher);
        return ResponseEntity.ok(voucherResponses);
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<List<VoucherResponse>> getByIdAlumno(@PathVariable("id") String id) {
        List<VoucherResponse> voucherResponse = voucherService.findByIdAlumno(id);
        return ResponseEntity.ok(voucherResponse);
    }


    @GetMapping("/alumno/{alumno}/{concepto}")
    public ResponseEntity<List<VoucherResponse>> getByAlumnoAndConcepto(@PathVariable String alumno, @PathVariable int concepto){
        List<VoucherResponse> vouchers = voucherService.findByAlumnoAndConcepto(alumno, concepto);
        return ResponseEntity.ok(vouchers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEstado(@PathVariable("id") int id){
        voucherService.updateVoucher(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/")
    public ResponseEntity<Void> insertFromFiles(@RequestBody FileRequest files){
        voucherService.insertFromFiles(files);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
