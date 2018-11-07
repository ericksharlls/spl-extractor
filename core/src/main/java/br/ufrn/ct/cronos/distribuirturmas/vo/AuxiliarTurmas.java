/*    */ package br.ufrn.ct.cronos.distribuirturmas.vo;
/*    */ 
/*    */ public class AuxiliarTurmas
/*    */ {
/*    */   public boolean isConsecutivos(String horario1, String horario2)
/*    */   {
/*  6 */     Auxiliar auxiliar = new Auxiliar();
/*    */ 
/*  8 */     String[] arrayDias1 = auxiliar.retornaArrayDias(horario1);
/*  9 */     String[] arrayDias2 = auxiliar.retornaArrayDias(horario2);
/*    */ 
/* 11 */     for (int i = 0; i < arrayDias1.length; i++) {
/* 12 */       if (!arrayDias1[i].equals(arrayDias2[i])) {
/* 13 */         return false;
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 18 */     String[] arrayHorarios1 = auxiliar.retornaArrayHorarios(horario1);
/* 19 */     String[] arrayHorarios2 = auxiliar.retornaArrayHorarios(horario2);
/*    */ 
/* 21 */     for (int i = 0; i < arrayHorarios1.length; i++) {
/* 22 */       if (!arrayHorarios1[i].equals(arrayHorarios2[i])) {
/* 23 */         return false;
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 28 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.distribuirturmas.vo.AuxiliarTurmas
 * JD-Core Version:    0.6.2
 */