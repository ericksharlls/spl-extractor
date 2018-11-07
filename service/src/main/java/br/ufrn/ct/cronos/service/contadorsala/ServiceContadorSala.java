/*    */ package br.ufrn.ct.cronos.service.contadorsala;
/*    */ 
/*    */ import br.ufrn.ct.cronos.contadorsala.vo.ContadorSala;
/*    */ import br.ufrn.ct.cronos.contadorsala.vo.DadosContadorSala;
/*    */ import br.ufrn.ct.cronos.contadorsala.vo.RespostaContadorSala;
/*    */ import br.ufrn.ct.cronos.dao.SalaDao;
/*    */ import dev.home.componente.service.AbstractService;
/*    */ import dev.home.componente.service.excecao.NegocioException;
/*    */ 
/*    */ public class ServiceContadorSala extends AbstractService<ContadorSala, RespostaContadorSala>
/*    */ {
/*    */   private SalaDao salaDao;
/*    */ 
/*    */   public RespostaContadorSala processa(ContadorSala solicitacao)
/*    */     throws NegocioException
/*    */   {
/* 16 */     Integer total = this.salaDao.count();
/* 17 */     DadosContadorSala dadosContadorSala = new DadosContadorSala(total);
/* 18 */     return new RespostaContadorSala(dadosContadorSala);
/*    */   }
/*    */ 
/*    */   public void valida(ContadorSala solicitacao) throws NegocioException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void setSalaDao(SalaDao salaDao)
/*    */   {
/* 27 */     this.salaDao = salaDao;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-service-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.service.contadorsala.ServiceContadorSala
 * JD-Core Version:    0.6.2
 */