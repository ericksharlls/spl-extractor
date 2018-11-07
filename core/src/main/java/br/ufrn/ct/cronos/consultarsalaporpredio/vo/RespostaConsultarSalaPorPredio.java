/*    */ package br.ufrn.ct.cronos.consultarsalaporpredio.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.ResponseListPaged;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RespostaConsultarSalaPorPredio extends ResponseListPaged<DadosConsultarSalaPorPredio>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public RespostaConsultarSalaPorPredio()
/*    */   {
/*    */   }
/*    */ 
/*    */   public RespostaConsultarSalaPorPredio(List<DadosConsultarSalaPorPredio> dados, Integer totalNumeroLinhas)
/*    */   {
/* 17 */     setDados(dados);
/* 18 */     setTotalNumeroLinhas(totalNumeroLinhas);
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.consultarsalaporpredio.vo.RespostaConsultarSalaPorPredio
 * JD-Core Version:    0.6.2
 */