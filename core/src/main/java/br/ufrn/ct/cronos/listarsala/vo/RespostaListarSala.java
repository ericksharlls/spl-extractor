/*    */ package br.ufrn.ct.cronos.listarsala.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.ResponseList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RespostaListarSala extends ResponseList<DadosListarSala>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public RespostaListarSala()
/*    */   {
/*    */   }
/*    */ 
/*    */   public RespostaListarSala(List<DadosListarSala> dadosListarTurma)
/*    */   {
/* 17 */     setDados(dadosListarTurma);
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.listarsala.vo.RespostaListarSala
 * JD-Core Version:    0.6.2
 */