/*    */ package br.ufrn.ct.cronos.entity.cache;
/*    */ 
/*    */ import br.ufrn.ct.cronos.entity.Funcionario;
/*    */ import dev.home.componente.entity.cache.AbstractCacheEntity;
/*    */ 
/*    */ public class FuncionarioCache extends AbstractCacheEntity<Long, Funcionario>
/*    */ {
/*    */   public String getCacheName()
/*    */   {
/* 10 */     return Funcionario.class.getCanonicalName();
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-persistence-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.entity.cache.FuncionarioCache
 * JD-Core Version:    0.6.2
 */