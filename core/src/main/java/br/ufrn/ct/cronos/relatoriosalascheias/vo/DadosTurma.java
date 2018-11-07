/*    */ package br.ufrn.ct.cronos.relatoriosalascheias.vo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DadosTurma
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*  9 */   private String disciplina = null;
/* 10 */   private String horario = null;
/* 11 */   private String docente = null;
/* 12 */   private String sala = null;
/* 13 */   private Integer capacidadeSala = null;
/* 14 */   private Integer matriculados = null;
/*    */ 
/*    */   public String getDisciplina()
/*    */   {
/* 22 */     return this.disciplina;
/*    */   }
/*    */ 
/*    */   public void setDisciplina(String disciplina) {
/* 26 */     this.disciplina = disciplina;
/*    */   }
/*    */ 
/*    */   public String getHorario() {
/* 30 */     return this.horario;
/*    */   }
/*    */ 
/*    */   public void setHorario(String horario) {
/* 34 */     this.horario = horario;
/*    */   }
/*    */ 
/*    */   public String getDocente() {
/* 38 */     return this.docente;
/*    */   }
/*    */ 
/*    */   public void setDocente(String docente) {
/* 42 */     this.docente = docente;
/*    */   }
/*    */ 
/*    */   public String getSala() {
/* 46 */     return this.sala;
/*    */   }
/*    */ 
/*    */   public void setSala(String sala) {
/* 50 */     this.sala = sala;
/*    */   }
/*    */ 
/*    */   public Integer getMatriculados() {
/* 54 */     return this.matriculados;
/*    */   }
/*    */ 
/*    */   public void setMatriculados(Integer matriculados) {
/* 58 */     this.matriculados = matriculados;
/*    */   }
/*    */ 
/*    */   public Integer getCapacidadeSala() {
/* 62 */     return this.capacidadeSala;
/*    */   }
/*    */ 
/*    */   public void setCapacidadeSala(Integer capacidadeSala)
/*    */   {
/* 67 */     this.capacidadeSala = capacidadeSala;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.relatoriosalascheias.vo.DadosTurma
 * JD-Core Version:    0.6.2
 */