/*    */ package br.ufrn.ct.cronos.web;
/*    */ 
/*    */ import dev.home.componente.web.infra.util.GeradorLog;
/*    */ import dev.home.componente.web.relatorio.util.JasperDataSource;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.util.Collection;
/*    */ import java.util.Locale;
/*    */ import java.util.Map;
/*    */ import javax.servlet.ServletContext;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import net.sf.jasperreports.engine.JRDataSource;
/*    */ import net.sf.jasperreports.engine.JREmptyDataSource;
/*    */ import net.sf.jasperreports.engine.JRException;
/*    */ import net.sf.jasperreports.engine.JRExporter;
/*    */ import net.sf.jasperreports.engine.JRExporterParameter;
/*    */ import net.sf.jasperreports.engine.JasperFillManager;
/*    */ import net.sf.jasperreports.engine.JasperPrint;
/*    */ import net.sf.jasperreports.engine.export.JExcelApiExporter;
/*    */ import net.sf.jasperreports.engine.export.JRCsvExporter;
/*    */ import net.sf.jasperreports.engine.export.JRPdfExporter;
/*    */ import net.sf.jasperreports.engine.export.JRRtfExporter;
/*    */ 
/*    */ public class ReportUtils
/*    */ {
/*    */   private static final String REPORT_NAME = "relatorio";
/*    */   private static final String CSV_Extension = ".csv";
/*    */   private static final String XLS_Extension = ".xls";
/*    */   private static final String RTF_Extension = ".rft";
/*    */   private static final String PDF_Extension = ".pdf";
/*    */ 
/*    */   public static void saveCsvReport(HttpServletResponse response, HttpServletRequest request, Map<?, ?> params)
/*    */   {
/* 45 */     JRExporter exporter = new JRCsvExporter();
/* 46 */     String nomeArquivo = getParamFileName(params) + ".csv";
/* 47 */     response.setContentType("text/plain");
/* 48 */     buildReport(response, request, Boolean.valueOf(false), exporter, nomeArquivo, params);
/*    */   }
/*    */ 
/*    */   public static void saveXlsReport(HttpServletResponse response, HttpServletRequest request, Map<?, ?> params)
/*    */   {
/* 53 */     JRExporter exporter = new JExcelApiExporter();
/* 54 */     String nomeArquivo = getParamFileName(params) + ".xls";
/* 55 */     response.setContentType("application/xls");
/* 56 */     buildReport(response, request, Boolean.valueOf(false), exporter, nomeArquivo, params);
/*    */   }
/*    */ 
/*    */   public static void saveRtfReport(HttpServletResponse response, HttpServletRequest request, Map<?, ?> params)
/*    */   {
/* 61 */     JRExporter exporter = new JRRtfExporter();
/* 62 */     String nomeArquivo = getParamFileName(params) + ".rft";
/* 63 */     response.setContentType("application/rtf");
/* 64 */     buildReport(response, request, Boolean.valueOf(false), exporter, nomeArquivo, params);
/*    */   }
/*    */ 
/*    */   public static void savePdfReport(HttpServletResponse response, HttpServletRequest request, Map<?, ?> params)
/*    */   {
/* 69 */     JRExporter exporter = new JRPdfExporter();
/* 70 */     String nomeArquivo = getParamFileName(params) + ".pdf";
/* 71 */     response.setContentType("application/pdf");
/* 72 */     buildReport(response, request, Boolean.valueOf(false), exporter, nomeArquivo, params);
/*    */   }
/*    */ 
/*    */   private static String getParamFileName(Map<?, ?> params) {
/* 76 */     String nomeArquivo = new String();
/* 77 */     if (params.get("FILE_NAME") != null) {
/* 78 */       nomeArquivo = (String)params.get("FILE_NAME");
/*    */     }
/* 80 */     if ((nomeArquivo == null) || (nomeArquivo.trim().equals(""))) {
/* 81 */       nomeArquivo = "relatorio";
/*    */     }
/* 83 */     return nomeArquivo;
/*    */   }
/*    */ 
/*    */   private static void buildReport(HttpServletResponse response, HttpServletRequest request, Boolean isPrint, JRExporter exporter, String fileName, Map<?, ?> params)
/*    */   {
/*    */     try
/*    */     {
/* 91 */       String path = (String)params.get("jasper:report:file");
/* 92 */       Map parameters = (Map)params.get("jasper:report:parameters");
/*    */ 
/* 94 */       Collection col = (Collection)params.get("jasper:report:data:html");
/*    */ 
/* 96 */       JRDataSource ds = null;
/* 97 */       OutputStream out = null;
/* 98 */       if (parameters != null)
/* 99 */         parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));
/* 100 */       ds = col.isEmpty() ? new JREmptyDataSource() : new JasperDataSource(col);
/*    */ 
/* 102 */       out = response.getOutputStream();
/* 103 */       File reportFile = new File(request.getSession().getServletContext().getRealPath(path));
/*    */ 
/* 105 */       InputStream inputstream = new FileInputStream(reportFile.getAbsolutePath());
/*    */ 
/* 107 */       JasperPrint jp = JasperFillManager.fillReport(inputstream, parameters, ds);
/*    */ 
/* 109 */       ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 110 */       response.setHeader("Content-disposition", "attachment; filename=" + fileName);
/*    */ 
/* 112 */       exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
/* 113 */       exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
/* 114 */       exporter.exportReport();
/* 115 */       byte[] bytes = baos.toByteArray();
/* 116 */       response.setContentLength(bytes.length);
/* 117 */       out = response.getOutputStream();
/* 118 */       out.write(bytes, 0, bytes.length);
/* 119 */       out.flush();
/* 120 */       out.close();
/*    */     } catch (IOException e) {
/* 122 */       GeradorLog.getInstance().geraLog(e, ReportUtils.class);
/*    */     } catch (JRException e) {
/* 124 */       GeradorLog.getInstance().geraLog(e, ReportUtils.class);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-client-web-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.web.ReportUtils
 * JD-Core Version:    0.6.2
 */