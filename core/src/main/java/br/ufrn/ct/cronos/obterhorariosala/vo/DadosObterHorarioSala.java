/*    */ package br.ufrn.ct.cronos.obterhorariosala.vo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DadosObterHorarioSala
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*  9 */   private String semestre = null;
/* 10 */   private String sala = null;
/*    */ 
/* 12 */   private List<DadosTurma> distribuicoes = null;
/*    */ 
/*    */   public String getSemestre()
/*    */   {
/* 19 */     return this.semestre;
/*    */   }
/*    */ 
/*    */   public void setSemestre(String semestre) {
/* 23 */     this.semestre = semestre;
/*    */   }
/*    */ 
/*    */   public String getSala() {
/* 27 */     return this.sala;
/*    */   }
/*    */ 
/*    */   public void setSala(String sala) {
/* 31 */     this.sala = sala;
/*    */   }
/*    */ 
/*    */   public List<DadosTurma> getDistribuicoes() {
/* 35 */     return this.distribuicoes;
/*    */   }
/*    */ 
/*    */   public void setDistribuicoes(List<DadosTurma> distribuicoes) {
/* 39 */     this.distribuicoes = distribuicoes;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.obterhorariosala.vo.DadosObterHorarioSala
 * JD-Core Version:    0.6.2
 */