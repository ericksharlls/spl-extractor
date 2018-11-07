/*    */ package br.ufrn.ct.cronos.consultarpredio.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.ResponseListPaged;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RespostaConsultarPredio extends ResponseListPaged<DadosConsultarPredio>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public RespostaConsultarPredio()
/*    */   {
/*    */   }
/*    */ 
/*    */   public RespostaConsultarPredio(List<DadosConsultarPredio> dados, Integer totalNumeroLinhas)
/*    */   {
/* 17 */     setDados(dados);
/* 18 */     setTotalNumeroLinhas(totalNumeroLinhas);
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.consultarpredio.vo.RespostaConsultarPredio
 * JD-Core Version:    0.6.2
 */