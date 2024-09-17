package com.api.gesco.service.escola;

import com.api.gesco.model.escola.TelefoneEscola;
import com.api.gesco.repository.escola.TelefoneEscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelefoneEscolaService {

    @Autowired
    TelefoneEscolaRepository repository;

    public TelefoneEscola cadastrarTelefone(String telfoneEscola, Long id){
        var telefone = repository.save(new TelefoneEscola(telfoneEscola, id));
        return telefone;
    }
}
