/*     */ package br.ufrn.ct.cronos.relatoriohorariosalas.vo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class DadosHorario
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*   9 */   private String sala = null;
/*     */ 
/*  11 */   private Integer primeiroHorarioManha = Integer.valueOf(0);
/*  12 */   private Integer segundoHorarioManha = Integer.valueOf(0);
/*  13 */   private Integer terceiroHorarioManha = Integer.valueOf(0);
/*  14 */   private Integer quartoHorarioManha = Integer.valueOf(0);
/*  15 */   private Integer quintoHorarioManha = Integer.valueOf(0);
/*  16 */   private Integer sextoHorarioManha = Integer.valueOf(0);
/*     */ 
/*  18 */   private Integer primeiroHorarioTarde = Integer.valueOf(0);
/*  19 */   private Integer segundoHorarioTarde = Integer.valueOf(0);
/*  20 */   private Integer terceiroHorarioTarde = Integer.valueOf(0);
/*  21 */   private Integer quartoHorarioTarde = Integer.valueOf(0);
/*  22 */   private Integer quintoHorarioTarde = Integer.valueOf(0);
/*  23 */   private Integer sextoHorarioTarde = Integer.valueOf(0);
/*     */ 
/*  25 */   private Integer primeiroHorarioNoite = Integer.valueOf(0);
/*  26 */   private Integer segundoHorarioNoite = Integer.valueOf(0);
/*  27 */   private Integer terceiroHorarioNoite = Integer.valueOf(0);
/*  28 */   private Integer quartoHorarioNoite = Integer.valueOf(0);
/*     */ 
/*     */   public String getSala()
/*     */   {
/*  35 */     return this.sala;
/*     */   }
/*     */ 
/*     */   public void setSala(String sala) {
/*  39 */     this.sala = sala;
/*     */   }
/*     */ 
/*     */   public Integer getPrimeiroHorarioManha() {
/*  43 */     return this.primeiroHorarioManha;
/*     */   }
/*     */ 
/*     */   public void setPrimeiroHorarioManha(Integer primeiroHorarioManha) {
/*  47 */     this.primeiroHorarioManha = primeiroHorarioManha;
/*     */   }
/*     */ 
/*     */   public Integer getSegundoHorarioManha() {
/*  51 */     return this.segundoHorarioManha;
/*     */   }
/*     */ 
/*     */   public void setSegundoHorarioManha(Integer segundoHorarioManha) {
/*  55 */     this.segundoHorarioManha = segundoHorarioManha;
/*     */   }
/*     */ 
/*     */   public Integer getTerceiroHorarioManha() {
/*  59 */     return this.terceiroHorarioManha;
/*     */   }
/*     */ 
/*     */   public void setTerceiroHorarioManha(Integer terceiroHorarioManha) {
/*  63 */     this.terceiroHorarioManha = terceiroHorarioManha;
/*     */   }
/*     */ 
/*     */   public Integer getQuartoHorarioManha() {
/*  67 */     return this.quartoHorarioManha;
/*     */   }
/*     */ 
/*     */   public void setQuartoHorarioManha(Integer quartoHorarioManha) {
/*  71 */     this.quartoHorarioManha = quartoHorarioManha;
/*     */   }
/*     */ 
/*     */   public Integer getQuintoHorarioManha() {
/*  75 */     return this.quintoHorarioManha;
/*     */   }
/*     */ 
/*     */   public void setQuintoHorarioManha(Integer quintoHorarioManha) {
/*  79 */     this.quintoHorarioManha = quintoHorarioManha;
/*     */   }
/*     */ 
/*     */   public Integer getSextoHorarioManha() {
/*  83 */     return this.sextoHorarioManha;
/*     */   }
/*     */ 
/*     */   public void setSextoHorarioManha(Integer sextoHorarioManha) {
/*  87 */     this.sextoHorarioManha = sextoHorarioManha;
/*     */   }
/*     */ 
/*     */   public Integer getPrimeiroHorarioTarde() {
/*  91 */     return this.primeiroHorarioTarde;
/*     */   }
/*     */ 
/*     */   public void setPrimeiroHorarioTarde(Integer primeiroHorarioTarde) {
/*  95 */     this.primeiroHorarioTarde = primeiroHorarioTarde;
/*     */   }
/*     */ 
/*     */   public Integer getSegundoHorarioTarde() {
/*  99 */     return this.segundoHorarioTarde;
/*     */   }
/*     */ 
/*     */   public void setSegundoHorarioTarde(Integer segundoHorarioTarde) {
/* 103 */     this.segundoHorarioTarde = segundoHorarioTarde;
/*     */   }
/*     */ 
/*     */   public Integer getTerceiroHorarioTarde() {
/* 107 */     return this.terceiroHorarioTarde;
/*     */   }
/*     */ 
/*     */   public void setTerceiroHorarioTarde(Integer terceiroHorarioTarde) {
/* 111 */     this.terceiroHorarioTarde = terceiroHorarioTarde;
/*     */   }
/*     */ 
/*     */   public Integer getQuartoHorarioTarde() {
/* 115 */     return this.quartoHorarioTarde;
/*     */   }
/*     */ 
/*     */   public void setQuartoHorarioTarde(Integer quartoHorarioTarde) {
/* 119 */     this.quartoHorarioTarde = quartoHorarioTarde;
/*     */   }
/*     */ 
/*     */   public Integer getQuintoHorarioTarde() {
/* 123 */     return this.quintoHorarioTarde;
/*     */   }
/*     */ 
/*     */   public void setQuintoHorarioTarde(Integer quintoHorarioTarde) {
/* 127 */     this.quintoHorarioTarde = quintoHorarioTarde;
/*     */   }
/*     */ 
/*     */   public Integer getSextoHorarioTarde() {
/* 131 */     return this.sextoHorarioTarde;
/*     */   }
/*     */ 
/*     */   public void setSextoHorarioTarde(Integer sextoHorarioTarde) {
/* 135 */     this.sextoHorarioTarde = sextoHorarioTarde;
/*     */   }
/*     */ 
/*     */   public Integer getPrimeiroHorarioNoite() {
/* 139 */     return this.primeiroHorarioNoite;
/*     */   }
/*     */ 
/*     */   public void setPrimeiroHorarioNoite(Integer primeiroHorarioNoite) {
/* 143 */     this.primeiroHorarioNoite = primeiroHorarioNoite;
/*     */   }
/*     */ 
/*     */   public Integer getSegundoHorarioNoite() {
/* 147 */     return this.segundoHorarioNoite;
/*     */   }
/*     */ 
/*     */   public void setSegundoHorarioNoite(Integer segundoHorarioNoite) {
/* 151 */     this.segundoHorarioNoite = segundoHorarioNoite;
/*     */   }
/*     */ 
/*     */   public Integer getTerceiroHorarioNoite() {
/* 155 */     return this.terceiroHorarioNoite;
/*     */   }
/*     */ 
/*     */   public void setTerceiroHorarioNoite(Integer terceiroHorarioNoite) {
/* 159 */     this.terceiroHorarioNoite = terceiroHorarioNoite;
/*     */   }
/*     */ 
/*     */   public Integer getQuartoHorarioNoite() {
/* 163 */     return this.quartoHorarioNoite;
/*     */   }
/*     */ 
/*     */   public void setQuartoHorarioNoite(Integer quartoHorarioNoite) {
/* 167 */     this.quartoHorarioNoite = quartoHorarioNoite;
/*     */   }
/*     */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.relatoriohorariosalas.vo.DadosHorario
 * JD-Core Version:    0.6.2
 */