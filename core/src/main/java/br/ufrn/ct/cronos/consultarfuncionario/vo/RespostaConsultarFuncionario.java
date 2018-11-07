/*    */ package br.ufrn.ct.cronos.consultarfuncionario.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.ResponseListPaged;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RespostaConsultarFuncionario extends ResponseListPaged<DadosConsultarFuncionario>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public RespostaConsultarFuncionario()
/*    */   {
/*    */   }
/*    */ 
/*    */   public RespostaConsultarFuncionario(List<DadosConsultarFuncionario> dados, Integer totalNumeroLinhas)
/*    */   {
/* 16 */     setDados(dados);
/* 17 */     setTotalNumeroLinhas(totalNumeroLinhas);
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.consultarfuncionario.vo.RespostaConsultarFuncionario
 * JD-Core Version:    0.6.2
 */