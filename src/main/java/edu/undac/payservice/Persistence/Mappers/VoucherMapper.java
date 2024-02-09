package edu.undac.payservice.Persistence.Mappers;

import edu.undac.payservice.Models.Voucher;
import org.springframework.cglib.core.Local;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class VoucherMapper implements RowMapper<Voucher>{


    @Override
    public Voucher mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Voucher voucher = new Voucher();
        voucher.setId(resultSet.getInt("id"));
        voucher.setCodigoEstudiante(resultSet.getString("codigo_estudiante"));
        voucher.setNombreEstudiante(resultSet.getString("nombre_estudiante"));
        voucher.setCodigoVoucher(resultSet.getString("codigo_voucher"));
        voucher.setConceptoId(resultSet.getInt("concepto_id"));
        voucher.setMonto(resultSet.getDouble("monto"));
        voucher.setFecha(resultSet.getDate("fecha").toLocalDate());
        voucher.setEstado(resultSet.getInt("estado"));
        return voucher;
    }
}
