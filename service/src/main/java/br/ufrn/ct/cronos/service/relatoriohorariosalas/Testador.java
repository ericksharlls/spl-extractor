/*    */ package br.ufrn.ct.cronos.service.relatoriohorariosalas;
/*    */ 
/*    */ import br.ufrn.ct.cronos.distribuirturmas.vo.Auxiliar;
/*    */ import java.io.PrintStream;
/*    */ import java.text.DateFormat;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class Testador
/*    */ {
/*    */   public static void main(String[] args)
/*    */     throws ParseException
/*    */   {
/* 17 */     DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
/* 18 */     Date dataInicial = formatter.parse("2012/04/13 08:10:59");
/* 19 */     Date dataFinal = formatter.parse("2012/04/15 08:00:59");
/* 20 */     Date dataIntermediaria = formatter.parse("2012/04/13 08:11:59");
/* 21 */     AuxiliarRelatorioHorarioSalas aux = new AuxiliarRelatorioHorarioSalas();
/*    */ 
/* 23 */     Auxiliar auxiliar = new Auxiliar();
/* 24 */     String[] diasDaSemana = auxiliar.retornaArrayDias("24T12");
/* 25 */     for (int i = 0; i < diasDaSemana.length; i++)
/* 26 */       System.out.println(diasDaSemana[i]);
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-service-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.service.relatoriohorariosalas.Testador
 * JD-Core Version:    0.6.2
 */