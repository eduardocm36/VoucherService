package edu.undac.payservice.Persistence;

import edu.undac.payservice.Models.Concepto;
import edu.undac.payservice.Persistence.Repositories.ConceptoCrudRepository;
import lombok.RequiredArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConceptoRepository implements ConceptoCrudRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<Concepto> getByCodigo(int codigo) {
        String query = """
                SELECT
                    codigo,
                    descripcion
                FROM 
                    conceptos
                WHERE 
                    codigo = :codigo
        """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("codigo", codigo);

        List<Concepto> concepto = jdbcTemplate.query(query, params, new ConceptoMapper());

        return concepto.isEmpty() ? Optional.empty() : Optional.of(concepto.get(0));
    }

    private static class ConceptoMapper implements RowMapper<Concepto> {
        @Override
        public Concepto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Concepto concepto = new Concepto();
                concepto.setCodigo(resultSet.getInt("codigo"));
                concepto.setDescripcion(resultSet.getString("descripcion"));
            return concepto;
        }
    }

}
