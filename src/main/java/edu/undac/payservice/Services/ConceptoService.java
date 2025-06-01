package edu.undac.payservice.Services;

import edu.undac.payservice.Enums.ErrorCode;
import edu.undac.payservice.Exceptions.NotFoundException;
import edu.undac.payservice.Models.Concepto;
import edu.undac.payservice.Persistence.ConceptoRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConceptoService {

    @Autowired
    private ConceptoRepository conceptoRepository;

    public Concepto getByCodigo(int codigo){

        Optional<Concepto> concepto = conceptoRepository.getByCodigo(codigo);
        if (concepto.isEmpty())
            throw new NotFoundException(ErrorCode.CONCEPTO_NOT_FOUND.getMessage());

        
        return concepto.get();
    }

}
