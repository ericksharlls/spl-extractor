package br.ufrn.ct.cronos.service.listarsalaporpredio;

import br.ufrn.ct.cronos.dao.SalaDao;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.listarsalaporpredio.vo.DadosListarSalaPorPredio;
import br.ufrn.ct.cronos.listarsalaporpredio.vo.ListarSalaPorPredio;
import br.ufrn.ct.cronos.listarsalaporpredio.vo.RespostaListarSalaPorPredio;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;
import java.util.ArrayList;
import java.util.List;

public class ServiceListarSalaPorPredio extends AbstractService<ListarSalaPorPredio, RespostaListarSalaPorPredio>{
      
      private SalaDao salaDao;
      
      @Override
      public RespostaListarSalaPorPredio processa(ListarSalaPorPredio solicitacao) throws NegocioException {
         List<Sala> salas = this.salaDao.getAllByPredio(solicitacao.getIdPredio());
         List<DadosListarSalaPorPredio> dadosListarSala = new ArrayList<DadosListarSalaPorPredio>();
         
         for (Sala s : salas) {
            DadosListarSalaPorPredio dados = new DadosListarSalaPorPredio();
            dados.setId(s.getId());
            dados.setNome(s.getNome());
            dadosListarSala.add(dados);
         }
         
         return new RespostaListarSalaPorPredio(dadosListarSala);
      }
      
      @Override
      public void valida(ListarSalaPorPredio solicitacao) throws NegocioException{
         
      }
      
      public void setSalaDao(SalaDao salaDao){
         this.salaDao = salaDao;
      }
      
}
