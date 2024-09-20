package com.api.gesco.service;

import com.api.gesco.infra.exception.ValidacaoException;
import com.api.gesco.model.escola.Escola;
import com.api.gesco.model.escola.TelefoneEscola;
import com.api.gesco.repository.escola.TelefoneEscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TelefoneService {

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

    public TelefoneEscola cadastrarTelefone(String telefoneEscola, Escola escola){
        var telefone = repository.save(new TelefoneEscola(telefoneEscola, escola));
        return telefone;
    }

    @Transactional
    public TelefoneEscola atualizarTelefone(Long id, String telefoneEscola){
        var telefone= repository.findOneById(id);
        if (telefone != null) {
            telefone.atualizarTelefone(telefoneEscola);
            return repository.save(telefone);
        } else {
            throw new ValidacaoException(String.format("Não foi possível encontrar um telefone com esse id!"));
        }
    }
}
