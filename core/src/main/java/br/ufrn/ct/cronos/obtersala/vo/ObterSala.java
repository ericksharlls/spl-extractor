/*    */ package br.ufrn.ct.cronos.obtersala.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.Request;
/*    */ 
/*    */ public class ObterSala extends Request
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long id;
/*    */ 
/*    */   public ObterSala()
/*    */   {
/*    */   }
/*    */ 
/*    */   public ObterSala(Long id)
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
 * Qualified Name:     br.ufrn.ct.cronos.obtersala.vo.ObterSala
 * JD-Core Version:    0.6.2
 */