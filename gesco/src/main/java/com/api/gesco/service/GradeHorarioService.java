package com.api.gesco.service;

import com.api.gesco.domain.grade_horario.DadosCadastroGrade;
import com.api.gesco.domain.grade_horario.DadosDetalhamentoGradeHorarioUnico;
import com.api.gesco.model.frequencia.GradeHorario;
import com.api.gesco.repository.disciplina.DisciplinaRepository;
import com.api.gesco.repository.gradeHorario.GradeHorarioRepository;
import com.api.gesco.repository.horario.HorarioRepository;
import com.api.gesco.repository.professor.ProfessorRepository;
import com.api.gesco.repository.semana.SemanaRepository;
import com.api.gesco.repository.turmas.TurmasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GradeHorarioService {

    @Autowired
    private GradeHorarioRepository repository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private TurmasRepository turmaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private SemanaRepository semanaRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Transactional
    public GradeHorario cadastrarGrade(DadosCadastroGrade dados){

        var turma = turmaRepository.findOneById(dados.id_turma());
        var professor = professorRepository.findOneById(dados.id_professor());
        var disciplina = disciplinaRepository.findOneById(dados.id_disciplina());
        var semana = semanaRepository.findOneById(dados.id_semana());
        var horario = horarioRepository.findOneById(dados.id_horario());

        return repository.save(new GradeHorario(turma, professor, semana, horario, disciplina));

    }

    public ResponseEntity listarGradeHorarioPeloProfessorId(Long id){
        var grade = repository.findGradeHorarioByProfessorId(id);
        return ResponseEntity.ok(grade);
    }

    public ResponseEntity listarGradeHorarioPelaTurmaId(Long id){
        var grade = repository.findGradeHorarioByTurmaId(id);
        return ResponseEntity.ok(grade);
    }

    @Transactional
    public ResponseEntity atualizarGrade(Long id,DadosCadastroGrade dados){
        System.out.println(dados);
        System.out.println(id);
        var grade = repository.findOneById(id);

        if (grade != null){
            var turma = turmaRepository.findOneById(dados.id_turma());
            var professor = professorRepository.findOneById(dados.id_professor());
            var horario = horarioRepository.findOneById(dados.id_horario());
            var semana = semanaRepository.findOneById(dados.id_semana());
            var disciplina = disciplinaRepository.findOneById(dados.id_disciplina());

            grade.atualizarGrade(turma,professor, semana, horario, disciplina);

            repository.save(grade);
        }

        return ResponseEntity.ok(grade);
    }

    @Transactional
    public void deletarGrade(Long id){
        repository.deleteById(id);
    }

    public ResponseEntity pegarGradePeloId(Long id){
        var grade = repository.findOneById(id);

        return ResponseEntity.ok(new DadosDetalhamentoGradeHorarioUnico(grade));
    }
}
