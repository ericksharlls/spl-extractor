
package br.ufrn.ct.cronos.entity;

import java.io.Serializable;

public class PerfilSalaTurma implements Serializable {

   private static final long serialVersionUID = 1L;
   private Long id;
   private String nome;
   private String descricao;

   public PerfilSalaTurma() {
      super();
   }

   public PerfilSalaTurma(Long id, String nome, String descricao) {
      super();
      this.id = id;
      this.nome = nome;
      this.descricao = descricao;
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
    * Recupera o valor do atributo nome
    * @return o nome
    */
   public String getNome() {
      return nome;
   }

   /**
    * Atribui o novo valor de nome
    * @param nome nome que será atribuído
    */
   public void setNome(String nome) {
      this.nome = nome;
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

}
