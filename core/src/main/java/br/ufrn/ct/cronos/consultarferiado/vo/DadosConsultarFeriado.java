package br.ufrn.ct.cronos.consultarferiado.vo;

import java.io.Serializable;
import java.util.Date;

public class DadosConsultarFeriado implements Serializable {

   private static final long serialVersionUID = 1L;
   private Long id;
   private String descricao, periodo;
   private Date data;

   public DadosConsultarFeriado() {
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
    * Recupera o valor do atributo periodo
    * @return o periodo
    */
   public String getPeriodo() {
      return periodo;
   }

   /**
    * Atribui o novo valor de periodo
    * @param periodo periodo que será atribuído
    */
   public void setPeriodo(String periodo) {
      this.periodo = periodo;
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
}
