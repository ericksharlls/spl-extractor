/*    */ package br.ufrn.ct.cronos.obterusuario.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.ResponseUnique;
/*    */ 
/*    */ public class RespostaObterUsuario extends ResponseUnique<DadosObterUsuario>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public RespostaObterUsuario()
/*    */   {
/*    */   }
/*    */ 
/*    */   public RespostaObterUsuario(DadosObterUsuario objeto)
/*    */   {
/* 15 */     setObjeto(objeto);
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.obterusuario.vo.RespostaObterUsuario
 * JD-Core Version:    0.6.2
 */