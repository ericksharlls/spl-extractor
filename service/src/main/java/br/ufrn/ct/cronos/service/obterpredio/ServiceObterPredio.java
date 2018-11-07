/*    */ package br.ufrn.ct.cronos.service.obterpredio;
/*    */ 
/*    */ import br.ufrn.ct.cronos.dao.PredioDao;
/*    */ import br.ufrn.ct.cronos.entity.Predio;
/*    */ import br.ufrn.ct.cronos.obterpredio.vo.DadosObterPredio;
/*    */ import br.ufrn.ct.cronos.obterpredio.vo.ObterPredio;
/*    */ import br.ufrn.ct.cronos.obterpredio.vo.RespostaObterPredio;
/*    */ import dev.home.componente.service.AbstractService;
/*    */ import dev.home.componente.service.excecao.NegocioException;
/*    */ 
/*    */ public class ServiceObterPredio extends AbstractService<ObterPredio, RespostaObterPredio>
/*    */ {
/*    */   private PredioDao predioDao;
/*    */ 
/*    */   public RespostaObterPredio processa(ObterPredio solicitacao)
/*    */     throws NegocioException
/*    */   {
/* 18 */     Predio predio = (Predio)this.predioDao.get(solicitacao.getId());
/* 19 */     if (predio == null) throw new NegocioException(19);
/* 20 */     DadosObterPredio dados = new DadosObterPredio();
/* 21 */     dados.setId(predio.getId());
/* 22 */     dados.setNome(predio.getNome());
/* 23 */     dados.setDescricao(predio.getDescricao());
/* 24 */     return new RespostaObterPredio(dados);
/*    */   }
/*    */ 
/*    */   public void valida(ObterPredio solicitacao) throws NegocioException
/*    */   {
/* 29 */     if (solicitacao.getId() == null)
/* 30 */       throw new NegocioException(2);
/*    */   }
/*    */ 
/*    */   public void setPredioDao(PredioDao predioDao)
/*    */   {
/* 35 */     this.predioDao = predioDao;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-service-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.service.obterpredio.ServiceObterPredio
 * JD-Core Version:    0.6.2
 */