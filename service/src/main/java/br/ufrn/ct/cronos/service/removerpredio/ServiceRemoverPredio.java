/*    */ package br.ufrn.ct.cronos.service.removerpredio;
/*    */ 
/*    */ import br.ufrn.ct.cronos.dao.PredioDao;
/*    */ import br.ufrn.ct.cronos.entity.Predio;
/*    */ import br.ufrn.ct.cronos.removerpredio.vo.RemoverPredio;
/*    */ import br.ufrn.ct.cronos.removerpredio.vo.RespostaRemoverPredio;
/*    */ import dev.home.componente.service.AbstractService;
/*    */ import dev.home.componente.service.excecao.NegocioException;
/*    */ 
/*    */ public class ServiceRemoverPredio extends AbstractService<RemoverPredio, RespostaRemoverPredio>
/*    */ {
/*    */   private PredioDao predioDao;
/*    */ 
/*    */   public RespostaRemoverPredio processa(RemoverPredio solicitacao)
/*    */     throws NegocioException
/*    */   {
/* 17 */     Predio predio = new Predio();
/* 18 */     predio.setId(solicitacao.getId());
/* 19 */     this.predioDao.delete(predio);
/* 20 */     return new RespostaRemoverPredio();
/*    */   }
/*    */ 
/*    */   public void valida(RemoverPredio solicitacao) throws NegocioException
/*    */   {
/* 25 */     if (solicitacao.getId() == null)
/* 26 */       throw new NegocioException(2);
/*    */   }
/*    */ 
/*    */   public void setPredioDao(PredioDao predioDao)
/*    */   {
/* 31 */     this.predioDao = predioDao;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-service-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.service.removerpredio.ServiceRemoverPredio
 * JD-Core Version:    0.6.2
 */