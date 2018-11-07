/*    */ package br.ufrn.ct.cronos.consultarsala.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.ResponseListPaged;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RespostaConsultarSala extends ResponseListPaged<DadosConsultarSala>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public RespostaConsultarSala()
/*    */   {
/*    */   }
/*    */ 
/*    */   public RespostaConsultarSala(List<DadosConsultarSala> dados, Integer totalNumeroLinhas)
/*    */   {
/* 17 */     setDados(dados);
/* 18 */     setTotalNumeroLinhas(totalNumeroLinhas);
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.consultarsala.vo.RespostaConsultarSala
 * JD-Core Version:    0.6.2
 */