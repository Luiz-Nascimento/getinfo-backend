-- endereco_empresa
INSERT INTO endereco_empresa (logradouro, cep, numero, bairro, complemento) VALUES ('Av. Paulista', '01311-200', 1000, 'Bela Vista', 'Conjunto 501');
INSERT INTO endereco_empresa (logradouro, cep, numero, bairro, complemento) VALUES ('Rua das Flores', '01001-000', 50, 'Centro', 'Sala 3');

-- responsavel
INSERT INTO responsavel (nome, sobrenome, cpf, email, telefone) VALUES ('Carlos', 'Silva', '12345678900', 'carlos@email.com', '11999990000');
INSERT INTO responsavel (nome, sobrenome, cpf, email, telefone) VALUES ('Ana', 'Souza', '98765432100', 'ana@email.com', '11988887777');

-- tel_responsavel
INSERT INTO tel_responsavel (id_responsavel, numero) VALUES (1, '11999990000');
INSERT INTO tel_responsavel (id_responsavel, numero) VALUES (2, '11988887777');

-- empresa
INSERT INTO empresa (id_endereco, id_responsavel, cnpj, razao_social, nome_fantasia, tipo) VALUES (1, 1, '12345678000100', 'Empresa A LTDA', 'EmpA', 1);
INSERT INTO empresa (id_endereco, id_responsavel, cnpj, razao_social, nome_fantasia, tipo) VALUES (2, 2, '98765432000199', 'Empresa B SA', 'EmpB', 2);

-- email_empresa
INSERT INTO email_empresa (id_empresa, email) VALUES (1, 'contato@empa.com');
INSERT INTO email_empresa (id_empresa, email) VALUES (2, 'suporte@empb.com');

-- tel_empresa
INSERT INTO tel_empresa (id_empresa, numero) VALUES (1, '1130004000');
INSERT INTO tel_empresa (id_empresa, numero) VALUES (2, '1144445566');

-- cargo
INSERT INTO cargo (descricao, ativo) VALUES ('Analista de Contratos', 1);
INSERT INTO cargo (descricao, ativo) VALUES ('Gerente de Projetos', 1);

-- colaborador
INSERT INTO colaborador (nome, ativo, cpf, contato, id_cargo) VALUES ('Lucas Almeida', 1, '32165498700', 'lucas@empresa.com', 1);
INSERT INTO colaborador (nome, ativo, cpf, contato, id_cargo) VALUES ('Marina Costa', 1, '74185296300', 'marina@empresa.com', 2);

-- status
INSERT INTO status (descricao) VALUES ('Ativo');
INSERT INTO status (descricao) VALUES ('Concluído');

-- contrato
INSERT INTO contrato (id_cliente, id_funcionario, valor, id_status, descricao, tipo_contrato, anexo, prazo, data_inicio, data_final, id_responsavel) VALUES (1, 1, 50000.00, 1, 'Contrato de prestação de serviços A', 'Serviço', NULL, '2025-12-31 00:00:00', '2025-01-01', '2025-12-31', 1);
INSERT INTO contrato (id_cliente, id_funcionario, valor, id_status, descricao, tipo_contrato, anexo, prazo, data_inicio, data_final, id_responsavel) VALUES (2, 2, 75000.00, 2, 'Contrato de fornecimento B', 'Produto', NULL, '2025-11-30 00:00:00', '2025-02-01', '2025-11-30', 2);

-- entregavel
INSERT INTO entregavel (id_contrato, id_status, prazo, descricao, observacao, just_canc, data_canc, data_final, data_entrega) VALUES (1, 1, 30, 'Entrega do software módulo A', 'Sem observações', NULL, NULL, '2025-05-01', '2025-04-28');
INSERT INTO entregavel (id_contrato, id_status, prazo, descricao, observacao, just_canc, data_canc, data_final, data_entrega) VALUES (2, 2, 45, 'Entrega dos equipamentos B', 'Entrega feita antes do prazo', NULL, NULL, '2025-07-15', '2025-07-10');

-- aditivo
INSERT INTO aditivo (id_entregavel, tempo_adit, data_inicio, data_final, anexo, descricao, tipo_contrato) VALUES (1, '00:30:00', '2025-04-01', '2025-04-30', NULL, 'Aditivo para ajustes', 'Serviço');
INSERT INTO aditivo (id_entregavel, tempo_adit, data_inicio, data_final, anexo, descricao, tipo_contrato) VALUES (2, '01:00:00', '2025-06-01', '2025-07-01', NULL, 'Prorrogação de prazo', 'Produto');

-- repactuacao
INSERT INTO repactuacao (id_contrato, tempo_adit, data_inicio, data_final, anexo, descricao, tipo_contrato) VALUES (1, '00:15:00', '2025-03-01', '2025-04-01', NULL, 'Revisão contratual parcial', 'Serviço');
INSERT INTO repactuacao (id_contrato, tempo_adit, data_inicio, data_final, anexo, descricao, tipo_contrato) VALUES (2, '02:00:00', '2025-05-01', '2025-07-01', NULL, 'Revisão contratual completa', 'Produto');

-- anexo
INSERT INTO anexo (id_contrato, anexo) VALUES (1, NULL);
INSERT INTO anexo (id_contrato, anexo) VALUES (2, NULL);


#SELECT * FROM empresa

#DELETE FROM empresa;