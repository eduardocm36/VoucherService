package edu.undac.payservice.Persistence.Repositories;

import java.util.Optional;

import edu.undac.payservice.Models.Concepto;

public interface ConceptoCrudRepository {

    Optional<Concepto> getByCodigo (int codigo);

}
