
package br.ufrn.ct.cronos.listarturmasdistribuidas.vo;

import java.io.Serializable;

public class DadosListarTurmasDistribuidas implements Serializable {

   private static final long serialVersionUID = 1L;
   private Long id;
   private String nome;
   private Integer numero;

   public DadosListarTurmasDistribuidas() {
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
    * Recupera o valor do atributo numero
    * @return o numero
    */
   public Integer getNumero() {
      return numero;
   }

   /**
    * Atribui o novo valor de numero
    * @param numero numero que será atribuído
    */
   public void setNumero(Integer numero) {
      this.numero = numero;
   }

}
