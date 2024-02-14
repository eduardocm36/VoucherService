package edu.undac.payservice.Persistence.Repositories;

import edu.undac.payservice.Models.Concepto;

public interface ConceptoCrudRepository {

    Concepto getByCodigo (int codigo);

}
