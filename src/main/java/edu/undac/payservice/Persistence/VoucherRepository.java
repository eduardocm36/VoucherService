package edu.undac.payservice.Persistence;

import edu.undac.payservice.Models.Voucher;
import edu.undac.payservice.Persistence.Mappers.VoucherMapper;
import edu.undac.payservice.Persistence.Repositories.VoucherCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VoucherRepository implements VoucherCrudRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Voucher> findByIdAlumno(String idAlumno) throws DataAccessException {
        StringBuilder query = new StringBuilder( "SELECT id, codigo_estudiante, nombre_estudiante, " +
                "codigo_voucher, concepto_id, monto, fecha, estado " +
                "FROM vouchers WHERE codigo_estudiante = ?");
        return jdbcTemplate.query(String.valueOf(query), new Object[]{idAlumno}, new VoucherMapper());
    }

    @Override
    public List<Voucher> findByAlumnoAndConcepto(String idAlumno, int concepto) {
        StringBuilder query = new StringBuilder( "SELECT id, codigo_estudiante, nombre_estudiante, " +
                "codigo_voucher, concepto_id, monto, fecha, estado " +
                "FROM vouchers WHERE codigo_estudiante = ? AND concepto_id = ?");
        return jdbcTemplate.query(String.valueOf(query), new  Object[] {idAlumno, concepto}, new VoucherMapper());
    }

    @Override
    public List<Voucher> findByIdVoucher(String idVoucher) {
        StringBuilder query = new StringBuilder( "SELECT id, codigo_estudiante, nombre_estudiante, " +
                "codigo_voucher, concepto_id, monto, fecha, estado " +
                "FROM vouchers WHERE codigo_voucher = ?");
        return jdbcTemplate.query(String.valueOf(query), new Object[]{idVoucher}, new VoucherMapper());
    }

    @Override
    public void updateVoucher(int id) {
        StringBuilder query = new StringBuilder("UPDATE vouchers " +
                "SET estado = 1 WHERE id = ?");
        jdbcTemplate.update(String.valueOf(query), id);
    }

    @Override
    public void insertVoucher(Voucher voucher) {
        StringBuilder query = new StringBuilder("INSERT INTO vouchers (codigo_estudiante, nombre_estudiante, " +
                "codigo_voucher, concepto_id, monto, fecha, estado) " +
                "VALUE (?, ?, ?, ?, ?, ?, ?)");
        jdbcTemplate.update(String.valueOf(query), voucher.getCodigoEstudiante(),
                voucher.getNombreEstudiante(), voucher.getCodigoVoucher(), voucher.getConceptoId(),
                voucher.getMonto(), voucher.getFecha(), voucher.getEstado());
    }

}
