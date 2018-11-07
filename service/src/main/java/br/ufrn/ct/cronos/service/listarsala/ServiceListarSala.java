package br.ufrn.ct.cronos.service.listarsala;
 
import br.ufrn.ct.cronos.dao.SalaDao;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.listarsala.vo.DadosListarSala;
import br.ufrn.ct.cronos.listarsala.vo.ListarSala;
import br.ufrn.ct.cronos.listarsala.vo.RespostaListarSala;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;
import java.util.ArrayList;
import java.util.List;
 
public class ServiceListarSala extends AbstractService<ListarSala, RespostaListarSala> {
		private SalaDao salaDao;
 
		public RespostaListarSala processa(ListarSala solicitacao) throws NegocioException {
			List<Sala> salas = this.salaDao.getAllOrdemAlfabetica();
			List<DadosListarSala> dadosListarSala = new ArrayList<DadosListarSala>();

			for (Sala s : salas) {
				DadosListarSala dados = new DadosListarSala();
				dados.setId(s.getId());
				dados.setNome(s.getNome());
				dadosListarSala.add(dados);
			}

			return new RespostaListarSala(dadosListarSala);
		}
 
		public void valida(ListarSala solicitacao) throws NegocioException{
		}
 
	public void setSalaDao(SalaDao salaDao){
		this.salaDao = salaDao;
	}

}

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-service-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.service.listarsala.ServiceListarSala
 * JD-Core Version:    0.6.2
 */