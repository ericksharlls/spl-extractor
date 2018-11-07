/*    */ package br.ufrn.ct.cronos.listartiposala.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.ResponseList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RespostaListarTipoSala extends ResponseList<DadosListarTipoSala>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public RespostaListarTipoSala()
/*    */   {
/*    */   }
/*    */ 
/*    */   public RespostaListarTipoSala(List<DadosListarTipoSala> dadosListarSala)
/*    */   {
/* 17 */     setDados(dadosListarSala);
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.listartiposala.vo.RespostaListarTipoSala
 * JD-Core Version:    0.6.2
 */