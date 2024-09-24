
create table sexo(
id int not null auto_increment primary key,
nome varchar(10) not null,
sigla varchar(2) not null,

unique index(id)
);

create table escola(
id int not null auto_increment primary key,
nome varchar(255) not null,
imagem varchar(500) not null,

unique index(id)
);

create table emailEscola(
id int not null auto_increment primary key,
email varchar(255) not null unique,
id_escola int not null,

CONSTRAINT fk_escolaEmail
FOREIGN KEY (id_escola) REFERENCES escola(id)
ON DELETE CASCADE,

unique index(id)
);

create table telefoneEscola(
id int not null auto_increment primary key,
telefone varchar(20) not null unique,
id_escola int not null,

CONSTRAINT fk_escolaTelefone
FOREIGN KEY (id_escola) REFERENCES escola(id)
ON DELETE CASCADE,

unique index(id)
);

create table estado(
id int not null auto_increment primary key,
nome varchar(20) not null unique,
sigla varchar(2) not null,

unique index(id)
);

create table cidade(
id int not null auto_increment primary key,
nome varchar(35) not null unique,
id_estado int not null,

CONSTRAINT fk_Cidade_Estado
FOREIGN KEY (id_estado) REFERENCES estado(id)
ON DELETE CASCADE,

unique index(id)
);

create table enderecoEscola(
id int not null primary key auto_increment,
logradouro varchar(100) not null,
cep varchar(10) not null,
bairro varchar(40) not null,
complemento varchar(20) not null,
numero varchar(5) not null,
id_cidade int not null,
id_escola int not null,

CONSTRAINT fk_enderecoEscola
FOREIGN KEY (id_escola) REFERENCES escola(id)
ON DELETE CASCADE,

CONSTRAINT fk_enderecoEscola_Cidade
FOREIGN KEY (id_cidade) REFERENCES cidade(id)
ON DELETE CASCADE,

unique index(id)
);


create table turmas(
id int not null auto_increment primary key,
nome varchar(20) not null,
ano year not null,
id_escola int not null,

CONSTRAINT fk_turmaEscola
FOREIGN KEY (id_escola) REFERENCES escola(id)
ON DELETE CASCADE,

unique index(id)
);

create table alunos(
id int not null auto_increment primary key,
nome varchar(100) not null,
cpf varchar(15) not null unique,
matricula varchar(20) not null,
data_nascimento date not null,
foto varchar(500) not null,
id_escola int not null,
id_sexo int not null,

CONSTRAINT fk_AlunosSexo
FOREIGN KEY (id_sexo) REFERENCES sexo(id)
ON DELETE CASCADE,

CONSTRAINT fk_alunosEscola
FOREIGN KEY (id_escola) REFERENCES escola(id)
ON DELETE CASCADE,

unique index(id)
);

create table emailAluno(
id int not null auto_increment primary key,
email varchar(255) not null unique,
id_aluno int not null,

CONSTRAINT fk_alunoEmail
FOREIGN KEY (id_aluno) REFERENCES alunos(id)
ON DELETE CASCADE,

unique index(id)
);

create table telefoneAluno(
id int not null auto_increment primary key,
telefone varchar(20) not null unique,
id_aluno int not null,

CONSTRAINT fk_alunoTelefone
FOREIGN KEY (id_aluno) REFERENCES alunos(id)
ON DELETE CASCADE,

unique index(id)
);


create table enderecoAluno(
id int not null primary key auto_increment,
logradouro varchar(100) not null,
cep varchar(10) not null,
bairro varchar(40) not null,
complemento varchar(20) not null,
numero varchar(5) not null,
id_cidade int not null,
id_aluno int not null,

CONSTRAINT fk_enderecoAluno
FOREIGN KEY (id_aluno) REFERENCES alunos(id)
ON DELETE CASCADE,

CONSTRAINT fk_enderecoAluno_Cidade
FOREIGN KEY (id_cidade) REFERENCES cidade(id)
ON DELETE CASCADE,

unique index(id)
);

