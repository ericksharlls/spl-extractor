package br.ufrn.ct.cronos.service.cadastrarusuario;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.lang.StringUtils;
import br.ufrn.ct.cronos.cadastrarusuario.vo.CadastrarUsuario;
import br.ufrn.ct.cronos.cadastrarusuario.vo.RespostaCadastrarUsuario;
import br.ufrn.ct.cronos.dao.PermissaoUsuarioDao;
import br.ufrn.ct.cronos.dao.UsuarioDao;
import br.ufrn.ct.cronos.entity.PermissaoUsuario;
import br.ufrn.ct.cronos.entity.Usuario;
import br.ufrn.ct.cronos.error.ErrorCode;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceCadastrarUsuario extends AbstractService<CadastrarUsuario, RespostaCadastrarUsuario> {

   private UsuarioDao usuarioDao;
   private PermissaoUsuarioDao permissaoUsuarioDao;

   public RespostaCadastrarUsuario processa(CadastrarUsuario solicitacao) throws NegocioException {
      Usuario usuario = new Usuario();
      usuario.setIdFuncionario(solicitacao.getIdFuncionario());
      usuario.setLogin(solicitacao.getLogin());
      usuario.setAtivo(true);
      try {
         MessageDigest algoritmo = MessageDigest.getInstance("MD5");
         byte[] messageDigest = algoritmo.digest(solicitacao.getSenha().getBytes("UTF-8"));
         StringBuilder hexString = new StringBuilder();
         for (byte b : messageDigest) {
            hexString.append(String.format("%02x", new Object[]{Integer.valueOf(0xFF & b)}));
         }
         usuario.setSenha(hexString.toString());
      } catch (NoSuchAlgorithmException e) {
         e.printStackTrace();
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      }

      this.usuarioDao.save(usuario);

      PermissaoUsuario usuarioPerfil = new PermissaoUsuario();
      usuarioPerfil.setIdPapel(solicitacao.getIdPapel());
      usuarioPerfil.setIdUsuario(usuario.getId());

      this.permissaoUsuarioDao.save(usuarioPerfil);

      return new RespostaCadastrarUsuario();
   }

   public void valida(CadastrarUsuario solicitacao) throws NegocioException {
      if ((solicitacao.getIdFuncionario() == 0) || (solicitacao.getIdFuncionario() == null)) {
         throw new NegocioException(ErrorCode.FUNCIONARIO_VAZIO);
      }
      if (StringUtils.isBlank(solicitacao.getLogin())) {
         throw new NegocioException(ErrorCode.LOGIN_VAZIO);
      }
      if (StringUtils.isBlank(solicitacao.getSenha())) {
         throw new NegocioException(ErrorCode.SENHA_VAZIO);
      }
      if ((solicitacao.getIdPapel() == 0) || (solicitacao.getIdPapel() == null)) {
         throw new NegocioException(ErrorCode.PERFIL_VAZIO);
      }
      if (!solicitacao.getSenha().equals(solicitacao.getSenhaConfirmacao())) {
         throw new NegocioException(ErrorCode.SENHAS_NAO_COINCIDEM);
      }
      if (this.usuarioDao.getByFuncionario(solicitacao.getIdFuncionario()) != null) {
         throw new NegocioException(ErrorCode.FUNCIONARIO_JA_TEM_USUARIO);
      }
      if (this.usuarioDao.getByLogin(solicitacao.getLogin()) != null) {
         throw new NegocioException(ErrorCode.LOGIN_JA_EXISTE);
      }
   }

   public void setUsuarioDao(UsuarioDao usuarioDao) {
      this.usuarioDao = usuarioDao;
   }

   public void setPermissaoUsuarioDao(PermissaoUsuarioDao permissaoUsuarioDao) {
      this.permissaoUsuarioDao = permissaoUsuarioDao;
   }

}
