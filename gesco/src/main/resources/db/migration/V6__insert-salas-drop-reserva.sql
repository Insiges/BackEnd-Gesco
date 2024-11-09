ALTER TABLE SALAS DROP FOREIGN KEY fk_salas_Escola;

ALTER TABLE SALAS
drop column id_escola;

ALTER TABLE reserva_sala
ADD COLUMN id_escola INT NOT NULL,
ADD CONSTRAINT fk_reservaEscola
FOREIGN KEY (id_escola) REFERENCES escola(id)
ON DELETE CASCADE;

INSERT INTO salas (nome)
VALUES
('Laboratório de Informática'),
('Sala de Artes'),
('Auditório'),
('Laboratório de Ciências'),
('Biblioteca');
