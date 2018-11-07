/*     */ package br.ufrn.ct.cronos.obterhorariosala.vo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DadosTurma
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  11 */   private String horario = null;
/*     */ 
/*  13 */   private String horarioSegunda = null;
/*  14 */   private String horarioTerca = null;
/*  15 */   private String horarioQuarta = null;
/*  16 */   private String horarioQuinta = null;
/*  17 */   private String horarioSexta = null;
/*  18 */   private String horarioSabado = null;
/*     */ 
/*  20 */   private List<DadosLegais> horariosSegunda = new ArrayList();
/*  21 */   private List<DadosLegais> horariosTerca = new ArrayList();
/*  22 */   private List<DadosLegais> horariosQuarta = new ArrayList();
/*  23 */   private List<DadosLegais> horariosQuinta = new ArrayList();
/*  24 */   private List<DadosLegais> horariosSexta = new ArrayList();
/*  25 */   private List<DadosLegais> horariosSabado = new ArrayList();
/*     */ 
/*  27 */   private List<DadosLegais> dadoslegais = new ArrayList();
/*     */ 
/*     */   public String getHorario()
/*     */   {
/*  34 */     return this.horario;
/*     */   }
/*     */ 
/*     */   public void setHorario(String horario) {
/*  38 */     this.horario = horario;
/*     */   }
/*     */ 
/*     */   public List<DadosLegais> getHorariosSegunda() {
/*  42 */     return this.horariosSegunda;
/*     */   }
/*     */ 
/*     */   public void setHorariosSegunda(List<DadosLegais> horariosSegunda) {
/*  46 */     this.horariosSegunda = horariosSegunda;
/*     */   }
/*     */ 
/*     */   public List<DadosLegais> getHorariosTerca() {
/*  50 */     return this.horariosTerca;
/*     */   }
/*     */ 
/*     */   public void setHorariosTerca(List<DadosLegais> horariosTerca) {
/*  54 */     this.horariosTerca = horariosTerca;
/*     */   }
/*     */ 
/*     */   public List<DadosLegais> getHorariosQuarta() {
/*  58 */     return this.horariosQuarta;
/*     */   }
/*     */ 
/*     */   public void setHorariosQuarta(List<DadosLegais> horariosQuarta) {
/*  62 */     this.horariosQuarta = horariosQuarta;
/*     */   }
/*     */ 
/*     */   public List<DadosLegais> getHorariosQuinta() {
/*  66 */     return this.horariosQuinta;
/*     */   }
/*     */ 
/*     */   public void setHorariosQuinta(List<DadosLegais> horariosQuinta) {
/*  70 */     this.horariosQuinta = horariosQuinta;
/*     */   }
/*     */ 
/*     */   public List<DadosLegais> getHorariosSexta() {
/*  74 */     return this.horariosSexta;
/*     */   }
/*     */ 
/*     */   public void setHorariosSexta(List<DadosLegais> horariosSexta) {
/*  78 */     this.horariosSexta = horariosSexta;
/*     */   }
/*     */ 
/*     */   public List<DadosLegais> getDadoslegais() {
/*  82 */     return this.dadoslegais;
/*     */   }
/*     */ 
/*     */   public void setDadoslegais(List<DadosLegais> dadoslegais) {
/*  86 */     this.dadoslegais = dadoslegais;
/*     */   }
/*     */ 
/*     */   public String getHorarioSegunda() {
/*  90 */     return this.horarioSegunda;
/*     */   }
/*     */ 
/*     */   public void setHorarioSegunda(String horarioSegunda) {
/*  94 */     this.horarioSegunda = horarioSegunda;
/*     */   }
/*     */ 
/*     */   public String getHorarioTerca() {
/*  98 */     return this.horarioTerca;
/*     */   }
/*     */ 
/*     */   public void setHorarioTerca(String horarioTerca) {
/* 102 */     this.horarioTerca = horarioTerca;
/*     */   }
/*     */ 
/*     */   public String getHorarioQuarta() {
/* 106 */     return this.horarioQuarta;
/*     */   }
/*     */ 
/*     */   public void setHorarioQuarta(String horarioQuarta) {
/* 110 */     this.horarioQuarta = horarioQuarta;
/*     */   }
/*     */ 
/*     */   public String getHorarioQuinta() {
/* 114 */     return this.horarioQuinta;
/*     */   }
/*     */ 
/*     */   public void setHorarioQuinta(String horarioQuinta) {
/* 118 */     this.horarioQuinta = horarioQuinta;
/*     */   }
/*     */ 
/*     */   public String getHorarioSexta() {
/* 122 */     return this.horarioSexta;
/*     */   }
/*     */ 
/*     */   public void setHorarioSexta(String horarioSexta) {
/* 126 */     this.horarioSexta = horarioSexta;
/*     */   }
/*     */ 
/*     */   public String getHorarioSabado() {
/* 130 */     return this.horarioSabado;
/*     */   }
/*     */ 
/*     */   public void setHorarioSabado(String horarioSabado) {
/* 134 */     this.horarioSabado = horarioSabado;
/*     */   }
/*     */ 
/*     */   public void setHorariosSabado(List<DadosLegais> horariosSabado) {
/* 138 */     this.horariosSabado = horariosSabado;
/*     */   }
/*     */ 
/*     */   public List<DadosLegais> getHorariosSabado() {
/* 142 */     return this.horariosSabado;
/*     */   }
/*     */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.obterhorariosala.vo.DadosTurma
 * JD-Core Version:    0.6.2
 */