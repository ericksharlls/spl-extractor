﻿-- phpMyAdmin SQL Dump
-- version 3.3.2deb1ubuntu1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: Jun 14, 2013 as 10:27 AM
-- Versão do Servidor: 5.1.62
-- Versão do PHP: 5.3.2-1ubuntu4.19

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de Dados: `db_cronos`
--

USE db_cronos;


INSERT INTO `tipo` (`id_tipo`, `nome_tipo`, `descricao_tipo`) VALUES
(1, 'Convencional', 'Perfil para aulas convencionais'),
(2, 'Laboratório', 'Perfil para caracterizar realização de aulas de informática'),
(3, 'Prancheta', 'Perfil para aulas em sala de aula com prancheta');

INSERT INTO `perfil` (`id_perfil`, `nome_perfil`, `descricao_perfil`) VALUES
(1, 'ROLE_ADMINISTRADOR', 'Perfil para administradores'),
(2, 'ROLE_USUARIO', 'Perfil para usuários comuns'),
(3, 'ROLE_SUPERVISAO', 'Perfil para o pessoal da supervisão do Setor IV'),
(4, 'ROLE_DIRECAO_CT', 'Perfil para o pessoal da direção do CT');

INSERT INTO `funcionario` (`id_funcionario`, `nome_funcionario`, `matricula_funcionario`, `cpf_funcionario`, `email_funcionario`) VALUES
(1, 'Funcionario ADMIN', 'pitagoras', NULL, 'funcionario_admin@ct.ufrn.br'),
(2, 'Supervisão do Setor IV', 'supervisao', NULL, 'supervisao@ct.ufrn.br'),
(3, 'José Daniel Melo', '1202134', NULL, 'daniel.melo@ufrnet.br '),
(4, 'João Bosco', '0347304', NULL, 'bosco@dem.ufrn.br');


INSERT INTO `usuario` (`id_usuario`, `login_usuario`, `senha_usuario`, `ativo_usuario`, `tentativas_login_usuario`, `id_funcionario`) VALUES
(1, 'pitagoras', 'a688a47ac73fb58ce3828bcb184cb157', 1, 0, 1),
(2, 'supervisao', '632b62a30539c7eea1b8fe4eaf133970', 1, 0, 2),
(3, 'daniel', '71b3b26aaa319e0cdf6fdb8429c112b0', 1, 0, 3),
(4, 'bosco', '71b3b26aaa319e0cdf6fdb8429c112b0', 1, 0, 4);


INSERT INTO `usuario_perfil` (`id_usuario`, `id_perfil`) VALUES
(1, 1),
(2, 3),
(3, 4),
(4, 4);



INSERT INTO `operacao` (`id_operacao`, `nome_operacao`) VALUES
(1, 'ENTRADA'),
(2, 'SAÍDA');


INSERT INTO `predio` (`id_predio`, `nome_predio`, `descricao_predio`) VALUES
(1, 'Setor IV', 'Prédio do Setor IV de Aulas'),
(2, 'Escola de Ciências e Tecnologia', 'Prédio da Escola de Ciências e Tecnologia'),
(3, 'Núcleo de Tecnologia Industrial', 'Prédio do Núcleo de Tecnologia Industrial'),
(4, 'Laboratório de Arquitetura', 'Prédio do Laboratório de Arquitetura');

INSERT INTO `produto` (`id_produto`, `nome_produto`) VALUES
(1, 'açúcar refinado triturado'),
(2, 'água sanitária'),
(3, 'café torrado e muido'),
(4, 'copo plástico descartável para água'),
(5, 'desenfetante bactericida'),
(6, 'leite em pó integral'),
(7, 'marcador descartável para quadro branco'),
(8, 'marcador descartável para quadro(preto)'),
(9, 'papel higiênico em rolo'),
(10, 'sabão em pó'),
(11, 'sabonete líquido neutro'),
(12, 'saco plástico para lixo'),
(13, 'saco plástico para lixo(30 litros)'),
(14, 'toalha de papel');

