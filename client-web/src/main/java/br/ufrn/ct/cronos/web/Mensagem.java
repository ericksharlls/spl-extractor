/*    */ package br.ufrn.ct.cronos.web;
/*    */ 
/*    */ import java.util.ResourceBundle;
/*    */ 
/*    */ public class Mensagem
/*    */ {
/*    */   private static final String MESSAGE_RESOURCE_FILE = "br/ufrn/ct/suporte/web/Mensagens";
/*    */ 
/*    */   public static String getMensagem(String label)
/*    */   {
/* 14 */     return ResourceBundle.getBundle("br/ufrn/ct/suporte/web/Mensagens").getString(label);
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-client-web-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.web.Mensagem
 * JD-Core Version:    0.6.2
 */