package br.ufrn.ct.cronos.service.listarperfil;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.dao.PapelUsuarioDao;
import br.ufrn.ct.cronos.entity.PapelUsuario;
import br.ufrn.ct.cronos.listarperfil.vo.DadosListarPerfil;
import br.ufrn.ct.cronos.listarperfil.vo.ListarPerfil;
import br.ufrn.ct.cronos.listarperfil.vo.RespostaListarPerfil;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;


public class ServiceListarPerfil extends AbstractService<ListarPerfil, RespostaListarPerfil>{
	
   private PapelUsuarioDao papelUsuarioDao;

	
	public RespostaListarPerfil processa(ListarPerfil solicitacao) throws NegocioException{
      List<PapelUsuario> lista = this.papelUsuarioDao.findAll();
		List<DadosListarPerfil> dadosListarPerfil = new ArrayList<DadosListarPerfil>(lista.size());
		for (PapelUsuario perfil : lista) {
			DadosListarPerfil dados = new DadosListarPerfil();
			dados.setId(perfil.getId());
			dados.setNome(perfil.getNome());
			dadosListarPerfil.add(dados);
		}
		return new RespostaListarPerfil(dadosListarPerfil);
	}

	public void valida(ListarPerfil solicitacao) throws NegocioException{
		
	}

   public void setPapelUsuarioDao(PapelUsuarioDao papelUsuarioDao) {
      this.papelUsuarioDao = papelUsuarioDao;
	}
	
}

