package br.ufrn.ct.cronos.entity;

import java.io.Serializable;

public class PermissaoUsuario implements Serializable {

   private static final long serialVersionUID = 1L;
   private Long idUsuario;
   private Long idPapel;

   public PermissaoUsuario() {
      super();
   }

   public PermissaoUsuario(Long idUsuario, Long idPapel) {
      super();
      this.idUsuario = idUsuario;
      this.idPapel = idPapel;
   }


   /**
    * Recupera o valor do atributo idUsuario
    * @return o idUsuario
    */
   public Long getIdUsuario() {
      return idUsuario;
   }


   /**
    * Atribui o novo valor de idUsuario
    * @param idUsuario idUsuario que será atribuído
    */
   public void setIdUsuario(Long idUsuario) {
      this.idUsuario = idUsuario;
   }


   /**
    * Recupera o valor do atributo idPapel
    * @return o idPapel
    */
   public Long getIdPapel() {
      return idPapel;
   }


   /**
    * Atribui o novo valor de idPapel
    * @param idPapel idPapel que será atribuído
    */
   public void setIdPapel(Long idPapel) {
      this.idPapel = idPapel;
   }

}
