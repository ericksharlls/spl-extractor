/*    */ package br.ufrn.ct.cronos.service.contadorsalaporpredio;
/*    */ 
/*    */ import br.ufrn.ct.cronos.contadorsalaporpredio.vo.ContadorSalaPorPredio;
/*    */ import br.ufrn.ct.cronos.contadorsalaporpredio.vo.DadosContadorSalaPorPredio;
/*    */ import br.ufrn.ct.cronos.contadorsalaporpredio.vo.RespostaContadorSalaPorPredio;
/*    */ import br.ufrn.ct.cronos.dao.SalaDao;
/*    */ import dev.home.componente.service.AbstractService;
/*    */ import dev.home.componente.service.excecao.NegocioException;
/*    */ 
/*    */ public class ServiceContadorSalaPorPredio extends AbstractService<ContadorSalaPorPredio, RespostaContadorSalaPorPredio>
/*    */ {
/*    */   private SalaDao salaDao;
/*    */ 
/*    */   public RespostaContadorSalaPorPredio processa(ContadorSalaPorPredio solicitacao)
/*    */     throws NegocioException
/*    */   {
/* 16 */     Integer total = this.salaDao.countByPredio(solicitacao.getIdPredio());
/* 17 */     DadosContadorSalaPorPredio dadosContadorSala = new DadosContadorSalaPorPredio(total);
/* 18 */     return new RespostaContadorSalaPorPredio(dadosContadorSala);
/*    */   }
/*    */ 
/*    */   public void valida(ContadorSalaPorPredio solicitacao) throws NegocioException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void setSalaDao(SalaDao salaDao)
/*    */   {
/* 27 */     this.salaDao = salaDao;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-service-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.service.contadorsalaporpredio.ServiceContadorSalaPorPredio
 * JD-Core Version:    0.6.2
 */