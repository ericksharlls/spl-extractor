/*    */ package br.ufrn.ct.cronos.service.consultarpredio;
/*    */ 
/*    */ import br.ufrn.ct.cronos.consultarpredio.vo.ConsultarPredio;
/*    */ import br.ufrn.ct.cronos.consultarpredio.vo.DadosConsultarPredio;
/*    */ import br.ufrn.ct.cronos.consultarpredio.vo.RespostaConsultarPredio;
/*    */ import br.ufrn.ct.cronos.dao.PredioDao;
/*    */ import br.ufrn.ct.cronos.entity.Predio;
/*    */ import dev.home.componente.service.AbstractService;
/*    */ import dev.home.componente.service.excecao.NegocioException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ServiceConsultarPredio extends AbstractService<ConsultarPredio, RespostaConsultarPredio>
/*    */ {
/*    */   private PredioDao predioDao;
/*    */ 
/*    */   public RespostaConsultarPredio processa(ConsultarPredio solicitacao)
/*    */     throws NegocioException
/*    */   {
/* 20 */     List<Predio> lista = this.predioDao.findAll(solicitacao.getStartPage().intValue(), solicitacao.getMaxPage().intValue());
/* 21 */     Integer totalNumeroLinhas = this.predioDao.count();
/* 22 */     List<DadosConsultarPredio> dadosConsultarPredio = new ArrayList<DadosConsultarPredio>(lista.size());
/* 23 */     for (Predio predio : lista) {
/* 24 */       DadosConsultarPredio dados = new DadosConsultarPredio();
/* 25 */       dados.setId(predio.getId());
/* 26 */       dados.setNome(predio.getNome());
/* 27 */       dadosConsultarPredio.add(dados);
/*    */     }
/* 29 */     return new RespostaConsultarPredio(dadosConsultarPredio, totalNumeroLinhas);
/*    */   }
/*    */ 
/*    */   public void valida(ConsultarPredio solicitacao) throws NegocioException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void setPredioDao(PredioDao predioDao)
/*    */   {
/* 38 */     this.predioDao = predioDao;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-service-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.service.consultarpredio.ServiceConsultarPredio
 * JD-Core Version:    0.6.2
 */