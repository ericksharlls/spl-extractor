/*    */ package br.ufrn.ct.cronos.desalocarturma.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.Request;
/*    */ 
/*    */ public class DesalocarTurma extends Request
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long idTurma;
/*    */ 
/*    */   public DesalocarTurma()
/*    */   {
/*    */   }
/*    */ 
/*    */   public DesalocarTurma(Long idTurma)
/*    */   {
/* 17 */     this.idTurma = idTurma;
/*    */   }
/*    */ 
/*    */   public Long getIdTurma() {
/* 21 */     return this.idTurma;
/*    */   }
/*    */ 
/*    */   public void setIdTurma(Long idTurma) {
/* 25 */     this.idTurma = idTurma;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.desalocarturma.vo.DesalocarTurma
 * JD-Core Version:    0.6.2
 */