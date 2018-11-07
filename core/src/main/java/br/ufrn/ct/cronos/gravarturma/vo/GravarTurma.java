/*    */ package br.ufrn.ct.cronos.gravarturma.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.Request;
/*    */ 
/*    */ public class GravarTurma extends Request
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long id;
/*    */   private Long idTipo;
/*    */   private Long idPredio;
/*    */   private Boolean distribuir;
/*    */ 
/*    */   public GravarTurma()
/*    */   {
/*    */   }
/*    */ 
/*    */   public GravarTurma(Long id, Long idTipo, Long idPredio, Boolean distribuir)
/*    */   {
/* 20 */     this.id = id;
/* 21 */     this.idTipo = idTipo;
/* 22 */     this.idPredio = idPredio;
/* 23 */     this.distribuir = distribuir;
/*    */   }
/*    */ 
/*    */   public Long getId() {
/* 27 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id) {
/* 31 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public Long getIdTipo() {
/* 35 */     return this.idTipo;
/*    */   }
/*    */ 
/*    */   public void setIdTipo(Long idTipo) {
/* 39 */     this.idTipo = idTipo;
/*    */   }
/*    */ 
/*    */   public Long getIdPredio() {
/* 43 */     return this.idPredio;
/*    */   }
/*    */ 
/*    */   public void setIdPredio(Long idPredio) {
/* 47 */     this.idPredio = idPredio;
/*    */   }
/*    */ 
/*    */   public Boolean getDistribuir() {
/* 51 */     return this.distribuir;
/*    */   }
/*    */ 
/*    */   public void setDistribuir(Boolean distribuir) {
/* 55 */     this.distribuir = distribuir;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.gravarturma.vo.GravarTurma
 * JD-Core Version:    0.6.2
 */