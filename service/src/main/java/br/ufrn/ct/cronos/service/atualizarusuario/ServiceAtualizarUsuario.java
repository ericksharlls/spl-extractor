package br.ufrn.ct.cronos.service.atualizarusuario;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.lang.StringUtils;
import br.ufrn.ct.cronos.atualizarusuario.vo.AtualizarUsuario;
import br.ufrn.ct.cronos.atualizarusuario.vo.RespostaAtualizarUsuario;
import br.ufrn.ct.cronos.dao.PermissaoUsuarioDao;
import br.ufrn.ct.cronos.dao.UsuarioDao;
import br.ufrn.ct.cronos.entity.PermissaoUsuario;
import br.ufrn.ct.cronos.entity.Usuario;
import br.ufrn.ct.cronos.error.ErrorCode;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceAtualizarUsuario extends
		AbstractService<AtualizarUsuario, RespostaAtualizarUsuario> {

	private UsuarioDao usuarioDao;
	private PermissaoUsuarioDao permissaoUsuarioDao;

	public RespostaAtualizarUsuario processa(AtualizarUsuario solicitacao) throws NegocioException {
		Usuario usuario = (Usuario) this.usuarioDao.get(solicitacao.getId());
		try {
			MessageDigest algoritmo = MessageDigest.getInstance("MD5");
			byte[] messageDigest = algoritmo.digest(solicitacao.getSenha()
					.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02x", new Object[] { Integer.valueOf(0xFF & b) }));
			}
			usuario.setSenha(hexString.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		usuario.setAtivo(!solicitacao.getDesabilitarUsuario());
		this.usuarioDao.merge(usuario);

		for (PermissaoUsuario up : this.permissaoUsuarioDao.getPorUsuario(usuario.getId())) {
			this.permissaoUsuarioDao.delete(up);
		}

		PermissaoUsuario usuarioPerfil = new PermissaoUsuario();
		usuarioPerfil.setIdPapel(solicitacao.getIdPapel());
		usuarioPerfil.setIdUsuario(usuario.getId());

		this.permissaoUsuarioDao.save(usuarioPerfil);

		return new RespostaAtualizarUsuario();
	}

	public void valida(AtualizarUsuario solicitacao) throws NegocioException {
		/*
		if (StringUtils.isBlank(solicitacao.getSenha())) {
			throw new NegocioException(ErrorCode.SENHA_VAZIO);
		}
		*/
		if (!solicitacao.getSenha().equals(solicitacao.getSenhaConfirmacao())) {
			throw new NegocioException(ErrorCode.SENHAS_NAO_COINCIDEM);
		}
		if ((solicitacao.getIdPapel() == 0) || (solicitacao.getIdPapel() == null)) {
			throw new NegocioException(ErrorCode.PERFIL_VAZIO);
		}
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public void setPermissaoUsuarioDao(PermissaoUsuarioDao permissaoUsuarioDao) {
		this.permissaoUsuarioDao = permissaoUsuarioDao;
	}

}
