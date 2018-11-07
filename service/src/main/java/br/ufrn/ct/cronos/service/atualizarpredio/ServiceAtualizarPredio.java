/*    */ package br.ufrn.ct.cronos.service.atualizarpredio;
/*    */ 
/*    */ import br.ufrn.ct.cronos.atualizarpredio.vo.AtualizarPredio;
/*    */ import br.ufrn.ct.cronos.atualizarpredio.vo.RespostaAtualizarPredio;
/*    */ import br.ufrn.ct.cronos.dao.PredioDao;
/*    */ import br.ufrn.ct.cronos.entity.Predio;
/*    */ import dev.home.componente.service.AbstractService;
/*    */ import dev.home.componente.service.excecao.NegocioException;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class ServiceAtualizarPredio extends AbstractService<AtualizarPredio, RespostaAtualizarPredio>
/*    */ {
/*    */   private PredioDao predioDao;
/*    */ 
/*    */   public RespostaAtualizarPredio processa(AtualizarPredio solicitacao)
/*    */     throws NegocioException
/*    */   {
/* 19 */     Predio predio = new Predio();
/* 20 */     predio.setId(solicitacao.getId());
/* 21 */     predio.setNome(solicitacao.getNome());
/* 22 */     predio.setDescricao(solicitacao.getDescricao());
/* 23 */     this.predioDao.merge(predio);
/* 24 */     return new RespostaAtualizarPredio();
/*    */   }
/*    */ 
/*    */   public void valida(AtualizarPredio solicitacao) throws NegocioException
/*    */   {
/* 29 */     if (StringUtils.isBlank(solicitacao.getNome())) {
/* 30 */       throw new NegocioException(3);
/*    */     }
/* 32 */     if (StringUtils.isBlank(solicitacao.getDescricao()))
/* 33 */       throw new NegocioException(12);
/*    */   }
/*    */ 
/*    */   public void setPredioDao(PredioDao predioDao)
/*    */   {
/* 38 */     this.predioDao = predioDao;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-service-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.service.atualizarpredio.ServiceAtualizarPredio
 * JD-Core Version:    0.6.2
 */