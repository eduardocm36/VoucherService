package edu.undac.payservice.Persistence.Repositories;


import edu.undac.payservice.Models.Voucher;

import java.util.List;
import java.util.Optional;

public interface VoucherCrudRepository {

    List<Voucher> findByIdAlumno(String idAlumno);

    List<Voucher> findByAlumnoAndConcepto(String idAlumno, int concepto);

    List<Voucher> findByIdVoucher(String idVoucher);

    void updateVoucher(int id);

    void insertVoucher(Voucher voucher);

}
