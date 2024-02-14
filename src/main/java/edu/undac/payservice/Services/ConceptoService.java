package edu.undac.payservice.Services;

import edu.undac.payservice.Models.Concepto;
import edu.undac.payservice.Persistence.ConceptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConceptoService {

    @Autowired
    private ConceptoRepository conceptoRepository;

    public Concepto getByCodigo(int codigo){
        return conceptoRepository.getByCodigo(codigo);
    }

}
