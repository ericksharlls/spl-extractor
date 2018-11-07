package br.ufrn.ct.cronos.service.consultarusuario;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.consultarusuario.vo.ConsultarUsuario;
import br.ufrn.ct.cronos.consultarusuario.vo.DadosConsultarUsuario;
import br.ufrn.ct.cronos.consultarusuario.vo.RespostaConsultarUsuario;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.dao.PapelUsuarioDao;
import br.ufrn.ct.cronos.dao.UsuarioDao;
import br.ufrn.ct.cronos.entity.Funcionario;
import br.ufrn.ct.cronos.entity.PapelUsuario;
import br.ufrn.ct.cronos.entity.Usuario;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceConsultarUsuario extends AbstractService<ConsultarUsuario, RespostaConsultarUsuario> {

   private UsuarioDao usuarioDao;
   private FuncionarioDao funcionarioDao;
   private PapelUsuarioDao papelUsuarioDao;

   public RespostaConsultarUsuario processa(ConsultarUsuario solicitacao) throws NegocioException {
      List<Usuario> lista = this.usuarioDao.findAll(solicitacao.getStartPage().intValue(), solicitacao.getMaxPage().intValue());
      Integer totalNumeroLinhas = this.usuarioDao.count();
      List<DadosConsultarUsuario> dadosConsultarUsuario = new ArrayList<DadosConsultarUsuario>(lista.size());

      for (Usuario usuario : lista) {
         DadosConsultarUsuario dados = new DadosConsultarUsuario();
         dados.setId(usuario.getId());
         dados.setLogin(usuario.getLogin());
         dados.setAtivo(usuario.getAtivo());

         Funcionario funcionario = (Funcionario) this.funcionarioDao.get(usuario.getIdFuncionario());
         dados.setNomeFuncionario(funcionario.getNome());

         PapelUsuario perfil = this.papelUsuarioDao.getByUsuario(usuario.getId());
         dados.setPerfil(perfil.getNome());
         dadosConsultarUsuario.add(dados);
      }

      return new RespostaConsultarUsuario(dadosConsultarUsuario, totalNumeroLinhas);
   }

   public void valida(ConsultarUsuario solicitacao) throws NegocioException {
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
