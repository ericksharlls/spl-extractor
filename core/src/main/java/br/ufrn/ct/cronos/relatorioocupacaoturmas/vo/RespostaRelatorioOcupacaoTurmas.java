/*    */ package br.ufrn.ct.cronos.relatorioocupacaoturmas.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.ResponseList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RespostaRelatorioOcupacaoTurmas extends ResponseList<DadosOcupacaoTurmas>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public RespostaRelatorioOcupacaoTurmas(List<DadosOcupacaoTurmas> dados)
/*    */   {
/* 12 */     setDados(dados);
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.relatorioocupacaoturmas.vo.RespostaRelatorioOcupacaoTurmas
 * JD-Core Version:    0.6.2
 */