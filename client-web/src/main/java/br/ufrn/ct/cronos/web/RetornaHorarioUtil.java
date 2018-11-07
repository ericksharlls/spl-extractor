/*    */ package br.ufrn.ct.cronos.web;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class RetornaHorarioUtil
/*    */ {
/*  8 */   private Map<String, Integer> horarios = new HashMap();
/*    */ 
/*    */   public RetornaHorarioUtil() {
/* 11 */     this.horarios.put("7:00 às 7:50", Integer.valueOf(1));
/* 12 */     this.horarios.put("7:50 às 8:40", Integer.valueOf(2));
/* 13 */     this.horarios.put("8:55 às 9:45", Integer.valueOf(3));
/* 14 */     this.horarios.put("9:45 às 10:35", Integer.valueOf(4));
/* 15 */     this.horarios.put("10:50 às 11:40", Integer.valueOf(5));
/* 16 */     this.horarios.put("11:40 às 12:30", Integer.valueOf(6));
/*    */ 
/* 18 */     this.horarios.put("13:00 às 13:50", Integer.valueOf(1));
/* 19 */     this.horarios.put("13:50 às 14:40", Integer.valueOf(2));
/* 20 */     this.horarios.put("14:55 às 15:45", Integer.valueOf(3));
/* 21 */     this.horarios.put("15:45 às 16:35", Integer.valueOf(4));
/* 22 */     this.horarios.put("16:50 às 17:40", Integer.valueOf(5));
/* 23 */     this.horarios.put("17:40 às 18:30", Integer.valueOf(6));
/*    */ 
/* 25 */     this.horarios.put("18:45 às 19:35", Integer.valueOf(1));
/* 26 */     this.horarios.put("19:35 às 20:25", Integer.valueOf(2));
/* 27 */     this.horarios.put("20:35 às 21:25", Integer.valueOf(3));
/* 28 */     this.horarios.put("21:25 às 22:15", Integer.valueOf(4));
/*    */   }
/*    */ 
/*    */   public Integer getHorario(String horario) {
/* 32 */     return (Integer)this.horarios.get(horario);
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-client-web-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.web.RetornaHorarioUtil
 * JD-Core Version:    0.6.2
 */