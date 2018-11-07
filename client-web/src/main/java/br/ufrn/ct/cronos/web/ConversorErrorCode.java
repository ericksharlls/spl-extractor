
package br.ufrn.ct.cronos.web;

import java.util.ResourceBundle;
import br.ufrn.ct.cronos.error.ErrorCode;
import dev.home.componente.service.excecao.NegocioException;

public class ConversorErrorCode {

   private static ConversorErrorCode conversorErrorCode = null;

   public static ConversorErrorCode getInstance() {
      if (conversorErrorCode == null) {
         conversorErrorCode = new ConversorErrorCode();
      }
      return conversorErrorCode;
   }

   public String converte(NegocioException e) {
      String msg = null;
      int erro = e.getErrorCode();
      ResourceBundle property = ResourceBundle.getBundle("br/ufrn/ct/cronos/web/ErrorCode");
      switch (erro) {
         case ErrorCode.ID_VAZIO:
            msg = property.getString(String.valueOf(ErrorCode.ID_VAZIO));
            break;
         case ErrorCode.NOME_VAZIO:
            msg = property.getString(String.valueOf(ErrorCode.NOME_VAZIO));
            break;
         case ErrorCode.DESCRICAO_VAZIO:
            msg = property.getString(String.valueOf(ErrorCode.DESCRICAO_VAZIO));
            break;
         case 14:
            msg = property.getString(String.valueOf(14));
            break;
         case 17:
            msg = property.getString(String.valueOf(17));
            break;
         case 18:
            msg = property.getString(String.valueOf(18));
            break;
         case 15:
            msg = property.getString(String.valueOf(15));
            break;
         case 16:
            msg = property.getString(String.valueOf(16));
            break;
         case 20:
            msg = property.getString(String.valueOf(20));
            break;
         case 21:
            msg = property.getString(String.valueOf(21));
            break;
         case 22:
            msg = property.getString(String.valueOf(22));
            break;
         case 23:
            msg = property.getString(String.valueOf(23));
            break;
         case 5:
            msg = property.getString(String.valueOf(5));
            break;
         case 4:
            msg = property.getString(String.valueOf(4));
            break;
         case 1062:
            msg = property.getString(String.valueOf(1062));
            break;
         case 10:
            msg = property.getString(String.valueOf(10));
            break;
         case 11:
            msg = property.getString(String.valueOf(11));
            break;
         case 24:
            msg = property.getString(String.valueOf(24));
            break;
         case 25:
            msg = property.getString(String.valueOf(25));
            break;
         case 26:
            msg = property.getString(String.valueOf(26));
            break;
         case 27:
            msg = property.getString(String.valueOf(27));
            break;
         case 28:
            msg = property.getString(String.valueOf(28));
            break;
         case 29:
            msg = property.getString(String.valueOf(29));
            break;
         case 30:
            msg = property.getString(String.valueOf(30));
            break;
         case 31:
            msg = property.getString(String.valueOf(31));
            break;
         case 32:
            msg = property.getString(String.valueOf(32));
            break;
         case 33:
            msg = property.getString(String.valueOf(33));
            break;
         case 34:
            msg = property.getString(String.valueOf(34));
            break;
         case 35:
            msg = property.getString(String.valueOf(35));
            break;
         case 36:
            msg = property.getString(String.valueOf(36));
            break;
         case 37:
            msg = property.getString(String.valueOf(37));
            break;
         case 38:
            msg = property.getString(String.valueOf(38));
            break;
         case 39:
            msg = property.getString(String.valueOf(39));
            break;
         case 40:
            msg = property.getString(String.valueOf(40));
            break;
         case 41:
            msg = property.getString(String.valueOf(41));
            break;
         case 42:
            msg = property.getString(String.valueOf(42));
            break;
         case 43:
            msg = property.getString(String.valueOf(43));
            break;
         case 44:
            msg = property.getString(String.valueOf(44));
            break;
         case 45:
            msg = property.getString(String.valueOf(45));
            break;
         case 46:
            msg = property.getString(String.valueOf(46));
            break;
         case 47:
            msg = property.getString(String.valueOf(47));
            break;
         case 48:
            msg = property.getString(String.valueOf(48));
            break;
         case 51:
            msg = property.getString(String.valueOf(51));
            break;
         case 55:
            msg = property.getString(String.valueOf(55));
            break;
         case 56:
            msg = property.getString(String.valueOf(56));
            break;
         case 57:
            msg = property.getString(String.valueOf(57));
            break;
         case 58:
            msg = property.getString(String.valueOf(58));
            break;
         case 59:
            msg = property.getString(String.valueOf(59));
            break;
         case 63:
            msg = property.getString(String.valueOf(63));
            break;
         case 71:
            msg = property.getString(String.valueOf(71));
            break;
         case 8:
            msg = property.getString(String.valueOf(8));
            break;
         case 67:
            msg = property.getString(String.valueOf(67));
            break;
         case 6:
            msg = property.getString(String.valueOf(6));
            break;
         case 9:
            msg = property.getString(String.valueOf(9));
            break;
         case 72:
            msg = property.getString(String.valueOf(72));
            break;
         case 73:
            msg = property.getString(String.valueOf(73));
            break;
         case 66:
            msg = property.getString(String.valueOf(66));
            break;
         case 68:
            msg = property.getString(String.valueOf(68));
            break;
         case 69:
            msg = property.getString(String.valueOf(69));
            break;
         case 70:
            msg = property.getString(String.valueOf(70));
            break;
         case 76:
            msg = property.getString(String.valueOf(76));
            break;
         case 77:
            msg = property.getString(String.valueOf(77));
            break;
         case 78:
            msg = property.getString(String.valueOf(78));
            break;
         case 60:
            msg = property.getString(String.valueOf(60));
            break;
         case 80:
            msg = property.getString(String.valueOf(80));
            break;
         case 83:
            msg = property.getString(String.valueOf(83));
            break;
         case ErrorCode.PERIODO_LETIVO_VAZIO:
            msg = property.getString(String.valueOf(ErrorCode.PERIODO_LETIVO_VAZIO));
            break;
         case ErrorCode.SALA_POSSUI_TURMA:
            msg = property.getString(String.valueOf(ErrorCode.SALA_POSSUI_TURMA));
            break;
         case ErrorCode.PERIODO_TEM_TURMAS_VINCULADAS:
            msg = property.getString(String.valueOf(ErrorCode.PERIODO_TEM_TURMAS_VINCULADAS));
            break;
         case ErrorCode.DATA_FERIADO_VAZIO:
            msg = property.getString(String.valueOf(ErrorCode.DATA_FERIADO_VAZIO));
            break;
         case ErrorCode.ERRO_COM_PERIODO_DATA:
            msg = property.getString(String.valueOf(ErrorCode.ERRO_COM_PERIODO_DATA));
            break;
         case ErrorCode.DATA_INICIO_INVALIDA:
            msg = property.getString(String.valueOf(ErrorCode.DATA_INICIO_INVALIDA));
            break;
         case ErrorCode.DATA_TERMINO_AGENDAMENTO_INVALIDA:
            msg = property.getString(String.valueOf(ErrorCode.DATA_TERMINO_AGENDAMENTO_INVALIDA));
            break;
         case ErrorCode.TURNOS_IGUAIS:
            msg = property.getString(String.valueOf(ErrorCode.TURNOS_IGUAIS));
            break;
         case ErrorCode.PERIODO_INVALIDO:
            msg = property.getString(String.valueOf(ErrorCode.PERIODO_INVALIDO));
            break;
         case ErrorCode.DATA_TERMINO_MENOR_DATA_INICIO:
            msg = property.getString(String.valueOf(ErrorCode.DATA_TERMINO_MENOR_DATA_INICIO));
            break;
         case ErrorCode.MATRICULA_OU_CPF_VAZIO:
            msg = property.getString(String.valueOf(ErrorCode.MATRICULA_OU_CPF_VAZIO));
            break;
         case ErrorCode.DATAS_NAO_CORRESPONDEM_PERIODO_INFORMADO:
            msg = property.getString(String.valueOf(ErrorCode.DATAS_NAO_CORRESPONDEM_PERIODO_INFORMADO));
            break;
         case ErrorCode.FERIADO_NAO_CORRESPONDE_PERIODO_INFORMADO:
            msg = property.getString(String.valueOf(ErrorCode.FERIADO_NAO_CORRESPONDE_PERIODO_INFORMADO));
            break;
         case ErrorCode.DIAS_SEMANA_NAO_INFORMADOS:
            msg = property.getString(String.valueOf(ErrorCode.DIAS_SEMANA_NAO_INFORMADOS));
            break;
         case ErrorCode.TURMA_JA_FOI_ALOCADA:
            msg = property.getString(String.valueOf(ErrorCode.TURMA_JA_FOI_ALOCADA));
            break;
         case ErrorCode.HORARIO_NAO_PODE_SER_ALTERADO:
            msg = property.getString(String.valueOf(ErrorCode.HORARIO_NAO_PODE_SER_ALTERADO));
            break;
         case ErrorCode.ANO_PERIODO_VAZIO:
            msg = property.getString(String.valueOf(ErrorCode.ANO_PERIODO_VAZIO));
            break;
         case ErrorCode.NUMERO_PERIODO_VAZIO:
            msg = property.getString(String.valueOf(ErrorCode.NUMERO_PERIODO_VAZIO));
            break;
         case ErrorCode.ID_TIPO_FUNCIONARIO:
            msg = property.getString(String.valueOf(ErrorCode.ID_TIPO_FUNCIONARIO));
            break;
         case ErrorCode.RESPONSAVEL_VAZIO:
            msg = property.getString(String.valueOf(ErrorCode.RESPONSAVEL_VAZIO));
            break;
         case ErrorCode.DDD_TELEFONE_VAZIO:
            msg = property.getString(String.valueOf(ErrorCode.DDD_TELEFONE_VAZIO));
            break;
         default:
            msg = property.getString(String.valueOf(ErrorCode.ERRO_DESCONHECIDO));
      }

      return msg;
   }
}

