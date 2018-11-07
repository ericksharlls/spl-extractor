package br.ufrn.ct.cronos.service.consultarferiado;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.consultarferiado.vo.ConsultarFeriado;
import br.ufrn.ct.cronos.consultarferiado.vo.DadosConsultarFeriado;
import br.ufrn.ct.cronos.consultarferiado.vo.RespostaConsultarFeriado;
import br.ufrn.ct.cronos.dao.FeriadoDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.entity.Feriado;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceConsultarFeriado extends AbstractService<ConsultarFeriado, RespostaConsultarFeriado> {

   private FeriadoDao feriadoDao;
   private PeriodoDao periodoDao;

   @Override
   public RespostaConsultarFeriado processa(ConsultarFeriado solicitacao) throws NegocioException {
      List<Feriado> lista = this.feriadoDao.findAllOrderByData(solicitacao.getStartPage(), solicitacao.getMaxPage());
      Integer totalNumeroLinhas = this.feriadoDao.count();
      List<DadosConsultarFeriado> dadosConsultarFeriado = new ArrayList<DadosConsultarFeriado>(lista.size());
      for (Feriado feriado : lista) {
         DadosConsultarFeriado dados = new DadosConsultarFeriado();
         dados.setId(feriado.getId());
         dados.setDescricao(feriado.getDescricao());
         dados.setData(feriado.getData());
         dados.setPeriodo(this.periodoDao.get(feriado.getIdPeriodo()).getNome());
         
         dadosConsultarFeriado.add(dados);
      }
      return new RespostaConsultarFeriado(dadosConsultarFeriado, totalNumeroLinhas);
   }

   @Override
   public void valida(ConsultarFeriado solicitacao) throws NegocioException {
   }

   public void setFeriadoDao(FeriadoDao feriadoDao) {
      this.feriadoDao = feriadoDao;
   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

}
