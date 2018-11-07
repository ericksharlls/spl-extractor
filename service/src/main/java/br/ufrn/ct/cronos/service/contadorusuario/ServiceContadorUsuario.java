/*    */ package br.ufrn.ct.cronos.service.contadorusuario;
/*    */ 
/*    */ import br.ufrn.ct.cronos.contadorusuario.vo.ContadorUsuario;
/*    */ import br.ufrn.ct.cronos.contadorusuario.vo.DadosContadorUsuario;
/*    */ import br.ufrn.ct.cronos.contadorusuario.vo.RespostaContadorUsuario;
/*    */ import br.ufrn.ct.cronos.dao.UsuarioDao;
/*    */ import dev.home.componente.service.AbstractService;
/*    */ import dev.home.componente.service.excecao.NegocioException;
/*    */ 
/*    */ public class ServiceContadorUsuario extends AbstractService<ContadorUsuario, RespostaContadorUsuario>
/*    */ {
/*    */   private UsuarioDao usuarioDao;
/*    */ 
/*    */   public RespostaContadorUsuario processa(ContadorUsuario solicitacao)
/*    */     throws NegocioException
/*    */   {
/* 16 */     Integer total = this.usuarioDao.count();
/* 17 */     DadosContadorUsuario dadosContadorUsuario = new DadosContadorUsuario(total);
/* 18 */     return new RespostaContadorUsuario(dadosContadorUsuario);
/*    */   }
/*    */ 
/*    */   public void valida(ContadorUsuario solicitacao) throws NegocioException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void setUsuarioDao(UsuarioDao usuarioDao)
/*    */   {
/* 27 */     this.usuarioDao = usuarioDao;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-service-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.service.contadorusuario.ServiceContadorUsuario
 * JD-Core Version:    0.6.2
 */