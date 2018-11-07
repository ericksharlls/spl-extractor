package br.ufrn.ct.cronos.service.consultarsalasparamarcacoes;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.consultarsalasparamarcacoes.vo.ConsultarSalasParaMarcacoes;
import br.ufrn.ct.cronos.consultarsalasparamarcacoes.vo.DadosConsultarSalasParaMarcacoes;
import br.ufrn.ct.cronos.consultarsalasparamarcacoes.vo.RespostaConsultarSalasParaMarcacoes;
import br.ufrn.ct.cronos.dao.AgendamentoDao;
import br.ufrn.ct.cronos.dao.PerfilSalaTurmaDao;
import br.ufrn.ct.cronos.distribuirturmas.vo.Auxiliar;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceConsultarSalasParaMarcacoesFuturo extends
         AbstractService<ConsultarSalasParaMarcacoes, RespostaConsultarSalasParaMarcacoes> {

   private PerfilSalaTurmaDao perfilSalaTurmaDao;
   private AgendamentoDao agendamentoDao;

   public RespostaConsultarSalasParaMarcacoes processa(ConsultarSalasParaMarcacoes solicitacao) throws NegocioException {
      List<DadosConsultarSalasParaMarcacoes> dados = new ArrayList<DadosConsultarSalasParaMarcacoes>();

      Integer totalNumeroLinhas = Integer.valueOf(dados.size());

      return new RespostaConsultarSalasParaMarcacoes(dados, totalNumeroLinhas);
   }

   public void valida(ConsultarSalasParaMarcacoes solicitacao) throws NegocioException {
      Auxiliar auxiliar = new Auxiliar();
      if ((solicitacao.getHorarioTurma().equals("")) || (!auxiliar.validarHorarioComSabado(solicitacao.getHorarioTurma()))) {
         throw new NegocioException(34);
      }

      for (int i = 0; i < auxiliar.contadorDeGruposComSabado(solicitacao.getHorarioTurma()); i++) {
         String grupo = auxiliar.retornaGrupoComSabado(solicitacao.getHorarioTurma(), i);
         String[] dias = auxiliar.retornaArrayDias(grupo);
         if (dias.length > 1)
            throw new NegocioException(48);
      }
   }

   public void setPerfilSalaTurmaDao(PerfilSalaTurmaDao perfilSalaTurmaDao) {
      this.perfilSalaTurmaDao = perfilSalaTurmaDao;
   }

   public void setAgendamentoDao(AgendamentoDao agendamentoDao) {
      this.agendamentoDao = agendamentoDao;
   }

}
