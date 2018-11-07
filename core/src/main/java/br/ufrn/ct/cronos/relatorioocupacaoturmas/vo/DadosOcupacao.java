/*    */ package br.ufrn.ct.cronos.relatorioocupacaoturmas.vo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DadosOcupacao
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*  9 */   private String chave = null;
/* 10 */   private String data = null;
/*    */ 
/* 12 */   private String primeiroHorario = "X";
/* 13 */   private String segundoHorario = "X";
/* 14 */   private String terceiroHorario = "X";
/* 15 */   private String quartoHorario = "X";
/* 16 */   private String quintoHorario = "X";
/* 17 */   private String sextoHorario = "X";
/*    */ 
/*    */   public String getChave()
/*    */   {
/* 24 */     return this.chave;
/*    */   }
/*    */ 
/*    */   public void setChave(String chave) {
/* 28 */     this.chave = chave;
/*    */   }
/*    */ 
/*    */   public String getData() {
/* 32 */     return this.data;
/*    */   }
/*    */ 
/*    */   public void setData(String data) {
/* 36 */     this.data = data;
/*    */   }
/*    */ 
/*    */   public String getPrimeiroHorario() {
/* 40 */     return this.primeiroHorario;
/*    */   }
/*    */ 
/*    */   public void setPrimeiroHorario(String primeiroHorario) {
/* 44 */     this.primeiroHorario = primeiroHorario;
/*    */   }
/*    */ 
/*    */   public String getSegundoHorario() {
/* 48 */     return this.segundoHorario;
/*    */   }
/*    */ 
/*    */   public void setSegundoHorario(String segundoHorario) {
/* 52 */     this.segundoHorario = segundoHorario;
/*    */   }
/*    */ 
/*    */   public String getTerceiroHorario() {
/* 56 */     return this.terceiroHorario;
/*    */   }
/*    */ 
/*    */   public void setTerceiroHorario(String terceiroHorario) {
/* 60 */     this.terceiroHorario = terceiroHorario;
/*    */   }
/*    */ 
/*    */   public String getQuartoHorario() {
/* 64 */     return this.quartoHorario;
/*    */   }
/*    */ 
/*    */   public void setQuartoHorario(String quartoHorario) {
/* 68 */     this.quartoHorario = quartoHorario;
/*    */   }
/*    */ 
/*    */   public String getQuintoHorario() {
/* 72 */     return this.quintoHorario;
/*    */   }
/*    */ 
/*    */   public void setQuintoHorario(String quintoHorario) {
/* 76 */     this.quintoHorario = quintoHorario;
/*    */   }
/*    */ 
/*    */   public String getSextoHorario() {
/* 80 */     return this.sextoHorario;
/*    */   }
/*    */ 
/*    */   public void setSextoHorario(String sextoHorario) {
/* 84 */     this.sextoHorario = sextoHorario;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.relatorioocupacaoturmas.vo.DadosOcupacao
 * JD-Core Version:    0.6.2
 */