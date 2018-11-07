/*    */ package br.ufrn.ct.cronos.distribuirturmas.vo;
/*    */ 
/*    */ import java.util.StringTokenizer;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class AuxiliarVersaoAntiga
/*    */ {
/*    */   public String[] retornaArrayDias(String horarioTurma)
/*    */   {
/* 15 */     horarioTurma = horarioTurma.trim();
/* 16 */     StringTokenizer tokenizer = new StringTokenizer(horarioTurma);
/* 17 */     String stringDias = tokenizer.nextToken("[MTN]");
/* 18 */     String[] arrayDias = new String[stringDias.length()];
/* 19 */     for (int i = 0; i < stringDias.length(); i++) {
/* 20 */       arrayDias[i] = stringDias.substring(i, i + 1);
/*    */     }
/* 22 */     return arrayDias;
/*    */   }
/*    */ 
/*    */   public String retornaTurno(String horarioTurma) {
/* 26 */     horarioTurma = horarioTurma.trim();
/* 27 */     String turno = null;
/* 28 */     String[] temporario = horarioTurma.split("\\d");
/* 29 */     for (int i = 0; i < temporario.length; i++) {
/* 30 */       if (!temporario[i].equals("")) {
/* 31 */         turno = temporario[i];
/*    */       }
/*    */     }
/* 34 */     return turno;
/*    */   }
/*    */ 
/*    */   public String[] retornaArrayHorarios(String horarioTurma) {
/* 38 */     horarioTurma = horarioTurma.trim();
/* 39 */     StringTokenizer tokenizer = new StringTokenizer(horarioTurma);
/* 40 */     tokenizer.nextToken("[MTN]");
/* 41 */     String stringHorarios = tokenizer.nextToken("[MTN]");
/* 42 */     String[] arrayHorarios = new String[stringHorarios.length()];
/* 43 */     for (int i = 0; i < stringHorarios.length(); i++) {
/* 44 */       arrayHorarios[i] = stringHorarios.substring(i, i + 1);
/*    */     }
/* 46 */     return arrayHorarios;
/*    */   }
/*    */ 
/*    */   public boolean validar2(String horarioTurma) {
/* 50 */     Pattern padrao = Pattern.compile("\\d+[MTN]\\d+");
/* 51 */     Matcher pesquisa = padrao.matcher(horarioTurma);
/* 52 */     if (pesquisa.matches()) {
/* 53 */       return true;
/*    */     }
/* 55 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean validar(String horarioTurma) {
/* 59 */     Pattern padrao = Pattern.compile("[2-6]+[MTN][1-6]+");
/* 60 */     Matcher pesquisa = padrao.matcher(horarioTurma);
/* 61 */     if (pesquisa.find()) {
/* 62 */       return true;
/*    */     }
/* 64 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.distribuirturmas.vo.AuxiliarVersaoAntiga
 * JD-Core Version:    0.6.2
 */