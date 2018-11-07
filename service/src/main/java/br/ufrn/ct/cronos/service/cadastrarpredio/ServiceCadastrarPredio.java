/*    */ package br.ufrn.ct.cronos.service.cadastrarpredio;
/*    */ 
/*    */ import br.ufrn.ct.cronos.cadastrarpredio.vo.CadastrarPredio;
/*    */ import br.ufrn.ct.cronos.cadastrarpredio.vo.RespostaCadastrarPredio;
/*    */ import br.ufrn.ct.cronos.dao.PredioDao;
/*    */ import br.ufrn.ct.cronos.entity.Predio;
/*    */ import dev.home.componente.service.AbstractService;
/*    */ import dev.home.componente.service.excecao.NegocioException;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class ServiceCadastrarPredio extends AbstractService<CadastrarPredio, RespostaCadastrarPredio>
/*    */ {
/*    */   private PredioDao predioDao;
/*    */ 
/*    */   public RespostaCadastrarPredio processa(CadastrarPredio solicitacao)
/*    */     throws NegocioException
/*    */   {
/* 19 */     Predio predio = new Predio();
/* 20 */     predio.setNome(solicitacao.getNome());
/* 21 */     predio.setDescricao(solicitacao.getDescricao());
/* 22 */     this.predioDao.save(predio);
/* 23 */     return new RespostaCadastrarPredio();
/*    */   }
/*    */ 
/*    */   public void valida(CadastrarPredio solicitacao) throws NegocioException
/*    */   {
/* 28 */     if (StringUtils.isBlank(solicitacao.getNome())) {
/* 29 */       throw new NegocioException(3);
/*    */     }
/* 31 */     if (StringUtils.isBlank(solicitacao.getDescricao()))
/* 32 */       throw new NegocioException(12);
/*    */   }
/*    */ 
/*    */   public void setPredioDao(PredioDao predioDao)
/*    */   {
/* 37 */     this.predioDao = predioDao;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-service-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.service.cadastrarpredio.ServiceCadastrarPredio
 * JD-Core Version:    0.6.2
 */