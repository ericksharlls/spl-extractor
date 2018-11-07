/*    */ package br.ufrn.ct.cronos.distribuirturmas.vo;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class MatcherDemo
/*    */ {
/*    */   private static final String REGEX = "[2-6]+[MTN][1-6]+";
/*    */   private static final String INPUT = " - -23T123 -34N56";
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 13 */     Pattern p = Pattern.compile("[2-6]+[MTN][1-6]+");
/* 14 */     Matcher m = p.matcher(" - -23T123 -34N56");
/* 15 */     int count = 0;
/* 16 */     while (m.find()) {
/* 17 */       count++;
/* 18 */       System.out.println("Match number " + count);
/* 19 */       System.out.println("start(): " + m.start());
/* 20 */       System.out.println("end(): " + m.end());
/* 21 */       System.out.println("Grupo:" + m.group());
/*    */     }
/* 23 */     System.out.println("Aki:" + m.groupCount());
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.distribuirturmas.vo.MatcherDemo
 * JD-Core Version:    0.6.2
 */