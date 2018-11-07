USE db_cronos;

-- Tabela papel_usuario
INSERT INTO papel_usuario (id_papel, nome_papel, descricao_papel) 
VALUES (1, 'ROLE_ADMINISTRADOR', 'Perfil para administradores');

-- Tabela predio
INSERT INTO predio (id_predio, nome_predio, descricao_predio) 
VALUES (1, 'Graduação - EAJ', 'Prédio da Escola Agrícola de Jundiaí');

-- Tabela funcionario
INSERT INTO funcionario (id_funcionario, nome_funcionario, matricula_funcionario, cpf_funcionario, email_funcionario, telefone_funcionario) 
VALUES (1, 'Administrador da Graduação - EAJ', '0000000', '12345678910', 'admin@eaj.ufrn.br', '12345678');

-- Tabela usuario
INSERT INTO usuario (id_usuario, login_usuario, senha_usuario, ativo_usuario, tentativas_login_usuario, id_funcionario) 
VALUES (1, 'admin', MD5('987456'), 1, 0, 1);

-- Tabela permissao_usuario
INSERT INTO permissao_usuario (id_papel, id_usuario) VALUES (1, 1);

-- Tabela perfil_sala_turma
INSERT INTO perfil_sala_turma(id_perfil_sala_turma, nome_perfil_sala_turma, descricao_perfil_sala_turma) VALUES 
(1, 'Convencional', 'Perfil para salas de aula convencionais'),
(2, 'Laboratório', 'Perfil para caracterizar realização de aulas de informática');

-- Tabela periodo
INSERT INTO periodo (id_periodo, nome_periodo, descricao_periodo, data_inicio_periodo, data_termino_periodo, is_periodo_letivo)
VALUES (1,'PERÍODO LETIVO - 2016.1','PERÍODO LETIVO - 2016.1','2016-02-01','2016-06-18', 1);

-- Tabela horario
INSERT INTO horario (id_horario, horario, inicio_horario, termino_horario, horario_intermediario, turno, inicio_horario_absoluto, termino_horario_absoluto) VALUES
(1, 1, '06:30:00', '07:50:59', '07:26:00', 'M', '07:00:00', '07:50:00'),
(2, 2, '07:51:00', '08:40:59', '08:15:00', 'M', '07:50:00', '08:40:00'),
(3, 3, '08:41:00', '09:45:59', '09:20:00', 'M', '08:55:00', '09:45:00'),
(4, 4, '09:46:00', '10:35:59', '10:10:00', 'M', '09:45:00', '10:35:00'),
(5, 5, '10:36:00', '11:40:59', '11:15:00', 'M', '10:50:00', '11:40:00'),
(6, 6, '11:41:00', '12:30:59', '12:05:00', 'M', '11:40:00', '12:30:00'),
(7, 1, '12:31:00', '13:50:59', '13:25:00', 'T', '13:00:00', '13:50:00'),
(8, 2, '13:51:00', '14:40:59', '13:15:00', 'T', '13:50:00', '14:40:00'),
(9, 3, '14:41:00', '15:45:59', '15:20:00', 'T', '14:55:00', '15:45:00'),
(10, 4, '15:46:00', '16:35:59', '16:10:00', 'T', '15:45:00', '16:35:00'),
(11, 5, '16:36:00', '17:40:59', '17:15:00', 'T', '16:50:00', '17:40:00'),
(12, 6, '17:41:00', '18:30:59', '18:05:00', 'T', '17:40:00', '18:30:00'),
(13, 1, '18:31:00', '19:35:59', '19:10:00', 'N', '18:45:00', '19:35:00'),
(14, 2, '19:36:00', '20:25:59', '20:00:00', 'N', '19:35:00', '20:25:00'),
(15, 3, '20:26:00', '21:25:59', '21:00:00', 'N', '20:35:00', '21:25:00'),
(16, 4, '21:26:00', '23:00:00', '21:50:00', 'N', '21:25:00', '22:15:00');

-- Tabela parametros_relatorios
INSERT INTO parametros_relatorios (id_parametros_relatorios, identificador_parametro_relatorios, texto_parametro_relatorios) 
VALUES 
(1, "PRIMEIRA_LINHA_CABECALHO", "UNIVERSIDADE FEDERAL DO RIO GRANDE DO NORTE"),
(2, "SEGUNDA_LINHA_CABECALHO", "ESCOLA AGRÍCOLA DE JUNDIAÍ"),
(3, "TERCEIRA_LINHA_CABECALHO", "COORDENAÇÃO DOS CURSOS DE GRADUAÇÃO");

-- Tabela departamento
INSERT INTO departamento (id_departamento, nome_departamento, descricao_departamento) VALUES 
(1, 'Departamento de Agronomia', 'Departamento do Curso de Agronomia'),
(2, 'Departamento de Análise e Desenvolvimento de Sistemas', 'Departamento do Curso de Análise e Desenvolvimento de Sistemas'),
(3, 'Departamento de Engenharia Florestal', 'Departamento do Curso de Engenharia Florestal'),
(4, 'Departamento de Zootecnia', 'Departamento do Curso de Zootecnia');

-- Tabela turma
INSERT INTO turma (id_turma, codigo_componente_turma, nome_componente_turma, nome_docente_turma, horario_turma, capacidade_turma, 
numero_turma, alunos_matriculados_turma, distribuir, local, id_perfil, id_predio, id_periodo, 
id_sala_temp, id_departamento) 
VALUES 
(1,'ARQ0243','MAQUETES E PROTOTIPOS','PAULO HEIDER FORTE FEIJO','246T456',20,1,0,1,'INDEFINIDO',1,1,1,114,1),
(2,'ARQ0244','MAQUETES E PROTOTIPOS II','PAULO HEIDER FORTE FEIJO','2M12 35N12 7M1234',20,1,0,1,'INDEFINIDO',1,1,1,114,1),
(3,'ARQ0510','INTRODUÇÃO À ARQUITETURA E AO URBANISMO','GIOVANA PAIVA DE OLIVEIRA','2M123',22,1,0,1,'INDEFINIDO',2,1,1,54,4),
(5,'ARQ0511','GEOMETRIA GRAFICA 01','A DEFINIR DOCENTE','35M456',22,1,0,1,'INDEFINIDO',2,1,1,54,1),
(6,'CIV0418','RESISTÊNCIA DOS MATERIAIS II','DESIREÉ ALVES DE OLIVEIRA','247T34',40,2,0,1,'INDEFINIDO',1,1,1,19,2),
(7,'CIV0419','ECONOMIA DA CONSTRUÇÃO E INFRAESTRUTURA','RUBENS EUGENIO BARRETO RAMOS','24M56',50,1,0,1,'INDEFINIDO',1,1,1,4,2),
(8,'DCO0013','SERVIÇOS DE TELECOMUNICAÇÕES','CLAUDIO RODRIGUES MUNIZ DA SILVA','6N1234',25,1,0,1,'INDEFINIDO',1,1,1,NULL,3),
(9,'DCO0015','COMUNICAÇÕES MÓVEIS','LAERCIO MARTINS DE MENDONCA','35N34 2T456',25,1,0,1,'INDEFINIDO',1,1,1,NULL,3),
(10,'DCO0016','COMUNICAÇÕES MÓVEIS II','LAERCIO MARTINS DE MENDONCA','35T34 467M12',25,1,0,1,'INDEFINIDO',2,1,1,NULL,4);