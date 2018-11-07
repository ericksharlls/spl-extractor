/*    */ package br.ufrn.ct.cronos.web.converter;
/*    */ 
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import java.util.ResourceBundle;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import javax.faces.application.FacesMessage;
/*    */ import javax.faces.component.UIComponent;
/*    */ import javax.faces.context.FacesContext;
/*    */ import javax.faces.convert.Converter;
/*    */ import javax.faces.convert.ConverterException;
/*    */ 
/*    */ public class CustomTimeConverter
/*    */   implements Converter
/*    */ {
/* 17 */   private static final Pattern REGEX_HORA = Pattern.compile("^([0-2][0-3]|[2][0-3]):[0-5][0-9]$");
/*    */   private static final String MESSAGE_RESOURCE_FILE = "br/ufrn/ct/cronos/web/Mensagens";
/* 19 */   private static final SimpleDateFormat FORMATO = new SimpleDateFormat("HH:mm");
/*    */ 
/*    */   public Object getAsObject(FacesContext context, UIComponent component, String value)
/*    */   {
/* 27 */     Date hora = null;
/* 28 */     if ((value != null) && (value.trim().length() > 0)) {
/* 29 */       String msgKey = null;
/* 30 */       if (REGEX_HORA.matcher(value).matches())
/*    */         try {
/* 32 */           hora = FORMATO.parse(value);
/*    */         } catch (ParseException e) {
/* 34 */           msgKey = "suporte.erro.valida";
/*    */         }
/*    */       else {
/* 37 */         msgKey = "suporte.erro.valida";
/*    */       }
/* 39 */       if (msgKey != null) {
/* 40 */         String nomeCampo = component.getId();
/* 41 */         String msg = ResourceBundle.getBundle("br/ufrn/ct/cronos/web/Mensagens").getString(msgKey + "." + nomeCampo);
/* 42 */         FacesMessage message = new FacesMessage(msg);
/* 43 */         message.setSeverity(FacesMessage.SEVERITY_ERROR);
/* 44 */         throw new ConverterException(message);
/*    */       }
/*    */     }
/* 47 */     return hora;
/*    */   }
/*    */ 
/*    */   public String getAsString(FacesContext context, UIComponent component, Object value)
/*    */   {
/* 52 */     String horaFormatada = null;
/* 53 */     horaFormatada = FORMATO.format(value);
/* 54 */     return horaFormatada;
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/* 22 */     FORMATO.setLenient(false);
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-client-web-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.web.converter.CustomTimeConverter
 * JD-Core Version:    0.6.2
 */