package edu.undac.payservice.Persistence.Mappers;

import edu.undac.payservice.Models.Concepto;
import edu.undac.payservice.Models.Voucher;
import edu.undac.payservice.Web.Responses.VoucherResponse;
import org.springframework.stereotype.Component;

@Component
public class VoucherResponseMapper {

    public VoucherResponse toVoucherResponse(Voucher voucher, Concepto concepto){
        VoucherResponse voucherResponse = new VoucherResponse();
        voucherResponse.setId(voucher.getId());
        voucherResponse.setCodigoEstudiante(voucher.getCodigoEstudiante());
        voucherResponse.setNombreEstudiante(voucher.getNombreEstudiante());
        voucherResponse.setFecha(voucherResponse.formatoFecha(voucher.getFecha()));
        voucherResponse.setCodigoVoucher(voucher.getCodigoVoucher());
        voucherResponse.setConcepto(concepto.getDescripcion());
        voucherResponse.setMonto(voucher.getMonto());
        voucherResponse.setEstado(voucher.getEstado());
        voucherResponse.setIdConcepto(concepto.getCodigo());
        return voucherResponse;
    }


}
