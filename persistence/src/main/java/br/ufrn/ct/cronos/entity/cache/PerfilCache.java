/*    */ package br.ufrn.ct.cronos.entity.cache;
/*    */ 
/*    */ import br.ufrn.ct.cronos.entity.PapelUsuario;
/*    */ import dev.home.componente.entity.cache.AbstractCacheEntity;
/*    */ 
/*    */ public class PerfilCache extends AbstractCacheEntity<Long, PapelUsuario>
/*    */ {
/*    */   public String getCacheName()
/*    */   {
/* 10 */     return PapelUsuario.class.getCanonicalName();
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-persistence-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.entity.cache.PerfilCache
 * JD-Core Version:    0.6.2
 */