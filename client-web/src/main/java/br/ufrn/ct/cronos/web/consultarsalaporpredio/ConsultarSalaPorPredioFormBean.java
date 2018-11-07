/*    */ package br.ufrn.ct.cronos.web.consultarsalaporpredio;
/*    */ 
/*    */ import br.ufrn.ct.cronos.consultarsalaporpredio.vo.ConsultarSalaPorPredio;
/*    */ import br.ufrn.ct.cronos.consultarsalaporpredio.vo.RespostaConsultarSalaPorPredio;
/*    */ import br.ufrn.ct.cronos.contadorsalaporpredio.vo.ContadorSalaPorPredio;
/*    */ import br.ufrn.ct.cronos.contadorsalaporpredio.vo.DadosContadorSalaPorPredio;
/*    */ import br.ufrn.ct.cronos.contadorsalaporpredio.vo.RespostaContadorSalaPorPredio;
/*    */ import dev.home.componente.service.Service;
/*    */ import dev.home.componente.service.excecao.NegocioException;
/*    */ import dev.home.componente.service.util.ServiceUtils;
/*    */ import dev.home.componente.web.infra.AbstractConsultarFormBean;
/*    */ import dev.home.componente.web.infra.paginacao.PaginacaoDataModel;
/*    */ import javax.faces.component.UIData;
/*    */ import javax.faces.model.DataModel;
/*    */ 
/*    */ public class ConsultarSalaPorPredioFormBean extends AbstractConsultarFormBean
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long idPredio;
/*    */ 
/*    */   public ConsultarSalaPorPredioFormBean()
/*    */     throws NegocioException
/*    */   {
/* 26 */     viewTable();
/*    */   }
/*    */ 
/*    */   public DataModel getDados() throws NegocioException {
/* 30 */     ConsultarSalaPorPredio solicitacao = new ConsultarSalaPorPredio();
/* 31 */     popularVo(solicitacao);
/* 32 */     Service service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
/* 33 */     RespostaConsultarSalaPorPredio resposta = (RespostaConsultarSalaPorPredio)service.executa(solicitacao);
/* 34 */     resposta.getDados();
/* 35 */     return new PaginacaoDataModel(resposta.getDados(), resposta.getTotalNumeroLinhas().intValue());
/*    */   }
/*    */ 
/*    */   private void viewTable() throws NegocioException {
/* 39 */     ContadorSalaPorPredio solicitacao = new ContadorSalaPorPredio();
/* 40 */     solicitacao.setIdPredio(this.idPredio);
/* 41 */     Service service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
/* 42 */     RespostaContadorSalaPorPredio resposta = (RespostaContadorSalaPorPredio)service.executa(solicitacao);
/* 43 */     setVisible(Boolean.valueOf(((DadosContadorSalaPorPredio)resposta.getObjeto()).getTotal().intValue() > 0));
/*    */   }
/*    */ 
/*    */   private void popularVo(ConsultarSalaPorPredio solicitacao)
/*    */   {
/* 50 */     solicitacao.setIdPredio(this.idPredio);
/* 51 */     solicitacao.setMaxPage(Integer.valueOf(getTable().getRows()));
/* 52 */     solicitacao.setStartPage(Integer.valueOf(getTable().getFirst()));
/*    */   }
/*    */ 
/*    */   public int getLinhas()
/*    */   {
/* 57 */     return 10;
/*    */   }
/*    */ 
/*    */   public Long getIdPredio()
/*    */   {
/* 62 */     return this.idPredio;
/*    */   }
/*    */ 
/*    */   public void setIdPredio(Long idPredio) {
/* 66 */     this.idPredio = idPredio;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-client-web-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.web.consultarsalaporpredio.ConsultarSalaPorPredioFormBean
 * JD-Core Version:    0.6.2
 */