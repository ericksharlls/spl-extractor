/*    */ package br.ufrn.ct.cronos.listarperfil.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.ResponseList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RespostaListarPerfil extends ResponseList<DadosListarPerfil>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public RespostaListarPerfil()
/*    */   {
/*    */   }
/*    */ 
/*    */   public RespostaListarPerfil(List<DadosListarPerfil> dadosListarPerfil)
/*    */   {
/* 16 */     setDados(dadosListarPerfil);
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.listarperfil.vo.RespostaListarPerfil
 * JD-Core Version:    0.6.2
 */