create table tipo_graduacao(
id int not null auto_increment primary key,
nome varchar(20) not null,

unique index(id)
);

INSERT INTO tipo_graduacao (nome)
VALUES
('Tecnólogo'),
('Bacharelado'),
('Licenciatura'),
('Mestrado'),
('Doutorado'),
('Pós-Graduação');


ALTER TABLE diploma
DROP COLUMN inicio,
DROP COLUMN fim;

ALTER TABLE diploma
ADD COLUMN id_tipo_graduacao INT NOT NULL,
ADD CONSTRAINT fk_diplomaTipoGraduacao
FOREIGN KEY (id_tipo_graduacao) REFERENCES tipo_graduacao(id)
ON DELETE CASCADE;
