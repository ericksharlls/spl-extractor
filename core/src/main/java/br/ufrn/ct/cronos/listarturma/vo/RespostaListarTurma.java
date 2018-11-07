/*    */ package br.ufrn.ct.cronos.listarturma.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.ResponseList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RespostaListarTurma extends ResponseList<DadosListarTurma>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public RespostaListarTurma()
/*    */   {
/*    */   }
/*    */ 
/*    */   public RespostaListarTurma(List<DadosListarTurma> dadosListarTurma)
/*    */   {
/* 17 */     setDados(dadosListarTurma);
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.listarturma.vo.RespostaListarTurma
 * JD-Core Version:    0.6.2
 */