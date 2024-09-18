package com.api.gesco.service.escola;

import com.api.gesco.infra.exception.ValidacaoException;
import com.api.gesco.model.escola.EmailEscola;
import com.api.gesco.model.escola.TelefoneEscola;
import com.api.gesco.repository.escola.TelefoneEscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelefoneEscolaService {

    @Autowired
    TelefoneEscolaRepository repository;

    public TelefoneEscola validarTelefone(String telefoneEscola){
        var validacaoTelefone = repository.findOneByTelefone(telefoneEscola);

        if(validacaoTelefone == null){
            return validacaoTelefone;
        }else{
            throw new ValidacaoException(String.format("Este telefone %s, já esta cadastrado!", telefoneEscola));
        }
    }

    public TelefoneEscola cadastrarTelefone(String telfoneEscola, Long id){
        var telefone = repository.save(new TelefoneEscola(telfoneEscola, id));
        return telefone;
    }
}
