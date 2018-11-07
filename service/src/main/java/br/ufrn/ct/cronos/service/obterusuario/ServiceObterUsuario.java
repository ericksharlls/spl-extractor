package br.ufrn.ct.cronos.service.obterusuario;

import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.dao.PapelUsuarioDao;
import br.ufrn.ct.cronos.dao.UsuarioDao;
import br.ufrn.ct.cronos.entity.Funcionario;
import br.ufrn.ct.cronos.entity.PapelUsuario;
import br.ufrn.ct.cronos.entity.Usuario;
import br.ufrn.ct.cronos.obterusuario.vo.DadosObterUsuario;
import br.ufrn.ct.cronos.obterusuario.vo.ObterUsuario;
import br.ufrn.ct.cronos.obterusuario.vo.RespostaObterUsuario;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceObterUsuario extends AbstractService<ObterUsuario, RespostaObterUsuario> {

   private UsuarioDao usuarioDao;
   private FuncionarioDao funcionarioDao;
   private PapelUsuarioDao papelUsuarioDao;

   public RespostaObterUsuario processa(ObterUsuario solicitacao) throws NegocioException {
      Usuario usuario = (Usuario) this.usuarioDao.get(solicitacao.getId());
      Funcionario funcionario = (Funcionario) this.funcionarioDao.get(usuario.getIdFuncionario());
      PapelUsuario perfil = this.papelUsuarioDao.getByUsuario(usuario.getId());

      DadosObterUsuario dados = new DadosObterUsuario();
      dados.setLogin(usuario.getLogin());
      dados.setNomeFuncionario(funcionario.getNome());
      dados.setId(usuario.getId());
      dados.setIdPerfil(perfil.getId());
      dados.setSenha(usuario.getSenha());
      dados.setSenhaConfirmacao(usuario.getSenha());

      return new RespostaObterUsuario(dados);
   }

   public void valida(ObterUsuario solicitacao) throws NegocioException {
   }

   public void setUsuarioDao(UsuarioDao usuarioDao) {
      this.usuarioDao = usuarioDao;
   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

   public void setPapelUsuarioDao(PapelUsuarioDao papelUsuarioDao) {
      this.papelUsuarioDao = papelUsuarioDao;
   }

}
