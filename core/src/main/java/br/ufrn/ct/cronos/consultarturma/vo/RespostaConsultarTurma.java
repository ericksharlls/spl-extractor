/*    */ package br.ufrn.ct.cronos.consultarturma.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.ResponseListPaged;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RespostaConsultarTurma extends ResponseListPaged<DadosConsultarTurma>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public RespostaConsultarTurma()
/*    */   {
/*    */   }
/*    */ 
/*    */   public RespostaConsultarTurma(List<DadosConsultarTurma> dados, Integer totalNumeroLinhas)
/*    */   {
/* 17 */     setDados(dados);
/* 18 */     setTotalNumeroLinhas(totalNumeroLinhas);
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.consultarturma.vo.RespostaConsultarTurma
 * JD-Core Version:    0.6.2
 */