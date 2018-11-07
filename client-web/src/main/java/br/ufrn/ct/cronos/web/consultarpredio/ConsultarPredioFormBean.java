/*    */ package br.ufrn.ct.cronos.web.consultarpredio;
/*    */ 
/*    */ import br.ufrn.ct.cronos.consultarpredio.vo.ConsultarPredio;
/*    */ import br.ufrn.ct.cronos.consultarpredio.vo.RespostaConsultarPredio;
/*    */ import br.ufrn.ct.cronos.contadorpredio.vo.ContadorPredio;
/*    */ import br.ufrn.ct.cronos.contadorpredio.vo.DadosContadorPredio;
/*    */ import br.ufrn.ct.cronos.contadorpredio.vo.RespostaContadorPredio;
/*    */ import dev.home.componente.service.Service;
/*    */ import dev.home.componente.service.excecao.NegocioException;
/*    */ import dev.home.componente.service.util.ServiceUtils;
/*    */ import dev.home.componente.web.infra.AbstractConsultarFormBean;
/*    */ import dev.home.componente.web.infra.paginacao.PaginacaoDataModel;
/*    */ import javax.faces.component.UIData;
/*    */ import javax.faces.model.DataModel;
/*    */ 
/*    */ public class ConsultarPredioFormBean extends AbstractConsultarFormBean
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public ConsultarPredioFormBean()
/*    */     throws NegocioException
/*    */   {
/* 23 */     viewTable();
/*    */   }
/*    */ 
/*    */   public DataModel getDados() throws NegocioException {
/* 27 */     ConsultarPredio solicitacao = new ConsultarPredio();
/* 28 */     popularVo(solicitacao);
/* 29 */     Service service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
/* 30 */     RespostaConsultarPredio resposta = (RespostaConsultarPredio)service.executa(solicitacao);
/* 31 */     return new PaginacaoDataModel(resposta.getDados(), resposta.getTotalNumeroLinhas().intValue());
/*    */   }
/*    */ 
/*    */   private void viewTable() throws NegocioException {
/* 35 */     ContadorPredio solicitacao = new ContadorPredio();
/* 36 */     Service service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
/* 37 */     RespostaContadorPredio resposta = (RespostaContadorPredio)service.executa(solicitacao);
/* 38 */     setVisible(Boolean.valueOf(((DadosContadorPredio)resposta.getObjeto()).getTotal().intValue() > 0));
/*    */   }
/*    */ 
/*    */   private void popularVo(ConsultarPredio solicitacao) {
/* 42 */     solicitacao.setMaxPage(Integer.valueOf(getTable().getRows()));
/* 43 */     solicitacao.setStartPage(Integer.valueOf(getTable().getFirst()));
/*    */   }
/*    */ 
/*    */   public int getLinhas()
/*    */   {
/* 48 */     return 10;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-client-web-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.web.consultarpredio.ConsultarPredioFormBean
 * JD-Core Version:    0.6.2
 */