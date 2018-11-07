/*    */ package br.ufrn.ct.cronos.listaroperacao.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.ResponseList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RespostaListarOperacao extends ResponseList<DadosListarOperacao>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public RespostaListarOperacao()
/*    */   {
/*    */   }
/*    */ 
/*    */   public RespostaListarOperacao(List<DadosListarOperacao> dadosListarOperacao)
/*    */   {
/* 17 */     setDados(dadosListarOperacao);
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.listaroperacao.vo.RespostaListarOperacao
 * JD-Core Version:    0.6.2
 */