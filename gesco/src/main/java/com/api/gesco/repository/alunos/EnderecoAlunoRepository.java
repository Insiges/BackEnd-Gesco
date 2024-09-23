package com.api.gesco.repository.alunos;

import com.api.gesco.model.endereco.EnderecoAluno;
import com.api.gesco.model.endereco.EnderecoProfessor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoAlunoRepository extends JpaRepository<EnderecoAluno, Long> {
    EnderecoAluno findOneById(Long id);
}
