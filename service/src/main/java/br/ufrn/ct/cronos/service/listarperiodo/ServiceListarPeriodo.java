package br.ufrn.ct.cronos.service.listarperiodo;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.entity.Periodo;
import br.ufrn.ct.cronos.listarperiodo.vo.DadosListarPeriodo;
import br.ufrn.ct.cronos.listarperiodo.vo.ListarPeriodo;
import br.ufrn.ct.cronos.listarperiodo.vo.RespostaListarPeriodo;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceListarPeriodo extends AbstractService<ListarPeriodo, RespostaListarPeriodo> {

   private PeriodoDao periodoDao;

   @Override
   public RespostaListarPeriodo processa(ListarPeriodo solicitacao) throws NegocioException {
      List<Periodo> lista = this.periodoDao.findAllOrderByDataTermino();
      List<DadosListarPeriodo> dadosListarPeriodo = new ArrayList<DadosListarPeriodo>(lista.size());
      for (Periodo periodo : lista) {
         DadosListarPeriodo dados = new DadosListarPeriodo();
         dados.setId(periodo.getId());
         dados.setNome(periodo.getNome());
         dadosListarPeriodo.add(dados);
      }
      return new RespostaListarPeriodo(dadosListarPeriodo);
   }

   @Override
   public void valida(ListarPeriodo solicitacao) throws NegocioException {
   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

}
