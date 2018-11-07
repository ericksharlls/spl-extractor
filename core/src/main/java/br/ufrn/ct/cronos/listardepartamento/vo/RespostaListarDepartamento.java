/*    */ package br.ufrn.ct.cronos.listardepartamento.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.ResponseList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RespostaListarDepartamento extends ResponseList<DadosListarDepartamento>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public RespostaListarDepartamento()
/*    */   {
/*    */   }
/*    */ 
/*    */   public RespostaListarDepartamento(List<DadosListarDepartamento> dadosListarDepartamento)
/*    */   {
/* 17 */     setDados(dadosListarDepartamento);
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.listardepartamento.vo.RespostaListarDepartamento
 * JD-Core Version:    0.6.2
 */