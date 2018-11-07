
package br.ufrn.ct.cronos.service.listarfuncionario;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.entity.Funcionario;
import br.ufrn.ct.cronos.listarfuncionario.vo.DadosListarFuncionario;
import br.ufrn.ct.cronos.listarfuncionario.vo.ListarFuncionario;
import br.ufrn.ct.cronos.listarfuncionario.vo.RespostaListarFuncionario;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceListarFuncionario extends AbstractService<ListarFuncionario, RespostaListarFuncionario> {

   private FuncionarioDao funcionarioDao;

   public RespostaListarFuncionario processa(ListarFuncionario solicitacao) throws NegocioException {
      List<Funcionario> lista = this.funcionarioDao.getAllOrdemAlfabetica();
      List<DadosListarFuncionario> dadosListarFuncionario = new ArrayList<DadosListarFuncionario>(lista.size());
      for (Funcionario funcionario : lista) {
         DadosListarFuncionario dados = new DadosListarFuncionario();
         dados.setId(funcionario.getId());
         dados.setNome(funcionario.getNome());
         dadosListarFuncionario.add(dados);
      }
      return new RespostaListarFuncionario(dadosListarFuncionario);
   }

   public void valida(ListarFuncionario solicitacao) throws NegocioException {

   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

}
