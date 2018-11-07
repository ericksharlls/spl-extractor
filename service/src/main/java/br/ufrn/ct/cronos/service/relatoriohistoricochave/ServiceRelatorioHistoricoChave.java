package br.ufrn.ct.cronos.service.relatoriohistoricochave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import br.ufrn.ct.cronos.dao.ChaveDao;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.dao.HistoricoChaveDao;
import br.ufrn.ct.cronos.dao.OperacaoDao;
import br.ufrn.ct.cronos.dao.ParametrosRelatoriosDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.dao.SalaDao;
import br.ufrn.ct.cronos.entity.Chave;
import br.ufrn.ct.cronos.entity.Funcionario;
import br.ufrn.ct.cronos.entity.HistoricoChave;
import br.ufrn.ct.cronos.entity.Operacao;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.relatoriohistoricochave.vo.DadosHistoricoChave;
import br.ufrn.ct.cronos.relatoriohistoricochave.vo.DadosRelatorioUtilizacaoChaves;
import br.ufrn.ct.cronos.relatoriohistoricochave.vo.RelatorioHistoricoChave;
import br.ufrn.ct.cronos.relatoriohistoricochave.vo.RespostaRelatorioHistoricoChave;
import br.ufrn.ct.cronos.service.ManipuladorDatas;
import br.ufrn.ct.cronos.service.ParametrosRelatoriosEnum;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceRelatorioHistoricoChave extends AbstractService<RelatorioHistoricoChave, RespostaRelatorioHistoricoChave> {

   private HistoricoChaveDao historicoChaveDao;
   private ChaveDao chaveDao;
   private SalaDao salaDao;
   private FuncionarioDao funcionarioDao;
   private PeriodoDao periodoDao;
   private OperacaoDao operacaoDao;
   private ParametrosRelatoriosDao parametrosRelatoriosDao;

   @Override
   public RespostaRelatorioHistoricoChave processa(RelatorioHistoricoChave solicitacao) throws NegocioException {
      List<HistoricoChave> lista = this.historicoChaveDao.getHistoricoChavePorData(solicitacao.getData());
      List<DadosRelatorioUtilizacaoChaves> dados = new ArrayList<DadosRelatorioUtilizacaoChaves>(0);
      List<DadosHistoricoChave> dadosHistoricoChave = new ArrayList<DadosHistoricoChave>(0);

      DadosRelatorioUtilizacaoChaves dadosRelatorioUtilizacaoChaves = new DadosRelatorioUtilizacaoChaves();
      dadosRelatorioUtilizacaoChaves.setPrimeiraLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.PRIMEIRA_LINHA_CABECALHO.name()).getTexto());
      dadosRelatorioUtilizacaoChaves.setSegundaLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.SEGUNDA_LINHA_CABECALHO.name()).getTexto());
      dadosRelatorioUtilizacaoChaves.setTerceiraLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.TERCEIRA_LINHA_CABECALHO.name()).getTexto());
      dadosRelatorioUtilizacaoChaves.setSemestre(this.periodoDao.getPeriodoLetivoAtualOuProximo().getNome());

      dadosRelatorioUtilizacaoChaves.setData(ManipuladorDatas.retornaStringDataFormatoDiaMesAno(solicitacao.getData()));

      for (HistoricoChave historicoChave : lista) {
         Chave chave = this.chaveDao.get(historicoChave.getIdChave());
         Sala sala = this.salaDao.get(chave.getIdSala());
         Operacao operacao = this.operacaoDao.get(historicoChave.getIdOperacao());
         Funcionario responsavel = this.funcionarioDao.get(historicoChave.getIdResponsavel());
         DadosHistoricoChave dHC = new DadosHistoricoChave();
         dHC.setDescricaoChave(chave.getDescricao());
         dHC.setSala(sala.getNome());
         dHC.setHorario(ManipuladorDatas.retornaStringDataFormatoHoraMinutoSegundo(historicoChave.getHoraRealizacao()));
         dHC.setOperacao(operacao.getNome());
         dHC.setResponsavel(responsavel.getNome());
         dadosHistoricoChave.add(dHC);
      }
      Collections.sort(dadosHistoricoChave, new Comparator<DadosHistoricoChave>() {

         public int compare(DadosHistoricoChave hc1, DadosHistoricoChave hc2) {
            // return hc1.getSala().compareTo(hc2.getSala());
            int resultado = hc1.getSala().compareTo(hc2.getSala());
            return resultado == 0 ? hc1.getHorario().compareTo(hc2.getHorario()) : resultado;
         }
      });

      /*
       * for (HistoricoChave historicoChave : lista) { DadosHistoricoChave dHC = new DadosHistoricoChave(); Chave chave =
       * this.chaveDao.get(historicoChave.getIdChave()); dHC.setDescricaoChave(chave.getDescricao()); // List<Sala> salasComOcorrenciaChave
       * = this.salaDao.getSalasComOcorrenciasDeChave(solicitacao.getData()); // dHC.setHoraDevolucao(historicoChave.get); //
       * dHC.setHoraEntrega(horaEntrega); HistoricoChave[] arrayListaHistoricoChave = (HistoricoChave[]) lista.toArray(new
       * HistoricoChave[lista.size()]); for (int i = 0; i < arrayListaHistoricoChave.length; i++) { if (i < arrayListaHistoricoChave.length
       * - 1) { if (arrayListaHistoricoChave[i].getIdChave().equals(arrayListaHistoricoChave[i + 1].getIdChave())) { if
       * (arrayListaHistoricoChave[i].getIdOperacao().equals(new Long(2))) {
       * dHC.setHoraEntrega(ManipuladorDatas.retornaStringDataFormatoHoraMinutoSegundo(arrayListaHistoricoChave[i] .getHoraRealizacao()));
       * dHC.setHoraDevolucao(ManipuladorDatas.retornaStringDataFormatoHoraMinutoSegundo(arrayListaHistoricoChave[i + 1]
       * .getHoraRealizacao())); } else {
       * dHC.setHoraDevolucao(ManipuladorDatas.retornaStringDataFormatoHoraMinutoSegundo(arrayListaHistoricoChave[i] .getHoraRealizacao()));
       * dHC.setHoraEntrega(ManipuladorDatas.retornaStringDataFormatoHoraMinutoSegundo(arrayListaHistoricoChave[i + 1]
       * .getHoraRealizacao())); } if (i + 1 == arrayListaHistoricoChave.length) { break; } else { i = i + 2; } } else { if
       * (arrayListaHistoricoChave[i].getIdOperacao().equals(new Long(2))) {
       * dHC.setHoraEntrega(ManipuladorDatas.retornaStringDataFormatoHoraMinutoSegundo(arrayListaHistoricoChave[i] .getHoraRealizacao())); }
       * else { dHC.setHoraDevolucao(ManipuladorDatas.retornaStringDataFormatoHoraMinutoSegundo(arrayListaHistoricoChave[i]
       * .getHoraRealizacao())); } } } else { if (arrayListaHistoricoChave[i].getIdOperacao().equals(new Long(2))) {
       * dHC.setHoraEntrega(ManipuladorDatas.retornaStringDataFormatoHoraMinutoSegundo(arrayListaHistoricoChave[i] .getHoraRealizacao())); }
       * else { dHC.setHoraDevolucao(ManipuladorDatas.retornaStringDataFormatoHoraMinutoSegundo(arrayListaHistoricoChave[i]
       * .getHoraRealizacao())); } } } Funcionario responsavel = this.funcionarioDao.get(historicoChave.getIdResponsavel());
       * dHC.setResponsavel(responsavel.getNome()); dHC.setSala(this.salaDao.get(chave.getIdSala()).getNome());
       * dadosHistoricoChave.add(dHC); }
       */

      dadosRelatorioUtilizacaoChaves.setHistoricosDasChaves(dadosHistoricoChave);
      dados.add(dadosRelatorioUtilizacaoChaves);
      return new RespostaRelatorioHistoricoChave(dados);
   }

   @Override
   public void valida(RelatorioHistoricoChave solicitacao) throws NegocioException {
   }

   public void setHistoricoChaveDao(HistoricoChaveDao historicoChaveDao) {
      this.historicoChaveDao = historicoChaveDao;
   }

   public void setSalaDao(SalaDao salaDao) {
      this.salaDao = salaDao;
   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

   public void setParametrosRelatoriosDao(ParametrosRelatoriosDao parametrosRelatoriosDao) {
      this.parametrosRelatoriosDao = parametrosRelatoriosDao;
   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

   public void setChaveDao(ChaveDao chaveDao) {
      this.chaveDao = chaveDao;
   }

   public void setOperacaoDao(OperacaoDao operacaoDao) {
      this.operacaoDao = operacaoDao;
   }

}
