/*    */ package br.ufrn.ct.cronos.consultarusuario.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.ResponseListPaged;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RespostaConsultarUsuario extends ResponseListPaged<DadosConsultarUsuario>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public RespostaConsultarUsuario()
/*    */   {
/*    */   }
/*    */ 
/*    */   public RespostaConsultarUsuario(List<DadosConsultarUsuario> dados, Integer totalNumeroLinhas)
/*    */   {
/* 17 */     setDados(dados);
/* 18 */     setTotalNumeroLinhas(totalNumeroLinhas);
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.consultarusuario.vo.RespostaConsultarUsuario
 * JD-Core Version:    0.6.2
 */