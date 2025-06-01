package edu.undac.payservice.Persistence.Repositories;


import edu.undac.payservice.Models.Voucher;

import java.util.List;
import java.util.Optional;

public interface VoucherCrudRepository {

    Optional<List<Voucher>> findByIdAlumno(String idAlumno);

    Optional<List<Voucher>> findByAlumnoAndConcepto(String idAlumno, int concepto);

    Optional<List<Voucher>> findByIdVoucher(String idVoucher);

    void updateVoucher(int id);

    void insertVoucher(Voucher voucher);

}
