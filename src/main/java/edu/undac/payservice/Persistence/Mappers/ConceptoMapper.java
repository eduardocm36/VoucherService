package edu.undac.payservice.Persistence.Mappers;

import edu.undac.payservice.Models.Concepto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConceptoMapper implements RowMapper<Concepto> {

    @Override
    public Concepto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Concepto concepto = new Concepto();
        concepto.setCodigo(resultSet.getInt("codigo"));
        concepto.setDescripcion(resultSet.getString("descripcion"));
        return concepto;
    }
}
