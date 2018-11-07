/*    */ package br.ufrn.ct.cronos.web.consultarusuario;
/*    */ 
/*    */ import br.ufrn.ct.cronos.consultarusuario.vo.ConsultarUsuario;
/*    */ import br.ufrn.ct.cronos.consultarusuario.vo.RespostaConsultarUsuario;
/*    */ import br.ufrn.ct.cronos.contadorusuario.vo.ContadorUsuario;
/*    */ import br.ufrn.ct.cronos.contadorusuario.vo.DadosContadorUsuario;
/*    */ import br.ufrn.ct.cronos.contadorusuario.vo.RespostaContadorUsuario;
/*    */ import dev.home.componente.service.Service;
/*    */ import dev.home.componente.service.excecao.NegocioException;
/*    */ import dev.home.componente.service.util.ServiceUtils;
/*    */ import dev.home.componente.web.infra.AbstractConsultarFormBean;
/*    */ import dev.home.componente.web.infra.paginacao.PaginacaoDataModel;
/*    */ import javax.faces.component.UIData;
/*    */ import javax.faces.model.DataModel;
/*    */ 
/*    */ public class ConsultarUsuarioFormBean extends AbstractConsultarFormBean
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public ConsultarUsuarioFormBean()
/*    */     throws NegocioException
/*    */   {
/* 23 */     viewTable();
/*    */   }
/*    */ 
/*    */   public DataModel getDados() throws NegocioException {
/* 27 */     ConsultarUsuario solicitacao = new ConsultarUsuario();
/* 28 */     popularVo(solicitacao);
/* 29 */     Service service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
/* 30 */     RespostaConsultarUsuario resposta = (RespostaConsultarUsuario)service.executa(solicitacao);
/*    */ 
/* 32 */     return new PaginacaoDataModel(resposta.getDados(), resposta.getTotalNumeroLinhas().intValue());
/*    */   }
/*    */ 
/*    */   private void viewTable() throws NegocioException {
/* 36 */     ContadorUsuario solicitacao = new ContadorUsuario();
/* 37 */     Service service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
/* 38 */     RespostaContadorUsuario resposta = (RespostaContadorUsuario)service.executa(solicitacao);
/* 39 */     setVisible(Boolean.valueOf(((DadosContadorUsuario)resposta.getObjeto()).getTotal().intValue() > 0));
/*    */   }
/*    */ 
/*    */   private void popularVo(ConsultarUsuario solicitacao) {
/* 43 */     solicitacao.setMaxPage(Integer.valueOf(getTable().getRows()));
/* 44 */     solicitacao.setStartPage(Integer.valueOf(getTable().getFirst()));
/*    */   }
/*    */ 
/*    */   public int getLinhas()
/*    */   {
/* 49 */     return 10;
/*    */   }
/*    */ }