create table alunos_turma(
id int not null auto_increment primary key,
id_aluno int,
id_turma int,

CONSTRAINT fk_AlunosTurmas
FOREIGN KEY (id_aluno) REFERENCES alunos(id)
ON DELETE CASCADE,

CONSTRAINT fk_turmasAlunos
FOREIGN KEY (id_turma) REFERENCES turmas(id)
ON DELETE CASCADE,

unique index(id)
);

create table disciplina(
id int not null auto_increment primary key,
nome varchar(20) not null,

unique index(id)
);

create table professor(
id int not null auto_increment primary key,
nome varchar(100) not null,
cpf varchar(20) not null unique,
data_nascimento date not null,
foto varchar(500) not null,
id_escola int not null,
id_sexo int not null,

CONSTRAINT fk_ProfessorSexo
FOREIGN KEY (id_sexo) REFERENCES sexo(id)
ON DELETE CASCADE,

CONSTRAINT fk_ProfessorEscola
FOREIGN KEY (id_escola) REFERENCES escola(id)
ON DELETE CASCADE,

unique index(id)
);

create table emailProfessor(
id int not null auto_increment primary key,
email varchar(255) not null unique,
id_professor int not null,

CONSTRAINT fk_professorEmail
FOREIGN KEY (id_professor) REFERENCES professor(id)
ON DELETE CASCADE,

unique index(id)
);

create table telefoneProfessor(
id int not null auto_increment primary key,
telefone varchar(20) not null unique,
id_professor int not null,

CONSTRAINT fk_professorTelefone
FOREIGN KEY (id_professor) REFERENCES professor(id)
ON DELETE CASCADE,

unique index(id)
);

create table enderecoProfessor(
id int not null primary key auto_increment,
logradouro varchar(100) not null,
cep varchar(10) not null,
bairro varchar(40) not null,
complemento varchar(20) not null,
numero varchar(5) not null,
id_cidade int not null,
id_professor int not null,

CONSTRAINT fk_enderecoProfessor
FOREIGN KEY (id_professor) REFERENCES professor(id)
ON DELETE CASCADE,

CONSTRAINT fk_enderecoProfessor_Cidade
FOREIGN KEY (id_cidade) REFERENCES cidade(id)
ON DELETE CASCADE,

unique index(id)
);

create table diploma(
id int not null auto_increment primary key,
faculdade varchar(100) not null,
inicio varchar(10) not null,
fim varchar(10) not null,
curso varchar(100) not null,
id_professor int not null,

CONSTRAINT fk_diplomaProfessor
FOREIGN KEY (id_professor) REFERENCES professor(id)
ON DELETE CASCADE,

unique index(id)
);

create table professor_disciplina(
id int not null auto_increment primary key,
id_professor int not null,
id_disciplina int not null,

CONSTRAINT fk_professor_diplomaProfessor
FOREIGN KEY (id_professor) REFERENCES professor(id)
ON DELETE CASCADE,

CONSTRAINT fk_diploma_DiplomaProfessor
FOREIGN KEY (id_disciplina) REFERENCES disciplina(id)
ON DELETE CASCADE,

unique index(id)
);

create table frequencia(
id int not null auto_increment primary key,
dia date not null,
id_aluno int not null,
id_disciplina int not null,
id_professor int not null,
presenca ENUM('Presente', 'Ausente'),


CONSTRAINT fk_frequenciaProfessor
FOREIGN KEY (id_professor) REFERENCES professor(id)
ON DELETE CASCADE,

CONSTRAINT fk_frequenciaAluno
FOREIGN KEY (id_aluno) REFERENCES alunos(id)
ON DELETE CASCADE,

CONSTRAINT fk_frequenciaDisciplina
FOREIGN KEY (id_disciplina) REFERENCES escola(id)
ON DELETE CASCADE,

unique index(id)
);

create table responsavel(
id int not null auto_increment primary key,
nome varchar(100) not null,
cpf varchar(20) not null unique,
telefone varchar(20) not null,
email varchar(255) not null,
data_nascimento date not null,
foto varchar(500) not null,
id_escola int not null,
id_sexo int not null,

CONSTRAINT fk_ResponsavelSexo
FOREIGN KEY (id_sexo) REFERENCES sexo(id)
ON DELETE CASCADE,

CONSTRAINT fk_ResponsavelEscola
FOREIGN KEY (id_escola) REFERENCES escola(id)
ON DELETE CASCADE,

unique index(id)
);

