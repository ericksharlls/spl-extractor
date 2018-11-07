SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `db_cronos_2014` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `db_cronos_2014` ;

-- -----------------------------------------------------
-- Table `db_cronos_2014`.`funcionario`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`funcionario` (
  `id_funcionario` SMALLINT NOT NULL AUTO_INCREMENT ,
  `nome_funcionario` VARCHAR(50) NOT NULL ,
  `matricula_funcionario` VARCHAR(11) NULL ,
  `cpf_funcionario` VARCHAR(11) NULL ,
  `email_funcionario` VARCHAR(30) NOT NULL ,
  `telefone_funcionario` VARCHAR(8) NOT NULL ,
  PRIMARY KEY (`id_funcionario`) )
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_cronos_2014`.`periodo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`periodo` (
  `id_periodo` TINYINT NOT NULL AUTO_INCREMENT ,
  `nome_periodo` VARCHAR(30) NOT NULL ,
  `descricao_periodo` VARCHAR(50) NOT NULL ,
  `data_inicio_periodo` DATE NOT NULL ,
  `data_termino_periodo` DATE NOT NULL ,
  `is_periodo_letivo` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`id_periodo`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_cronos_2014`.`agendamento`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`agendamento` (
  `id_agendamento` MEDIUMINT NOT NULL AUTO_INCREMENT ,
  `motivo` VARCHAR(50) NOT NULL ,
  `id_funcionario` SMALLINT NOT NULL ,
  `id_periodo` TINYINT NOT NULL ,
  PRIMARY KEY (`id_agendamento`) ,
  INDEX `fk_agendamento_funcionario1_idx` (`id_funcionario` ASC) ,
  INDEX `fk_agendamento_periodo1_idx` (`id_periodo` ASC) ,
  CONSTRAINT `fk_agendamento_funcionario1`
    FOREIGN KEY (`id_funcionario` )
    REFERENCES `db_cronos_2014`.`funcionario` (`id_funcionario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_agendamento_periodo1`
    FOREIGN KEY (`id_periodo` )
    REFERENCES `db_cronos_2014`.`periodo` (`id_periodo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_cronos_2014`.`tipo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`tipo` (
  `id_tipo` TINYINT NOT NULL AUTO_INCREMENT ,
  `nome_tipo` VARCHAR(50) NOT NULL ,
  `descricao_tipo` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id_tipo`) )
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_cronos_2014`.`predio`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`predio` (
  `id_predio` TINYINT NOT NULL AUTO_INCREMENT ,
  `nome_predio` VARCHAR(50) NOT NULL ,
  `descricao_predio` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id_predio`) )
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_cronos_2014`.`sala`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`sala` (
  `id_sala` SMALLINT NOT NULL AUTO_INCREMENT ,
  `nome_sala` VARCHAR(50) NOT NULL ,
  `descricao_sala` VARCHAR(100) NOT NULL ,
  `capacidade_sala` SMALLINT NOT NULL ,
  `tipo_quadro_sala` VARCHAR(50) NOT NULL ,
  `utilizar_distribuicao` TINYINT(1) NOT NULL DEFAULT 1 ,
  `utilizar_agendamento` TINYINT(1) NOT NULL DEFAULT 1 ,
  `distribuir` TINYINT(1) NOT NULL DEFAULT 1 ,
  `id_tipo` TINYINT NOT NULL ,
  `id_predio` TINYINT NOT NULL ,
  PRIMARY KEY (`id_sala`) ,
  INDEX `fk_sala_tipo1_idx` (`id_tipo` ASC) ,
  INDEX `fk_sala_predio1_idx` (`id_predio` ASC) ,
  CONSTRAINT `fk_sala_tipo1`
    FOREIGN KEY (`id_tipo` )
    REFERENCES `db_cronos_2014`.`tipo` (`id_tipo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sala_predio1`
    FOREIGN KEY (`id_predio` )
    REFERENCES `db_cronos_2014`.`predio` (`id_predio` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 88
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_cronos_2014`.`agendamento_backup`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`agendamento_backup` (
  `id_agendamento` SMALLINT NOT NULL AUTO_INCREMENT ,
  `dia` TINYINT(4) NOT NULL ,
  `turno` CHAR(1) NOT NULL ,
  `horario` TINYINT(4) NOT NULL ,
  `interessado` VARCHAR(50) NOT NULL ,
  `motivo` VARCHAR(70) NOT NULL ,
  `telefone` VARCHAR(15) NOT NULL ,
  `data_agendamento` DATE NOT NULL ,
  `identificador_interessado` VARCHAR(15) NOT NULL ,
  `id_sala` SMALLINT NOT NULL ,
  PRIMARY KEY (`id_agendamento`) ,
  INDEX `fk_agendamento_backup_sala1_idx` (`id_sala` ASC) ,
  CONSTRAINT `fk_agendamento_backup_sala1`
    FOREIGN KEY (`id_sala` )
    REFERENCES `db_cronos_2014`.`sala` (`id_sala` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_cronos_2014`.`chave`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`chave` (
  `id_chave` SMALLINT NOT NULL AUTO_INCREMENT ,
  `codigo_chave` VARCHAR(20) NOT NULL ,
  `id_sala` SMALLINT NOT NULL ,
  PRIMARY KEY (`id_chave`) ,
  UNIQUE INDEX `codigo_chave_UNIQUE` (`codigo_chave` ASC) ,
  INDEX `fk_chave_sala1_idx` (`id_sala` ASC) ,
  CONSTRAINT `fk_chave_sala1`
    FOREIGN KEY (`id_sala` )
    REFERENCES `db_cronos_2014`.`sala` (`id_sala` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_cronos_2014`.`turma`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`turma` (
  `id_turma` SMALLINT NOT NULL AUTO_INCREMENT ,
  `codigo_componente_turma` VARCHAR(20) NOT NULL ,
  `nome_componente_turma` VARCHAR(70) NOT NULL ,
  `nome_docente_turma` VARCHAR(80) NOT NULL ,
  `horario_turma` VARCHAR(30) NOT NULL ,
  `capacidade_turma` SMALLINT(6) NOT NULL ,
  `departamento_turma` VARCHAR(80) NOT NULL ,
  `numero_turma` TINYINT(4) NOT NULL ,
  `alunos_matriculados_turma` SMALLINT(6) NOT NULL DEFAULT 0 ,
  `distribuir` TINYINT(1) NOT NULL DEFAULT '1' ,
  `local` VARCHAR(30) NOT NULL DEFAULT 'INDEFINIDO' ,
  `id_tipo` TINYINT NOT NULL ,
  `id_predio` TINYINT NOT NULL DEFAULT 1 ,
  `id_periodo` TINYINT NOT NULL ,
  PRIMARY KEY (`id_turma`) ,
  INDEX `fk_turma_tipo1_idx` (`id_tipo` ASC) ,
  INDEX `fk_turma_predio1_idx` (`id_predio` ASC) ,
  INDEX `fk_turma_periodo1_idx` (`id_periodo` ASC) ,
  CONSTRAINT `fk_turma_tipo1`
    FOREIGN KEY (`id_tipo` )
    REFERENCES `db_cronos_2014`.`tipo` (`id_tipo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_turma_predio1`
    FOREIGN KEY (`id_predio` )
    REFERENCES `db_cronos_2014`.`predio` (`id_predio` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_turma_periodo1`
    FOREIGN KEY (`id_periodo` )
    REFERENCES `db_cronos_2014`.`periodo` (`id_periodo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 830
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_cronos_2014`.`distribuicao`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`distribuicao` (
  `id_distribuicao` SMALLINT NOT NULL AUTO_INCREMENT ,
  `dia` TINYINT(4) NOT NULL ,
  `turno` CHAR(1) NOT NULL ,
  `horario` TINYINT(4) NOT NULL ,
  `id_sala` SMALLINT NOT NULL ,
  `id_turma` SMALLINT NOT NULL ,
  PRIMARY KEY (`id_distribuicao`) ,
  UNIQUE INDEX `distribuicao_unica` (`turno` ASC, `dia` ASC, `horario` ASC, `id_sala` ASC) ,
  INDEX `fk_distribuicao_sala1_idx` (`id_sala` ASC) ,
  INDEX `fk_distribuicao_turma1_idx` (`id_turma` ASC) ,
  CONSTRAINT `fk_distribuicao_sala1`
    FOREIGN KEY (`id_sala` )
    REFERENCES `db_cronos_2014`.`sala` (`id_sala` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_distribuicao_turma1`
    FOREIGN KEY (`id_turma` )
    REFERENCES `db_cronos_2014`.`turma` (`id_turma` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_cronos_2014`.`operacao`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`operacao` (
  `id_operacao` SMALLINT NOT NULL AUTO_INCREMENT ,
  `nome_operacao` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`id_operacao`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_cronos_2014`.`historico_chave`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`historico_chave` (
  `id_historico_chave` MEDIUMINT NOT NULL AUTO_INCREMENT ,
  `hora_realizacao_operacao` DATETIME NOT NULL ,
  `id_chave` SMALLINT NOT NULL ,
  `id_operacao` SMALLINT NOT NULL ,
  PRIMARY KEY (`id_historico_chave`) ,
  INDEX `fk_historico_chave_chave1_idx` (`id_chave` ASC) ,
  INDEX `fk_historico_chave_operacao1_idx` (`id_operacao` ASC) ,
  CONSTRAINT `fk_historico_chave_chave1`
    FOREIGN KEY (`id_chave` )
    REFERENCES `db_cronos_2014`.`chave` (`id_chave` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_historico_chave_operacao1`
    FOREIGN KEY (`id_operacao` )
    REFERENCES `db_cronos_2014`.`operacao` (`id_operacao` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_cronos_2014`.`horario`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`horario` (
  `id_horario` SMALLINT NOT NULL AUTO_INCREMENT ,
  `horario` TINYINT NOT NULL ,
  `inicio_horario` TIME NOT NULL ,
  `termino_horario` TIME NOT NULL ,
  `horario_intermediario` TIME NOT NULL ,
  `turno` CHAR(1) NOT NULL ,
  PRIMARY KEY (`id_horario`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_cronos_2014`.`perfil`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`perfil` (
  `id_perfil` TINYINT NOT NULL AUTO_INCREMENT ,
  `nome_perfil` VARCHAR(50) NOT NULL ,
  `descricao_perfil` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id_perfil`) )
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_cronos_2014`.`produto`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`produto` (
  `id_produto` SMALLINT NOT NULL AUTO_INCREMENT ,
  `nome_produto` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id_produto`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_cronos_2014`.`turma_auxiliar`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`turma_auxiliar` (
  `id_turma` SMALLINT NOT NULL AUTO_INCREMENT ,
  `codigo_componente_turma` VARCHAR(20) NOT NULL ,
  `nome_componente_turma` VARCHAR(70) NOT NULL ,
  `nome_docente_turma` VARCHAR(70) NOT NULL ,
  `horario_turma` VARCHAR(30) NOT NULL ,
  `capacidade_turma` SMALLINT NOT NULL ,
  `departamento_turma` VARCHAR(80) NOT NULL ,
  `numero_turma` TINYINT NOT NULL ,
  `alunos_matriculados_turma` SMALLINT NULL ,
  `distribuir` TINYINT(1) NOT NULL DEFAULT '1' ,
  `local` VARCHAR(30) NULL DEFAULT 'INDEFINIDO' ,
  `id_tipo` TINYINT NOT NULL ,
  `id_predio` TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`id_turma`) ,
  INDEX `fk_turma_auxiliar_tipo1_idx` (`id_tipo` ASC) ,
  INDEX `fk_turma_auxiliar_predio1_idx` (`id_predio` ASC) ,
  CONSTRAINT `fk_turma_auxiliar_tipo1`
    FOREIGN KEY (`id_tipo` )
    REFERENCES `db_cronos_2014`.`tipo` (`id_tipo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_turma_auxiliar_predio1`
    FOREIGN KEY (`id_predio` )
    REFERENCES `db_cronos_2014`.`predio` (`id_predio` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_cronos_2014`.`usuario`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`usuario` (
  `id_usuario` SMALLINT NOT NULL AUTO_INCREMENT ,
  `login_usuario` VARCHAR(20) NOT NULL ,
  `senha_usuario` VARCHAR(200) NOT NULL ,
  `ativo_usuario` TINYINT(1) NOT NULL DEFAULT '1' ,
  `tentativas_login_usuario` SMALLINT(6) NOT NULL DEFAULT '0' ,
  `id_funcionario` SMALLINT NOT NULL ,
  PRIMARY KEY (`id_usuario`) ,
  INDEX `fk_usuario_funcionario1_idx` (`id_funcionario` ASC) ,
  CONSTRAINT `fk_usuario_funcionario1`
    FOREIGN KEY (`id_funcionario` )
    REFERENCES `db_cronos_2014`.`funcionario` (`id_funcionario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_cronos_2014`.`usuario_perfil`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`usuario_perfil` (
  `id_perfil` TINYINT NOT NULL ,
  `id_usuario` SMALLINT NOT NULL ,
  PRIMARY KEY (`id_perfil`, `id_usuario`) ,
  INDEX `fk_perfil_has_usuario_usuario1_idx` (`id_usuario` ASC) ,
  INDEX `fk_perfil_has_usuario_perfil1_idx` (`id_perfil` ASC) ,
  CONSTRAINT `fk_perfil_has_usuario_perfil1`
    FOREIGN KEY (`id_perfil` )
    REFERENCES `db_cronos_2014`.`perfil` (`id_perfil` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_perfil_has_usuario_usuario1`
    FOREIGN KEY (`id_usuario` )
    REFERENCES `db_cronos_2014`.`usuario` (`id_usuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_cronos_2014`.`disponibilidade_sala`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`disponibilidade_sala` (
  `id_disponibilidade_sala` MEDIUMINT NOT NULL AUTO_INCREMENT ,
  `data_reserva` DATE NOT NULL ,
  `id_sala` SMALLINT NOT NULL ,
  `id_periodo` TINYINT NOT NULL ,
  `id_horario_sala` SMALLINT NOT NULL ,
  `id_turma` SMALLINT NULL ,
  `id_agendamento` MEDIUMINT NULL ,
  INDEX `fk_disponibilidade_dia_periodo1_idx` (`id_periodo` ASC) ,
  INDEX `fk_disponibilidade_dia_sala1_idx` (`id_sala` ASC) ,
  INDEX `fk_disponibilidade_sala_turma1_idx` (`id_turma` ASC) ,
  PRIMARY KEY (`id_disponibilidade_sala`) ,
  UNIQUE INDEX `disponibilidade_unica` (`id_sala` ASC, `data_reserva` ASC, `id_horario_sala` ASC) ,
  INDEX `fk_disponibilidade_sala_agendamento1_idx` (`id_agendamento` ASC) ,
  INDEX `fk_disponibilidade_sala_horario1_idx` (`id_horario_sala` ASC) ,
  CONSTRAINT `fk_disponibilidade_dia_periodo1`
    FOREIGN KEY (`id_periodo` )
    REFERENCES `db_cronos_2014`.`periodo` (`id_periodo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_disponibilidade_dia_sala1`
    FOREIGN KEY (`id_sala` )
    REFERENCES `db_cronos_2014`.`sala` (`id_sala` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_disponibilidade_sala_turma1`
    FOREIGN KEY (`id_turma` )
    REFERENCES `db_cronos_2014`.`turma` (`id_turma` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_disponibilidade_sala_agendamento1`
    FOREIGN KEY (`id_agendamento` )
    REFERENCES `db_cronos_2014`.`agendamento` (`id_agendamento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_disponibilidade_sala_horario1`
    FOREIGN KEY (`id_horario_sala` )
    REFERENCES `db_cronos_2014`.`horario` (`id_horario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `db_cronos_2014`.`horario_sala`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`horario_sala` (
  `id_horario_sala` TINYINT NOT NULL AUTO_INCREMENT ,
  `dia_horario_sala` CHAR(1) NOT NULL ,
  `turno_horario_sala` CHAR(1) NOT NULL ,
  `numero_horario` CHAR(1) NOT NULL ,
  PRIMARY KEY (`id_horario_sala`) ,
  UNIQUE INDEX `horario_unico` (`dia_horario_sala` ASC, `turno_horario_sala` ASC, `numero_horario` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_cronos_2014`.`feriado`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `db_cronos_2014`.`feriado` (
  `id_feriado` SMALLINT NOT NULL AUTO_INCREMENT ,
  `descricao_feriado` VARCHAR(50) NOT NULL ,
  `data_feriado` DATE NOT NULL ,
  `id_periodo` TINYINT NOT NULL ,
  PRIMARY KEY (`id_feriado`) ,
  INDEX `fk_feriado_periodo1_idx` (`id_periodo` ASC) ,
  CONSTRAINT `fk_feriado_periodo1`
    FOREIGN KEY (`id_periodo` )
    REFERENCES `db_cronos_2014`.`periodo` (`id_periodo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
