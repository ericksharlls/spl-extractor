/*    */ package br.ufrn.ct.cronos.relatoriosalascheias.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.ResponseList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RespostaRelatorioSalasCheias extends ResponseList<DadosRelatorioSalasCheias>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public RespostaRelatorioSalasCheias(List<DadosRelatorioSalasCheias> dados)
/*    */   {
/* 12 */     setDados(dados);
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.relatoriosalascheias.vo.RespostaRelatorioSalasCheias
 * JD-Core Version:    0.6.2
 */