package br.ufrn.ct.cronos.service.listardepartamento;
 
import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.listardepartamento.vo.DadosListarDepartamento;
import br.ufrn.ct.cronos.listardepartamento.vo.ListarDepartamento;
import br.ufrn.ct.cronos.listardepartamento.vo.RespostaListarDepartamento;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;
 
public class ServiceListarDepartamentoAntigo extends AbstractService<ListarDepartamento, RespostaListarDepartamento>{
		private TurmaDao turmaDao;
 
		public RespostaListarDepartamento processa(ListarDepartamento solicitacao) throws NegocioException {
			List<String> lista = this.turmaDao.getDepartamentos();
			List<DadosListarDepartamento> dadosListarDepartamento = new ArrayList<DadosListarDepartamento>(lista.size());
			for (String departamento : lista) {
				DadosListarDepartamento dados = new DadosListarDepartamento();
				dados.setNome(departamento);
				dadosListarDepartamento.add(dados);
			}
			return new RespostaListarDepartamento(dadosListarDepartamento);
		}
 
		public void valida(ListarDepartamento solicitacao) throws NegocioException {
   
		}
 
		public void setTurmaDao(TurmaDao turmaDao) {
			this.turmaDao = turmaDao;
		}
 
		public TurmaDao getTurmaDao() {
			return this.turmaDao;
		}

}

