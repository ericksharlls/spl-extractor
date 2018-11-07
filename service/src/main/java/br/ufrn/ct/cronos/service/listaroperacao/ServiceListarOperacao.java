/*    */ package br.ufrn.ct.cronos.service.listaroperacao;
/*    */ 
/*    */ import br.ufrn.ct.cronos.dao.OperacaoDao;
/*    */ import br.ufrn.ct.cronos.entity.Operacao;
/*    */ import br.ufrn.ct.cronos.listaroperacao.vo.DadosListarOperacao;
/*    */ import br.ufrn.ct.cronos.listaroperacao.vo.ListarOperacao;
/*    */ import br.ufrn.ct.cronos.listaroperacao.vo.RespostaListarOperacao;
/*    */ import dev.home.componente.service.AbstractService;
/*    */ import dev.home.componente.service.excecao.NegocioException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ServiceListarOperacao extends AbstractService<ListarOperacao, RespostaListarOperacao>
/*    */ {
/*    */   private OperacaoDao operacaoDao;
/*    */ 
/*    */   public RespostaListarOperacao processa(ListarOperacao solicitacao)
/*    */     throws NegocioException
/*    */   {
/* 20 */     List<Operacao> operacoes = this.operacaoDao.findAll();
/* 21 */     List<DadosListarOperacao> dadosListarOperacao = new ArrayList<DadosListarOperacao>();
/*    */ 
/* 23 */     for (Operacao o : operacoes) {
/* 24 */       DadosListarOperacao dados = new DadosListarOperacao();
/* 25 */       dados.setId(o.getId());
/* 26 */       dados.setNome(o.getNome());
/* 27 */       dadosListarOperacao.add(dados);
/*    */     }
/*    */ 
/* 30 */     return new RespostaListarOperacao(dadosListarOperacao);
/*    */   }
/*    */ 
/*    */   public void valida(ListarOperacao solicitacao)
/*    */     throws NegocioException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void setOperacaoDao(OperacaoDao operacaoDao)
/*    */   {
/* 40 */     this.operacaoDao = operacaoDao;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-service-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.service.listaroperacao.ServiceListarOperacao
 * JD-Core Version:    0.6.2
 */