create table aluno_responsavel(
id int not null auto_increment primary key,
id_aluno int not null,
id_responsavel int not null,

CONSTRAINT fk_alunoResponsavel_Responsavel
FOREIGN KEY (id_responsavel) REFERENCES responsavel(id)
ON DELETE CASCADE,

CONSTRAINT fk_alunoResponsavel_Aluno
FOREIGN KEY (id_aluno) REFERENCES alunos(id)
ON DELETE CASCADE,

unique index(id)
);

create table tipoAtividade(
id int not null auto_increment primary key,
nome varchar(50) not null,


unique index(id)
);

create table atividade(
id int not null auto_increment primary key,
nome varchar(50) not null,
descricao text not null,
valor float not null,
dataAtividade date not null,
id_tipoAtividade int not null,
id_turma int not null,
id_professor int not null,

CONSTRAINT fk_atividadeProfessor
FOREIGN KEY (id_professor) REFERENCES professor(id)
ON DELETE CASCADE,

CONSTRAINT fk_atividadeTipoAtividade
FOREIGN KEY (id_tipoAtividade) REFERENCES tipoAtividade(id)
ON DELETE CASCADE,

CONSTRAINT fk_atividadeTurma
FOREIGN KEY (id_turma) REFERENCES turmas(id)
ON DELETE CASCADE,

unique index(id)
);

create table entrega_atividade(
id int not null auto_increment primary key,
data_entrega date not null,
valor float not null,
id_atividade int not null,
id_aluno int not null,

CONSTRAINT fk_entregaAtividade_Atividade
FOREIGN KEY (id_atividade) REFERENCES atividade(id)
ON DELETE CASCADE,

CONSTRAINT fk_entregaAtividade_Aluno
FOREIGN KEY (id_aluno) REFERENCES alunos(id)
ON DELETE CASCADE,

unique index(id)
);

create table horario(
id int not null auto_increment primary key,
hora time not null,

unique index(id)
);

create table semana(
id int not null auto_increment primary key,
dia varchar(15) not null,

unique index(id)
);

create table grade_horario(
id int not null auto_increment primary key,
id_horario int not null,
id_semana int not null,
id_turma int not null,
id_professor int not null,
id_disciplina int not null,

CONSTRAINT fk_gradeHorario_Horario
FOREIGN KEY (id_horario) REFERENCES horario(id)
ON DELETE CASCADE,

CONSTRAINT fk_gradeHorario_semana
FOREIGN KEY (id_semana) REFERENCES semana(id)
ON DELETE CASCADE,

CONSTRAINT fk_gradeHorario_turma
FOREIGN KEY (id_turma) REFERENCES turmas(id)
ON DELETE CASCADE,

CONSTRAINT fk_gradeHorario_professor
FOREIGN KEY (id_professor) REFERENCES professor(id)
ON DELETE CASCADE,

CONSTRAINT fk_gradeHorario_disciplina
FOREIGN KEY (id_disciplina) REFERENCES disciplina(id)
ON DELETE CASCADE,

unique index(id)
);

create table salas(
id int not null auto_increment primary key,
nome varchar(50) not null,
id_escola int not null,

CONSTRAINT fk_salas_Escola
FOREIGN KEY (id_escola) REFERENCES escola(id)
ON DELETE CASCADE,

unique index(id)
);

create table reserva_sala(
id int not null auto_increment primary key,
dia date not null,
hora time not null,
id_sala int not null,
id_professor int not null,

CONSTRAINT fk_ReservaSala_Sala
FOREIGN KEY (id_sala) REFERENCES salas(id)
ON DELETE CASCADE,

CONSTRAINT fk_reservaSala_Professor
FOREIGN KEY (id_professor) REFERENCES professor(id)
ON DELETE CASCADE,

unique index(id)
);

create table evento(
id int not null auto_increment primary key,
nome varchar(50) not null,
descricao text not null,
dia date not null,
horario time not null,
id_escola int not null,

CONSTRAINT fk_evento_escola
FOREIGN KEY (id_escola) REFERENCES escola(id)
ON DELETE CASCADE,

unique index(id)
);
