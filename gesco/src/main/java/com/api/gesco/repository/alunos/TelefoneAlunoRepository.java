package com.api.gesco.repository.alunos;

import com.api.gesco.model.alunos.TelefoneAluno;
import com.api.gesco.model.professor.TelefoneProfessor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneAlunoRepository extends JpaRepository<TelefoneAluno, Long> {
    TelefoneAluno findOneByTelefone(String telefone);
    TelefoneAluno findOneById(Long id);
}
