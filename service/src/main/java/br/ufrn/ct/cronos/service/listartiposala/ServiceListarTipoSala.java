package br.ufrn.ct.cronos.service.listartiposala;
 
import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.dao.PerfilSalaTurmaDao;
import br.ufrn.ct.cronos.entity.PerfilSalaTurma;
import br.ufrn.ct.cronos.listartiposala.vo.DadosListarTipoSala;
import br.ufrn.ct.cronos.listartiposala.vo.ListarTipoSala;
import br.ufrn.ct.cronos.listartiposala.vo.RespostaListarTipoSala;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceListarTipoSala extends AbstractService<ListarTipoSala, RespostaListarTipoSala>{

   private PerfilSalaTurmaDao perfilSalaTurmaDao;
 
	public RespostaListarTipoSala processa(ListarTipoSala solicitacao) throws NegocioException {
      List<PerfilSalaTurma> lista = this.perfilSalaTurmaDao.findAll();
		List<DadosListarTipoSala> dadosListarSala = new ArrayList<DadosListarTipoSala>(lista.size());
		for (PerfilSalaTurma tipoSala : lista) {
			DadosListarTipoSala dados = new DadosListarTipoSala();
			dados.setId(tipoSala.getId());
			dados.setNome(tipoSala.getNome());
			dadosListarSala.add(dados);
		}
		return new RespostaListarTipoSala(dadosListarSala);
   }
 
	public void valida(ListarTipoSala solicitacao) throws NegocioException {
		
	}
 
   public void setPerfilSalaTurmaDao(PerfilSalaTurmaDao perfilSalaTurmaDao) {
      this.perfilSalaTurmaDao = perfilSalaTurmaDao;
   }

}

