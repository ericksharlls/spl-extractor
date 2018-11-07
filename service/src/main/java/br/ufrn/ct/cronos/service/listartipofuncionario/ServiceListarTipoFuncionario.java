package br.ufrn.ct.cronos.service.listartipofuncionario;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.dao.TipoFuncionarioDao;
import br.ufrn.ct.cronos.entity.TipoFuncionario;
import br.ufrn.ct.cronos.listartipofuncionario.vo.DadosListarTipoFuncionario;
import br.ufrn.ct.cronos.listartipofuncionario.vo.ListarTipoFuncionario;
import br.ufrn.ct.cronos.listartipofuncionario.vo.RespostaListarTipoFuncionario;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceListarTipoFuncionario extends AbstractService<ListarTipoFuncionario, RespostaListarTipoFuncionario> {

   private TipoFuncionarioDao tipoFuncionarioDao;

   public RespostaListarTipoFuncionario processa(ListarTipoFuncionario solicitacao) throws NegocioException {
      List<TipoFuncionario> lista = this.tipoFuncionarioDao.findAll();
      List<DadosListarTipoFuncionario> dadosListarTipoFuncionario = new ArrayList<DadosListarTipoFuncionario>(lista.size());
      for (TipoFuncionario tipoFuncionario : lista) {
         DadosListarTipoFuncionario dados = new DadosListarTipoFuncionario();
         dados.setId(tipoFuncionario.getId());
         dados.setNome(tipoFuncionario.getNome());
         dadosListarTipoFuncionario.add(dados);
      }
      return new RespostaListarTipoFuncionario(dadosListarTipoFuncionario);
   }

   public void valida(ListarTipoFuncionario solicitacao) throws NegocioException {
   }

   public void setTipoFuncionarioDao(TipoFuncionarioDao tipoFuncionarioDao) {
      this.tipoFuncionarioDao = tipoFuncionarioDao;
   }

}
