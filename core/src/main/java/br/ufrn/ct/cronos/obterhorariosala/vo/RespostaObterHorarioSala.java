/*    */ package br.ufrn.ct.cronos.obterhorariosala.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.ResponseList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RespostaObterHorarioSala extends ResponseList<DadosTurma>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public RespostaObterHorarioSala(List<DadosTurma> dados)
/*    */   {
/* 12 */     setDados(dados);
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.obterhorariosala.vo.RespostaObterHorarioSala
 * JD-Core Version:    0.6.2
 */