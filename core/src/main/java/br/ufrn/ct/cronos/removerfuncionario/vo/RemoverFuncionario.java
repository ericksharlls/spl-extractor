/*    */ package br.ufrn.ct.cronos.removerfuncionario.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.Request;
/*    */ 
/*    */ public class RemoverFuncionario extends Request
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long id;
/*    */ 
/*    */   public RemoverFuncionario()
/*    */   {
/*    */   }
/*    */ 
/*    */   public RemoverFuncionario(Long id)
/*    */   {
/* 17 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public Long getId() {
/* 21 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id) {
/* 25 */     this.id = id;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.removerfuncionario.vo.RemoverFuncionario
 * JD-Core Version:    0.6.2
 */