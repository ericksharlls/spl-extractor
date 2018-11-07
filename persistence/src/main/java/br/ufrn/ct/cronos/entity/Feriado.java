package br.ufrn.ct.cronos.entity;

import java.io.Serializable;
import java.util.Date;

public class Feriado implements Serializable {

   /** @TODO Comentar atributo */
   private static final long serialVersionUID = 1L;

   private Long id;
   private String descricao;
   private Date data;
   private Long idPeriodo;

   public Feriado() {
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
    * Recupera o valor do atributo data
    * @return o data
    */
   public Date getData() {
      return data;
   }

   /**
    * Atribui o novo valor de data
    * @param data data que será atribuído
    */
   public void setData(Date data) {
      this.data = data;
   }

   /**
    * Recupera o valor do atributo idPeriodo
    * @return o idPeriodo
    */
   public Long getIdPeriodo() {
      return idPeriodo;
   }

   /**
    * Atribui o novo valor de idPeriodo
    * @param idPeriodo idPeriodo que será atribuído
    */
   public void setIdPeriodo(Long idPeriodo) {
      this.idPeriodo = idPeriodo;
   }
}
