package edu.undac.payservice.Persistence;

import edu.undac.payservice.Models.Concepto;
import edu.undac.payservice.Persistence.Mappers.ConceptoMapper;
import edu.undac.payservice.Persistence.Repositories.ConceptoCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ConceptoRepository implements ConceptoCrudRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Concepto getByCodigo(int codigo) {
        String query = "SELECT codigo, descripcion FROM conceptos WHERE codigo = ?";
        return jdbcTemplate.queryForObject(query,  new Object[]{codigo}, new ConceptoMapper());
    }

}
