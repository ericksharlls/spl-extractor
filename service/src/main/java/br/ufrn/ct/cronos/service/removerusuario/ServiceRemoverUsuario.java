package br.ufrn.ct.cronos.service.removerusuario;

import br.ufrn.ct.cronos.dao.PermissaoUsuarioDao;
import br.ufrn.ct.cronos.dao.UsuarioDao;
import br.ufrn.ct.cronos.entity.PermissaoUsuario;
import br.ufrn.ct.cronos.entity.Usuario;
import br.ufrn.ct.cronos.removerusuario.vo.RemoverUsuario;
import br.ufrn.ct.cronos.removerusuario.vo.RespostaRemoverUsuario;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceRemoverUsuario extends AbstractService<RemoverUsuario, RespostaRemoverUsuario> {

   private UsuarioDao usuarioDao;
   private PermissaoUsuarioDao permissaoUsuarioDao;

   public RespostaRemoverUsuario processa(RemoverUsuario solicitacao) throws NegocioException {
      for (PermissaoUsuario up : this.permissaoUsuarioDao.getPorUsuario(solicitacao.getId())) {
         this.permissaoUsuarioDao.delete(up);
      }
      Usuario usuario = new Usuario();

      usuario.setId(solicitacao.getId());
      this.usuarioDao.delete(usuario);
      return new RespostaRemoverUsuario();
   }

   public void valida(RemoverUsuario solicitacao) throws NegocioException {
      if (solicitacao.getId() == null)
         throw new NegocioException(2);
   }

   public void setUsuarioDao(UsuarioDao usuarioDao) {
      this.usuarioDao = usuarioDao;
   }

   public void setPermissaoUsuarioDao(PermissaoUsuarioDao permissaoUsuarioDao) {
      this.permissaoUsuarioDao = permissaoUsuarioDao;
   }

}
