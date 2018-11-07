/*    */ package br.ufrn.ct.cronos.contadorsalaporpredio.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.Request;
/*    */ 
/*    */ public class ContadorSalaPorPredio extends Request
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long idPredio;
/*    */ 
/*    */   public Long getIdPredio()
/*    */   {
/* 17 */     return this.idPredio;
/*    */   }
/*    */ 
/*    */   public void setIdPredio(Long idPredio) {
/* 21 */     this.idPredio = idPredio;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.contadorsalaporpredio.vo.ContadorSalaPorPredio
 * JD-Core Version:    0.6.2
 */