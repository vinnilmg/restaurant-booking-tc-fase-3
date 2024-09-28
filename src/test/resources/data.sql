INSERT INTO reserva
    (CPF_CLIENTE, DATA_HORA_RESERVA, STATUS, DATA_HORA_CRIACAO)
VALUES ('26407243041', '2025-01-01 13:00:00', 'SOLICITADA', '2024-09-22 18:30:00'),
       ('21859806023', '2030-01-30 12:00:00', 'CANCELADA', '2024-09-22 18:30:00'),
       ('82672559074', '2050-12-31 23:59:59', 'CONFIRMADA', '2024-09-22 18:30:00'),
       ('51207801054', '2025-12-01 16:00:00', 'SOLICITADA', '2024-09-22 18:30:00'),
       ('10205119077', '2025-06-01 12:00:00', 'SOLICITADA', '2024-09-22 18:30:00');


INSERT INTO endereco
    (RUA, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, ESTADO, CEP)
VALUES ('Rua numero 1', '52', 'ap 05', 'Vila Bela', 'Nao sei', 'SP', '02998050');

INSERT INTO restaurante
(NOME, CNPJ, ENDERECO_ID, TIPO_CULINARIA, CAPACIDADE, MEDIA_FEEDBACK, INICIO_FUNCIONAMENTO, FIM_FUNCIONAMENTO)
VALUES
    ('Padaria do seu Zé', '20094036000199', (select max(endereco.id) from endereco), 'BRASILEIRA', 40, 5.0, '08:00:00', '17:00:00');



INSERT INTO feedback (id, restaurante_id, nome_cliente, avaliacao, comentario, data_hora_criacao)
VALUES (1, 1, 'John Doe', 5, 'Excelente serviço!', '2024-09-28T12:34:56');
