package br.ufrn.ct.cronos.service.listarfuncionariodocente;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.entity.Funcionario;
import br.ufrn.ct.cronos.listarfuncionariodocente.vo.DadosListarFuncionarioDocente;
import br.ufrn.ct.cronos.listarfuncionariodocente.vo.ListarFuncionarioDocente;
import br.ufrn.ct.cronos.listarfuncionariodocente.vo.RespostaListarFuncionarioDocente;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceListarFuncionarioDocente extends AbstractService<ListarFuncionarioDocente, RespostaListarFuncionarioDocente> {

   private FuncionarioDao funcionarioDao;

   @Override
   public RespostaListarFuncionarioDocente processa(ListarFuncionarioDocente solicitacao) throws NegocioException {
      List<Funcionario> lista = this.funcionarioDao.getAllByTipoFuncionario(new Long(1));
      List<DadosListarFuncionarioDocente> dadosListarFuncionarioDocente = new ArrayList<DadosListarFuncionarioDocente>(lista.size());
      for (Funcionario funcionario : lista) {
         DadosListarFuncionarioDocente dados = new DadosListarFuncionarioDocente();
         dados.setId(funcionario.getId());
         dados.setNome(funcionario.getNome());
         dadosListarFuncionarioDocente.add(dados);
      }
      return new RespostaListarFuncionarioDocente(dadosListarFuncionarioDocente);
   }

   @Override
   public void valida(ListarFuncionarioDocente solicitacao) throws NegocioException {
   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

}