INSERT INTO `sala` (`id_sala`, `nome_sala`, `descricao_sala`, `capacidade_sala`, `tipo_quadro_sala`, `utilizar_distribuicao`, `utilizar_agendamento`, `distribuir`, `id_predio`, `id_tipo`) VALUES
(1, '4A1', '4A1', 50, 'Branco', 1, 1, 1, 1, 1),
(2, '4A2', '4A2', 50, 'Branco', 1, 1, 1, 1, 1),
(3, '4A3', '4A3', 50, 'Branco', 1, 1, 1, 1, 1),
(4, '4A4', '4A4', 50, 'Branco', 1, 1, 1, 1, 1),
(5, '4A5', '4A5', 50, 'Branco', 1, 1, 1, 1, 1),
(6, '4A6', '4A6', 30, 'Branco', 1, 1, 1, 1, 1),
(7, '4A7', '4A7', 50, 'Branco', 1, 1, 1, 1, 1),
(8, '4A8', '4A8', 50, 'Branco', 1, 1, 1, 1, 1),
(9, '4A9', '4A9', 50, 'Branco', 1, 1, 1, 1, 1),
(10, '4A10', '4A10', 50, 'Branco', 1, 1, 1, 1, 1),
(11, '4B1', '4B1', 60, 'Branco', 1, 1, 1, 1, 1),
(12, '4B2', '4B2', 60, 'Branco', 1, 1, 1, 1, 1),
(13, '4B3', '4B3', 60, 'Branco', 1, 1, 1, 1, 1),
(14, '4B4', '4B4', 20, 'Branco', 1, 1, 1, 1, 1),
(15, '4C1', '4C1', 50, 'Branco', 1, 1, 1, 1, 1),
(16, '4C2', '4C2', 50, 'Branco', 1, 1, 1, 1, 1),
(17, '4C3', '4C3', 35, 'Branco', 1, 1, 1, 1, 1),
(18, '4C4', '4C4', 40, 'Branco', 1, 1, 1, 1, 1),
(19, '4C5', '4C5', 45, 'Branco', 1, 1, 1, 1, 1),
(20, '4C6', '4C6', 1, 'Branco', 1, 1, 0, 1, 1),
(23, '4E3', '4E3', 60, 'Branco', 1, 1, 1, 1, 2),
(24, '4E5', '4E5', 50, 'Branco', 1, 1, 1, 1, 2),
(25, '4E6', '4E6', 50, 'Branco', 1, 1, 1, 1, 2),
(26, '4F1', '4F1', 35, 'Branco', 1, 1, 1, 1, 1),
(27, '4F2', '4F2', 40, 'Branco', 1, 1, 1, 1, 1),
(28, '4F3', '4F3', 40, 'Branco', 1, 1, 1, 1, 1),
(29, '4F4', '4F4', 50, 'Branco', 1, 1, 1, 1, 1),
(32, '4G2', '4G2', 50, 'Branco', 1, 1, 1, 1, 1),
(33, '4G3', '4G3', 50, 'Branco', 1, 1, 1, 1, 1),
(35, '4G4', '4G4', 40, 'Branco', 1, 1, 1, 1, 1),
(36, '4G5', '4G5', 60, 'Branco', 1, 1, 1, 1, 1),
(37, '4G6', '4G6', 50, 'Branco', 1, 1, 1, 1, 3),
(42, '4I2', '4I2', 50, 'Branco', 1, 1, 1, 1, 1),
(43, '4I3', '4I3', 50, 'Branco', 1, 1, 1, 1, 1),
(44, '4I4', '4I4', 60, 'Branco', 1, 1, 1, 1, 1),
(45, '4I5', '4I5', 50, 'Branco', 1, 1, 1, 1, 1),
(46, '4I6', '4I6', 50, 'Branco', 1, 1, 1, 1, 1),
(47, '4I7', '4I7', 50, 'Branco', 1, 1, 1, 1, 1),
(48, '4I8', '4I8', 50, 'Branco', 1, 1, 1, 1, 1),
(49, '4I9', '4I9', 50, 'Branco', 1, 1, 1, 1, 1),
(50, '4I10', '4I10', 60, 'Branco', 1, 1, 1, 1, 1),
(52, '4H2', '4H2', 30, 'Branco', 1, 1, 1, 1, 3),
(53, '4H3', '4H3', 50, 'Branco', 1, 1, 1, 1, 3),
(54, '4H4', '4H4', 50, 'Branco', 1, 1, 1, 1, 3),
(55, 'AUDITÓRIO', 'AUDITÓRIO', 70, 'Branco', 1, 1, 1, 1, 1),
(57, '4G1', '4G1', 50, 'Branco', 1, 1, 1, 1, 2),
(59, '4B5', '4B5', 20, 'Branco', 1, 1, 1, 1, 1),
(61, 'Laboratório de Maquetes', 'Lab. Arquitetura', 60, 'Branco', 1, 1, 1, 4, 1),
(77, '4F5', '4F5', 60, 'Branco', 1, 1, 0, 1, 1),
(78, 'LABINFO', 'LABINFO', 60, 'Branco', 1, 1, 1, 4, 1),
(79, 'LABCOM', 'LABCOM', 55, 'Branco', 1, 1, 1, 4, 3),
(80, 'LABTEX', 'LABTEX', 60, 'Negro', 1, 1, 1, 4, 1),
(81, 'LABORATÓRIO DE MALHARIA', 'LABORATÓRIO DE MALHARIA', 50, 'Negro/Branco', 1, 1, 1, 4, 1),
(82, 'LABORATÓRIO DE VESTUÁRIO', 'LABORATÓRIO DE VESTUÁRIO', 55, 'Branco', 1, 1, 1, 4, 1),
(83, 'LAB. DE CAD/CAN', 'LAB. DE CAD/CAN', 55, 'Branco', 1, 1, 1, 4, 1),
(84, 'S.A LABTEX', 'S.A LABTEX', 99, 'Negro', 1, 1, 1, 4, 1),
(85, '4H1', '4H1', 30, 'Branco', 1, 1, 1, 1, 3),
(86, 'D2', 'INFORMÁTICA', 40, 'Branco', 1, 1, 1, 1, 2);