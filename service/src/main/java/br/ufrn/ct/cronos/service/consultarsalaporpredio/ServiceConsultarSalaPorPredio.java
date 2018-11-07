package br.ufrn.ct.cronos.service.consultarsalaporpredio;
 
import br.ufrn.ct.cronos.consultarsalaporpredio.vo.ConsultarSalaPorPredio;
import br.ufrn.ct.cronos.consultarsalaporpredio.vo.DadosConsultarSalaPorPredio;
import br.ufrn.ct.cronos.consultarsalaporpredio.vo.RespostaConsultarSalaPorPredio;
import br.ufrn.ct.cronos.dao.SalaDao;
import br.ufrn.ct.cronos.entity.Sala;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;
import java.util.ArrayList;
import java.util.List;

public class ServiceConsultarSalaPorPredio extends AbstractService<ConsultarSalaPorPredio, RespostaConsultarSalaPorPredio> {
		
		private SalaDao salaDao;
		
		public RespostaConsultarSalaPorPredio processa(ConsultarSalaPorPredio solicitacao)	throws NegocioException {
			List<Sala> lista = this.salaDao.getByPredio(solicitacao.getIdPredio(), solicitacao.getStartPage(), solicitacao.getMaxPage());
			Integer totalNumeroLinhas = this.salaDao.countByPredio(solicitacao.getIdPredio());
			List<DadosConsultarSalaPorPredio> dadosConsultarSalaPorPredio = new ArrayList<DadosConsultarSalaPorPredio>(lista.size());
			for (Sala sala : lista) {
				DadosConsultarSalaPorPredio dados = new DadosConsultarSalaPorPredio();
				dados.setId(sala.getId());
				dados.setNome(sala.getNome());
				dados.setCapacidade(sala.getCapacidade().intValue());
				dadosConsultarSalaPorPredio.add(dados);
			}
			return new RespostaConsultarSalaPorPredio(dadosConsultarSalaPorPredio, totalNumeroLinhas);
		}
		
		public void valida(ConsultarSalaPorPredio solicitacao) throws NegocioException {
			
		}
		
		public void setSalaDao(SalaDao salaDao){
			this.salaDao = salaDao;
		}
	
}

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-service-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.service.consultarsalaporpredio.ServiceConsultarSalaPorPredio
 * JD-Core Version:    0.6.2
 */