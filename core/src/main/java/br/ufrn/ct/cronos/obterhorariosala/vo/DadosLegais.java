/*    */ package br.ufrn.ct.cronos.obterhorariosala.vo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DadosLegais
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long id;
/* 10 */   private String horario = null;
/*    */ 
/*    */   public DadosLegais()
/*    */   {
/*    */   }
/*    */ 
/*    */   public DadosLegais(Long id, String horario)
/*    */   {
/* 18 */     this.id = id;
/* 19 */     setHorario(horario);
/*    */   }
/*    */ 
/*    */   public String getHorario()
/*    */   {
/* 24 */     return this.horario;
/*    */   }
/*    */ 
/*    */   public void setHorario(String horario) {
/* 28 */     this.horario = horario;
/*    */   }
/*    */ 
/*    */   public void setId(Long id) {
/* 32 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public Long getId() {
/* 36 */     return this.id;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.obterhorariosala.vo.DadosLegais
 * JD-Core Version:    0.6.2
 */