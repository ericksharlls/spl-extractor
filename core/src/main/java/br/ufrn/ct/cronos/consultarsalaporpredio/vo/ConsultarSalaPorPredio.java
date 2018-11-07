/*    */ package br.ufrn.ct.cronos.consultarsalaporpredio.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.RequestPaged;
/*    */ 
/*    */ public class ConsultarSalaPorPredio extends RequestPaged
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long idPredio;
/*    */ 
/*    */   public ConsultarSalaPorPredio()
/*    */   {
/*    */   }
/*    */ 
/*    */   public ConsultarSalaPorPredio(Long idPredio)
/*    */   {
/* 17 */     this.idPredio = idPredio;
/*    */   }
/*    */ 
/*    */   public Long getIdPredio() {
/* 21 */     return this.idPredio;
/*    */   }
/*    */ 
/*    */   public void setIdPredio(Long idPredio) {
/* 25 */     this.idPredio = idPredio;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.consultarsalaporpredio.vo.ConsultarSalaPorPredio
 * JD-Core Version:    0.6.2
 */