/*    */ package br.ufrn.ct.cronos.web.removerusuario;
/*    */ 
/*    */ import br.ufrn.ct.cronos.obterusuario.vo.DadosObterUsuario;
/*    */ import br.ufrn.ct.cronos.obterusuario.vo.ObterUsuario;
/*    */ import br.ufrn.ct.cronos.obterusuario.vo.RespostaObterUsuario;
/*    */ import br.ufrn.ct.cronos.removerusuario.vo.RemoverUsuario;
/*    */ import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
/*    */ import dev.home.componente.service.Service;
/*    */ import dev.home.componente.service.excecao.NegocioException;
/*    */ import dev.home.componente.service.util.ServiceUtils;
/*    */ import dev.home.componente.web.infra.AbstractFormBean;
/*    */ 
/*    */ public class RemoverUsuarioFormBean extends AbstractFormBean
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long id;
/*    */   private String nomeFuncionario;
/*    */   private String login;
/*    */ 
/*    */   public String removerUsuario()
/*    */     throws NegocioException
/*    */   {
/* 26 */     RemoverUsuario solicitacao = new RemoverUsuario();
/* 27 */     popularVo(solicitacao);
/* 28 */     Service service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
/* 29 */     service.executa(solicitacao);
/* 30 */     return ControlNavigationFormBean.pageConsultarUsuario();
/*    */   }
/*    */ 
/*    */   public void obterUsuario() throws NegocioException {
/* 34 */     ObterUsuario solicitacao = new ObterUsuario();
/* 35 */     solicitacao.setId(this.id);
/* 36 */     Service service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
/* 37 */     RespostaObterUsuario resposta = (RespostaObterUsuario)service.executa(solicitacao);
/* 38 */     this.id = ((DadosObterUsuario)resposta.getObjeto()).getId();
/* 39 */     this.nomeFuncionario = ((DadosObterUsuario)resposta.getObjeto()).getNomeFuncionario();
/* 40 */     this.login = ((DadosObterUsuario)resposta.getObjeto()).getLogin();
/*    */   }
/*    */ 
/*    */   private void popularVo(RemoverUsuario solicitacao) {
/* 44 */     solicitacao.setId(this.id);
/*    */   }
/*    */ 
/*    */   public String cancelar() {
/* 48 */     return ControlNavigationFormBean.pageConsultarUsuario();
/*    */   }
/*    */ 
/*    */   public Long getId() {
/* 52 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id) {
/* 56 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getNomeFuncionario() {
/* 60 */     return this.nomeFuncionario;
/*    */   }
/*    */ 
/*    */   public void setNomeFuncionario(String nomeFuncionario) {
/* 64 */     this.nomeFuncionario = nomeFuncionario;
/*    */   }
/*    */ 
/*    */   public String getLogin() {
/* 68 */     return this.login;
/*    */   }
/*    */ 
/*    */   public void setLogin(String login) {
/* 72 */     this.login = login;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-client-web-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.web.removerusuario.RemoverUsuarioFormBean
 * JD-Core Version:    0.6.2
 */