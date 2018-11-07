/*    */ package br.ufrn.ct.cronos.service.contadorpredio;
/*    */ 
/*    */ import br.ufrn.ct.cronos.contadorpredio.vo.ContadorPredio;
/*    */ import br.ufrn.ct.cronos.contadorpredio.vo.DadosContadorPredio;
/*    */ import br.ufrn.ct.cronos.contadorpredio.vo.RespostaContadorPredio;
/*    */ import br.ufrn.ct.cronos.dao.PredioDao;
/*    */ import dev.home.componente.service.AbstractService;
/*    */ import dev.home.componente.service.excecao.NegocioException;
/*    */ 
/*    */ public class ServiceContadorPredio extends AbstractService<ContadorPredio, RespostaContadorPredio>
/*    */ {
/*    */   private PredioDao predioDao;
/*    */ 
/*    */   public RespostaContadorPredio processa(ContadorPredio solicitacao)
/*    */     throws NegocioException
/*    */   {
/* 16 */     Integer total = this.predioDao.count();
/* 17 */     DadosContadorPredio dadosContadorPredio = new DadosContadorPredio(total);
/* 18 */     return new RespostaContadorPredio(dadosContadorPredio);
/*    */   }
/*    */ 
/*    */   public void valida(ContadorPredio solicitacao) throws NegocioException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void setPredioDao(PredioDao predioDao)
/*    */   {
/* 27 */     this.predioDao = predioDao;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-service-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.service.contadorpredio.ServiceContadorPredio
 * JD-Core Version:    0.6.2
 */