
package br.ufrn.ct.cronos.service.obterfuncionario;

import java.text.ParseException;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.entity.Funcionario;
import br.ufrn.ct.cronos.obterfuncionario.vo.DadosObterFuncionario;
import br.ufrn.ct.cronos.obterfuncionario.vo.ObterFuncionario;
import br.ufrn.ct.cronos.obterfuncionario.vo.RespostaObterFuncionario;
import br.ufrn.ct.cronos.service.FormatadorMascaras;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceObterFuncionario extends AbstractService<ObterFuncionario, RespostaObterFuncionario> {

   private FuncionarioDao funcionarioDao;

   public RespostaObterFuncionario processa(ObterFuncionario solicitacao) throws NegocioException {
      Funcionario funcionario = (Funcionario) this.funcionarioDao.get(solicitacao.getId());
      DadosObterFuncionario dados = new DadosObterFuncionario();

      dados.setEmail(funcionario.getEmail());
      dados.setId(funcionario.getId());
      dados.setMatricula(funcionario.getMatricula());
      dados.setNome(funcionario.getNome());
      dados.setIdTipoFuncionario(funcionario.getIdTipoFuncionario());
      dados.setDddTelefone(funcionario.getTelefone().substring(0, 2));

      try {
         dados.setCpf(FormatadorMascaras.aplicarMascaraCPF(funcionario.getCpf()));
         dados.setTelefone(FormatadorMascaras.aplicarMascaraTelefoneSemDDD(funcionario.getTelefone().substring(2,
            funcionario.getTelefone().length())));
      } catch (ParseException e) {
         e.printStackTrace();
      }

      return new RespostaObterFuncionario(dados);
   }

   public void valida(ObterFuncionario solicitacao) throws NegocioException {

   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

}
