package br.ufrn.ct.cronos.service.obterferiado;

import br.ufrn.ct.cronos.dao.FeriadoDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.entity.Feriado;
import br.ufrn.ct.cronos.obterferiado.vo.DadosObterFeriado;
import br.ufrn.ct.cronos.obterferiado.vo.ObterFeriado;
import br.ufrn.ct.cronos.obterferiado.vo.RespostaObterFeriado;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceObterFeriado extends AbstractService<ObterFeriado, RespostaObterFeriado> {

   private FeriadoDao feriadoDao;
   private PeriodoDao periodoDao;

   @Override
   public RespostaObterFeriado processa(ObterFeriado solicitacao) throws NegocioException {
      Feriado feriado = this.feriadoDao.get(solicitacao.getId());

      DadosObterFeriado dados = new DadosObterFeriado();
      dados.setData(feriado.getData());
      dados.setDescricao(feriado.getDescricao());
      dados.setId(feriado.getId());
      dados.setIdPeriodo(feriado.getIdPeriodo());
      dados.setNomePeriodo(this.periodoDao.get(feriado.getIdPeriodo()).getNome());

      return new RespostaObterFeriado(dados);
   }

   @Override
   public void valida(ObterFeriado solicitacao) throws NegocioException {
   }

   public void setFeriadoDao(FeriadoDao feriadoDao) {
      this.feriadoDao = feriadoDao;
   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

}
