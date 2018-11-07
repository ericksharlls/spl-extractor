
package br.ufrn.ct.cronos.service.removerfuncionario;

import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.dao.UsuarioDao;
import br.ufrn.ct.cronos.entity.Funcionario;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.removerfuncionario.vo.RemoverFuncionario;
import br.ufrn.ct.cronos.removerfuncionario.vo.RespostaRemoverFuncionario;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceRemoverFuncionario extends AbstractService<RemoverFuncionario, RespostaRemoverFuncionario> {

   private FuncionarioDao funcionarioDao;
   private UsuarioDao usuarioDao;

   public RespostaRemoverFuncionario processa(RemoverFuncionario solicitacao) throws NegocioException {
      Funcionario funcionario = new Funcionario();
      funcionario.setId(solicitacao.getId());

      this.funcionarioDao.delete(funcionario);

      return new RespostaRemoverFuncionario();
   }

   public void valida(RemoverFuncionario solicitacao) throws NegocioException {
      if (solicitacao.getId() == null) {
         throw new NegocioException(ErrorCode.ID_VAZIO);
      }
      if (this.usuarioDao.getByFuncionario(solicitacao.getId()) != null) {
         throw new NegocioException(ErrorCode.FUNCIONARIO_NAO_PODE_SER_EXCLUIDO);
      }
   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

   public void setUsuarioDao(UsuarioDao usuarioDao) {
      this.usuarioDao = usuarioDao;
   }

}
