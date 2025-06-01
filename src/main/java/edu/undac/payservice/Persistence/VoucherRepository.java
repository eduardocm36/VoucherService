package edu.undac.payservice.Persistence;

import edu.undac.payservice.Models.Voucher;
import edu.undac.payservice.Persistence.Repositories.VoucherCrudRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VoucherRepository implements VoucherCrudRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<List<Voucher>> findByIdAlumno(String idAlumno) throws DataAccessException {
        String query = """
                SELECT 
                        id, 
                        codigo_estudiante,
                        nombre_estudiante,
                        codigo_voucher,
                        concepto_id,
                        monto,
                        fecha,
                        estado
                FROM 
                        vouchers
                WHERE 
                        codigo_estudiante = :codigo
        """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("codigo", idAlumno);
        List<Voucher> vouchers = jdbcTemplate.query(query, params, new VoucherMapper());

        return vouchers.isEmpty() ? Optional.empty() : Optional.of(vouchers);
    }

    @Override
    public Optional<List<Voucher>> findByAlumnoAndConcepto(String idAlumno, int concepto) {
        String query = """
                SELECT 
                        id,
                        codigo_estudiante,
                        nombre_estudiante,
                        codigo_voucher,
                        concepto_id,
                        monto,
                        fecha,estado
                FROM 
                        vouchers
                WHERE 
                        codigo_estudiante = :estudiante
                AND 
                        concepto_id = :concepto
        """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("estudiante", idAlumno);
        params.addValue("concepto", concepto);
        List<Voucher> vouchers = jdbcTemplate.query(query, params, new VoucherMapper());
        
        return vouchers.isEmpty() ? Optional.empty() : Optional.of(vouchers);
    }

    @Override
    public Optional<List<Voucher>> findByIdVoucher(String idVoucher) {
        String query = """
                SELECT 
                        id,
                        codigo_estudiante,
                        nombre_estudiante,
                        codigo_voucher,
                        concepto_id,
                        monto,
                        fecha,
                        estado
                FROM 
                        vouchers 
                WHERE 
                        codigo_voucher = :voucher
        """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("voucher", idVoucher);
        List<Voucher> vouchers = jdbcTemplate.query(query, params, new VoucherMapper());
        
        return vouchers.isEmpty() ? Optional.empty() : Optional.of(vouchers);
    }

    @Override
    public void updateVoucher(int id) {
        String query = """
                UPDATE 
                        vouchers
                SET 
                        estado = 1 
                WHERE 
                        id = :id
        """;
        
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        jdbcTemplate.update(query, params);
    }

    @Override
    public void insertVoucher(Voucher voucher) {
        String query = """
                INSERT INTO 
                        vouchers (
                                codigo_estudiante,
                                nombre_estudiante,
                                codigo_voucher,
                                concepto_id,
                                monto,
                                fecha,
                                estado
                        )
                VALUE (
                        :codEstudiante,
                        :nomEstudiante,
                        :voucher,
                        :concepto,
                        :monto,
                        :fecha,
                        :estado
                )
        """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("codEstudiante", voucher.getCodigoEstudiante());
        params.addValue("nomEstudiante", voucher.getNombreEstudiante());
        params.addValue("voucher", voucher.getCodigoVoucher());
        params.addValue("concepto", voucher.getConceptoId());
        params.addValue("monto", voucher.getMonto());
        params.addValue("fecha", voucher.getFecha());
        params.addValue("estado", voucher.getEstado());

        jdbcTemplate.update(query, params);
    }


    private static class VoucherMapper implements RowMapper<Voucher> {
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

}
