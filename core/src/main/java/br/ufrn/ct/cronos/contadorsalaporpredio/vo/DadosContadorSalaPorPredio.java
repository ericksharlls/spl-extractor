/*    */ package br.ufrn.ct.cronos.contadorsalaporpredio.vo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DadosContadorSalaPorPredio
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer total;
/*    */ 
/*    */   public DadosContadorSalaPorPredio()
/*    */   {
/*    */   }
/*    */ 
/*    */   public DadosContadorSalaPorPredio(Integer total)
/*    */   {
/* 17 */     this.total = total;
/*    */   }
/*    */ 
/*    */   public Integer getTotal() {
/* 21 */     return this.total;
/*    */   }
/*    */ 
/*    */   public void setTotal(Integer total) {
/* 25 */     this.total = total;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.contadorsalaporpredio.vo.DadosContadorSalaPorPredio
 * JD-Core Version:    0.6.2
 */