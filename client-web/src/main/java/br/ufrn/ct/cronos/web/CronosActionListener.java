/*    */ package br.ufrn.ct.cronos.web;
/*    */ 
/*    */ import com.sun.faces.application.ActionListenerImpl;
/*    */ import dev.home.componente.service.excecao.NegocioException;
/*    */ import javax.faces.application.Application;
/*    */ import javax.faces.application.FacesMessage;
/*    */ import javax.faces.application.NavigationHandler;
/*    */ import javax.faces.component.UIComponent;
/*    */ import javax.faces.context.FacesContext;
/*    */ import javax.faces.event.ActionEvent;
/*    */ import javax.faces.event.ActionListener;
/*    */ 
/*    */ public class CronosActionListener extends ActionListenerImpl
/*    */   implements ActionListener
/*    */ {
/*    */   public void processAction(ActionEvent event)
/*    */   {
/*    */     try
/*    */     {
/* 19 */       super.processAction(event);
/*    */     } catch (Throwable e) {
/* 21 */       int index = 1;
/* 22 */       String summary = null;
/* 23 */       String detail = null;
/* 24 */       Throwable cause = e;
/* 25 */       FacesContext facesContext = FacesContext.getCurrentInstance();
/*    */       do {
/* 27 */         if ((cause instanceof NegocioException)) {
/* 28 */           NegocioException excecao = (NegocioException)cause;
/* 29 */           summary = ConversorErrorCode.getInstance().converte(excecao);
/* 30 */           detail = excecao.getLocalizedMessage();
/* 31 */           break;
/*    */         }
/* 33 */         cause = cause.getCause();
/* 34 */       }while ((cause != null) && (index++ <= 10));
/*    */ 
/* 36 */       if (summary == null) {
/* 37 */         summary = "Erro desconhecido";
/* 38 */         detail = e.getLocalizedMessage();
/*    */       }
/* 40 */       facesContext.addMessage(event.getComponent().getClientId(facesContext), new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
/* 41 */       Application application = facesContext.getApplication();
/* 42 */       NavigationHandler navigationHandler = application.getNavigationHandler();
/* 43 */       navigationHandler.handleNavigation(facesContext, null, null);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-client-web-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.web.CronosActionListener
 * JD-Core Version:    0.6.2
 */