
package br.ufrn.ct.cronos.service.listarpredio;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.dao.PredioDao;
import br.ufrn.ct.cronos.entity.Predio;
import br.ufrn.ct.cronos.listarpredio.vo.DadosListarPredio;
import br.ufrn.ct.cronos.listarpredio.vo.ListarPredio;
import br.ufrn.ct.cronos.listarpredio.vo.RespostaListarPredio;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceListarPredio extends AbstractService<ListarPredio, RespostaListarPredio> {

   private PredioDao predioDao;

   public RespostaListarPredio processa(ListarPredio solicitacao) throws NegocioException {
      List<Predio> lista = this.predioDao.findAll();
      List<DadosListarPredio> dadosListarPredio = new ArrayList<DadosListarPredio>(lista.size());
      for (Predio predio : lista) {
         DadosListarPredio dados = new DadosListarPredio();
         dados.setId(predio.getId());
         dados.setNome(predio.getNome());
         dadosListarPredio.add(dados);
      }
      return new RespostaListarPredio(dadosListarPredio);
   }

   public void valida(ListarPredio solicitacao) throws NegocioException {
   }

   public void setPredioDao(PredioDao predioDao) {
      this.predioDao = predioDao;
   }

}
