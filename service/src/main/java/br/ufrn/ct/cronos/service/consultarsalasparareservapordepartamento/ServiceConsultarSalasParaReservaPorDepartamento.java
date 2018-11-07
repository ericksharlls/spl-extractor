package br.ufrn.ct.cronos.service.consultarsalasparareservapordepartamento;

import br.ufrn.ct.cronos.consultarsalasparareservapordepartamento.vo.ConsultarSalasParaReservaPorDepartamento;
import br.ufrn.ct.cronos.consultarsalasparareservapordepartamento.vo.RespostaConsultarSalasParaReservaPorDepartamento;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.distribuirturmas.vo.Auxiliar;
import br.ufrn.ct.cronos.error.ErrorCode;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceConsultarSalasParaReservaPorDepartamento extends
         AbstractService<ConsultarSalasParaReservaPorDepartamento, RespostaConsultarSalasParaReservaPorDepartamento> {

   private FuncionarioDao funcionarioDao;

   @Override
   public RespostaConsultarSalasParaReservaPorDepartamento processa(ConsultarSalasParaReservaPorDepartamento solicitacao)
      throws NegocioException {
      return null;
   }

   @Override
   public void valida(ConsultarSalasParaReservaPorDepartamento solicitacao) throws NegocioException {
      if (solicitacao.getIdPeriodo() == 0 || solicitacao.getIdPeriodo() == null) {
         throw new NegocioException(ErrorCode.ID_PERIODO);
      }

      if (solicitacao.getIdPredio() == 0 || solicitacao.getIdPredio() == null) {
         throw new NegocioException(ErrorCode.ID_PREDIO);
      }
      Auxiliar auxiliar = new Auxiliar();
      if ((solicitacao.getHorarioUm().equals("")) || (!auxiliar.validarHorarioComSabado(solicitacao.getHorarioUm()))) {
         throw new NegocioException(ErrorCode.HORARIO_VAZIO_OU_INVALIDO);
      }
      if ((solicitacao.getHorarioDois().equals("")) || (!auxiliar.validarHorarioComSabado(solicitacao.getHorarioDois()))) {
         throw new NegocioException(ErrorCode.HORARIO_VAZIO_OU_INVALIDO);
      }
      if ((solicitacao.getHorarioTres().equals("")) || (!auxiliar.validarHorarioComSabado(solicitacao.getHorarioTres()))) {
         throw new NegocioException(ErrorCode.HORARIO_VAZIO_OU_INVALIDO);
      }
      if (auxiliar.retornaTurno(solicitacao.getHorarioUm()).equals(auxiliar.retornaTurno(solicitacao.getHorarioDois()))) {
         throw new NegocioException(ErrorCode.TURNOS_IGUAIS);
      }
      if (auxiliar.retornaTurno(solicitacao.getHorarioUm()).equals(auxiliar.retornaTurno(solicitacao.getHorarioTres()))) {
         throw new NegocioException(ErrorCode.TURNOS_IGUAIS);
      }
      if (auxiliar.retornaTurno(solicitacao.getHorarioDois()).equals(auxiliar.retornaTurno(solicitacao.getHorarioTres()))) {
         throw new NegocioException(ErrorCode.TURNOS_IGUAIS);
      }
   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

}
