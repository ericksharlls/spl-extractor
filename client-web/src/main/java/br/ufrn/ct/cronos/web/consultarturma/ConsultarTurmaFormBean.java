/*    */ package br.ufrn.ct.cronos.web.consultarturma;
/*    */ 
/*    */ import br.ufrn.ct.cronos.consultarturma.vo.ConsultarTurma;
/*    */ import br.ufrn.ct.cronos.consultarturma.vo.RespostaConsultarTurma;
/*    */ import br.ufrn.ct.cronos.contadorturma.vo.ContadorTurma;
/*    */ import br.ufrn.ct.cronos.contadorturma.vo.DadosContadorTurma;
/*    */ import br.ufrn.ct.cronos.contadorturma.vo.RespostaContadorTurma;
/*    */ import dev.home.componente.service.Service;
/*    */ import dev.home.componente.service.excecao.NegocioException;
/*    */ import dev.home.componente.service.util.ServiceUtils;
/*    */ import dev.home.componente.web.infra.AbstractConsultarFormBean;
/*    */ import dev.home.componente.web.infra.paginacao.PaginacaoDataModel;
/*    */ import javax.faces.component.UIData;
/*    */ import javax.faces.model.DataModel;
/*    */ 
/*    */ public class ConsultarTurmaFormBean extends AbstractConsultarFormBean
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public ConsultarTurmaFormBean()
/*    */     throws NegocioException
/*    */   {
/* 23 */     viewTable();
/*    */   }
/*    */ 
/*    */   public DataModel getDados() throws NegocioException {
/* 27 */     ConsultarTurma solicitacao = new ConsultarTurma();
/* 28 */     popularVo(solicitacao);
/* 29 */     Service service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
/* 30 */     RespostaConsultarTurma resposta = (RespostaConsultarTurma)service.executa(solicitacao);
/* 31 */     return new PaginacaoDataModel(resposta.getDados(), resposta.getTotalNumeroLinhas().intValue());
/*    */   }
/*    */ 
/*    */   private void viewTable() throws NegocioException {
/* 35 */     ContadorTurma solicitacao = new ContadorTurma();
/* 36 */     Service service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
/* 37 */     RespostaContadorTurma resposta = (RespostaContadorTurma)service.executa(solicitacao);
/* 38 */     setVisible(Boolean.valueOf(((DadosContadorTurma)resposta.getObjeto()).getTotal().intValue() > 0));
/*    */   }
/*    */ 
/*    */   private void popularVo(ConsultarTurma solicitacao) {
/* 42 */     solicitacao.setMaxPage(Integer.valueOf(getTable().getRows()));
/* 43 */     solicitacao.setStartPage(Integer.valueOf(getTable().getFirst()));
/*    */   }
/*    */ 
/*    */   public int getLinhas()
/*    */   {
/* 48 */     return 15;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-client-web-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.web.consultarturma.ConsultarTurmaFormBean
 * JD-Core Version:    0.6.2
 */