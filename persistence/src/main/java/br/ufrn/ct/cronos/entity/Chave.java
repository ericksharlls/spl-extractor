package br.ufrn.ct.cronos.entity;

import java.io.Serializable;

public class Chave implements Serializable {

   private static final long serialVersionUID = 1L;
   private Long id;
   private String codigo;
   private String descricao;
   private Long idSala;

   public Chave() {
      super();
   }

   /**
    * Recupera o valor do atributo id
    * @return o id
    */
   public Long getId() {
      return id;
   }

   /**
    * Atribui o novo valor de id
    * @param id id que será atribuído
    */
   public void setId(Long id) {
      this.id = id;
   }

   /**
    * Recupera o valor do atributo codigo
    * @return o codigo
    */
   public String getCodigo() {
      return codigo;
   }

   /**
    * Atribui o novo valor de codigo
    * @param codigo codigo que será atribuído
    */
   public void setCodigo(String codigo) {
      this.codigo = codigo;
   }

   /**
    * Recupera o valor do atributo descricao
    * @return o descricao
    */
   public String getDescricao() {
      return descricao;
   }

   /**
    * Atribui o novo valor de descricao
    * @param descricao descricao que será atribuído
    */
   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   /**
    * Recupera o valor do atributo idSala
    * @return o idSala
    */
   public Long getIdSala() {
      return idSala;
   }

   /**
    * Atribui o novo valor de idSala
    * @param idSala idSala que será atribuído
    */
   public void setIdSala(Long idSala) {
      this.idSala = idSala;
   }
}
