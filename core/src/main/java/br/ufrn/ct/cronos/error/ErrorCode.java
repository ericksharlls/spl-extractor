package br.ufrn.ct.cronos.error;

import dev.home.componente.service.error.ErrorCodeMySQL;

public interface ErrorCode extends ErrorCodeMySQL {
   
   public static final int ERRO_DESCONHECIDO = 1;
   public static final int ID_VAZIO = 2;
   public static final int NOME_VAZIO = 3;
   public static final int ESTIMATIVA_RESOLUCAO_VAZIO = 4;
   public static final int ESTIMATIVA_RESOLUCAO_MENOR_QUE_ZERO = 5;
   public static final int MATRICULA_VAZIO = 6;
   public static final int SALA_VAZIO = 7;
   public static final int SENHA_VAZIO = 8;
   public static final int PERFIL_VAZIO = 9;
   public static final int FUNCIONARIO_NAO_ENCONTRADO = 10;
   public static final int STATUS_NAO_ENCONTRADO = 11;
   public static final int DESCRICAO_VAZIO = 12;
      
   // Adicionando os dados do CDU Cadastrar Sala
   public static final int SALA_NAO_ENCONTRADO = 13;
   public static final int CAPACIDADE_VAZIO = 14;
   public static final int ID_TIPO_SALA = 15;
   public static final int ID_PREDIO = 16;
   public static final int TIPO_QUADRO_VAZIO = 17;
   public static final int CONEXAO_INTERNET_VAZIO = 18;
      
   // Adicionando os dados do CDU Cadastrar Predio
   public static final int PREDIO_NAO_ENCONTRADO = 19;
      
   // Adicionando os dados do CDU de Turma
   public static final int ID_PERFIL_TURMA = 20;
   public static final int TURMA_NAO_ENCONTRADO = 21;
   public static final int LOCAL_VAZIO = 22;
   public static final int LOCAL_NAO_VALIDO = 23;
      
   // Adicionando os dados do CDU de Distribuição
   public static final int NAO_PODE_REALIZAR_DISTRIBUICAO = 24;
   public static final int DISTRIBUICAO_NAO_REALIZADA = 25;
      
   // Adicionando os dados do CDU de Permutação de Turmas
   public static final int HORARIOS_NAO_IGUAIS = 26;
   public static final int PERFIS_DIFERENTES = 27;
   public static final int TURMAS_IGUAIS = 28;
      
   public static final int DEPARTAMENTO_VAZIO = 29;
   public static final int TURMA_VAZIA = 30;
   public static final int SALA_VAZIA = 31;
   public static final int NAO_PODE_REALIZAR_ALOCACAO = 32;
      
   public static final int TURMA_COM_HORARIO_INVALIDO = 33;
      
   public static final int HORARIO_VAZIO_OU_INVALIDO = 34;
      
   public static final int TURNO_VAZIO = 35;
   public static final int INTERESSADO_VAZIO = 36;
   public static final int TELEFONE_VAZIO = 37;
   public static final int MOTIVO_VAZIO = 38;
   public static final int DATA_VAZIO = 39;
   public static final int SALA_NAO_DISPONIVEL = 40;
      
   // Cadastrar Turma
   public static final int CODIGO_DISCIPLINA_VAZIO = 41;
   public static final int NOME_DISCIPLINA_VAZIO = 42;
   public static final int HORARIO_VAZIO = 43;
   public static final int NUMERO_VAZIO = 44;
   public static final int DOCENTE_VAZIO = 45;
   public static final int DATA_NAO_DISPONIVEL = 46;
   public static final int DATA_INVALIDA = 47;
   public static final int DATA_DIA_NAO_CORRESPONDENTES = 48;

   public static final int IDENTIFICADOR_INTERESSADO_VAZIO = 51;

   // CDU do Registro de Entrada/Saida de Chaves
   public static final int ID_OPERACAO_VAZIO = 55;
   public static final int CODIGO_CHAVE_VAZIO = 56;
   public static final int CHAVE_NAO_ENCONTRADA = 57;
   public static final int ENTRADA_JA_REGISTRADA = 58;
   public static final int SAIDA_JA_REGISTRADA = 59;
   public static final int OPERACAO_INVALIDA = 60;

   // CDU Desalocar Turma
   public static final int TURMA_SEM_SALA = 63;

   // CDU Cadastrar Usuario
   public static final int LOGIN_VAZIO = 66;
   public static final int SENHAS_NAO_COINCIDEM = 67;
   public static final int FUNCIONARIO_VAZIO = 68;
   public static final int FUNCIONARIO_JA_TEM_USUARIO = 69;
   public static final int LOGIN_JA_EXISTE = 70;

   // CDU de Funcionario
   public static final int EMAIL_VAZIO = 71;
   public static final int EMAIL_INVALIDO = 72;
   public static final int FUNCIONARIO_NAO_PODE_SER_EXCLUIDO = 73;
   public static final int ID_TIPO_FUNCIONARIO = 74;

   // CDU de Relatorio da utilizacao das Chaves
   public static final int DATA_INICIAL_VAZIO = 76;
   public static final int DATA_FINAL_VAZIO = 77;
   public static final int DATA_FINAL_INVALIDA = 78;
   public static final int HORARIO_OPCIONAL_INVALIDO = 80;
      
   public static final int ID_PERIODO = 83;

   // CDU Remover Sala
   public static final int SALA_POSSUI_TURMA = 84;

   // CDU Periodo Letivo
   public static final int PERIODO_LETIVO_VAZIO = 85;
   public static final int PERIODO_TEM_TURMAS_VINCULADAS = 86;

   public static final int DATA_FERIADO_VAZIO = 87;

   public static final int ERRO_COM_PERIODO_DATA = 88;

   public static final int DATA_INICIO_INVALIDA = 89;
   public static final int DATA_TERMINO_AGENDAMENTO_INVALIDA = 90;

   public static final int TURNOS_IGUAIS = 91;

   public static final int PERIODO_INVALIDO = 92;
   public static final int DATA_TERMINO_MENOR_DATA_INICIO = 93;
   
   public static final int MATRICULA_OU_CPF_VAZIO = 94;

   public static final int DATAS_NAO_CORRESPONDEM_PERIODO_INFORMADO = 95;

   public static final int FERIADO_NAO_CORRESPONDE_PERIODO_INFORMADO = 96;

   public static final int DIAS_SEMANA_NAO_INFORMADOS = 97;

   // CDU de Turma
   public static final int TURMA_JA_FOI_ALOCADA = 98;
   public static final int HORARIO_NAO_PODE_SER_ALTERADO = 99;

   // CDU Periodo Letivo
   public static final int ANO_PERIODO_VAZIO = 100;
   public static final int NUMERO_PERIODO_VAZIO = 101;

   // CDU Registrar Entrada e Saida de Chaves
   public static final int RESPONSAVEL_VAZIO = 102;

   // CDU Gerenciar Interessado
   public static final int DDD_TELEFONE_VAZIO = 103;

}
