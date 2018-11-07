package br.ufrn.ct.cronos.entity;

import java.io.Serializable;

public class Sala implements Serializable {
   
      private static final long serialVersionUID = 1L;
      
      private Long id;
   private String nome, descricao, tipoQuadro;
      private Integer capacidade;
      private Boolean utilizarNaDistribuicao, utilizarNoAgendamento;
      private Long idTipo;
      private Long idPredio;
 
      public Sala(){
         super();
      }

   public Sala(Long id, String nome, String descricao, String tipoQuadro, Integer capacidade, Boolean utilizarNaDistribuicao,
         Boolean utilizarNoAgendamento, Long idTipo, Long idPredio) {
         super();
         this.id = id;
         this.nome = nome;
         this.descricao = descricao;
      this.tipoQuadro = tipoQuadro;
         this.capacidade = capacidade;
         this.utilizarNaDistribuicao = utilizarNaDistribuicao;
         this.utilizarNoAgendamento = utilizarNoAgendamento;
         this.idTipo = idTipo;
         this.idPredio = idPredio;
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

   /**
    * Recupera o valor do atributo tipoQuadro
    * @return o tipoQuadro
    */
   public String getTipoQuadro() {
      return tipoQuadro;
   }

   /**
    * Atribui o novo valor de tipoQuadro
    * @param tipoQuadro tipoQuadro que será atribuído
    */
   public void setTipoQuadro(String tipoQuadro) {
      this.tipoQuadro = tipoQuadro;
   }
      
      /**
    * Recupera o valor do atributo capacidade
    * @return o capacidade
    */
      public Integer getCapacidade() {
         return capacidade;
      }

      
   /**
    * Atribui o novo valor de capacidade
    * @param capacidade capacidade que será atribuído
    */
      public void setCapacidade(Integer capacidade) {
         this.capacidade = capacidade;
      }

      
   /**
    * Recupera o valor do atributo utilizarNaDistribuicao
    * @return o utilizarNaDistribuicao
    */
      public Boolean getUtilizarNaDistribuicao() {
         return utilizarNaDistribuicao;
      }

      
   /**
    * Atribui o novo valor de utilizarNaDistribuicao
    * @param utilizarNaDistribuicao utilizarNaDistribuicao que será atribuído
    */
      public void setUtilizarNaDistribuicao(Boolean utilizarNaDistribuicao) {
         this.utilizarNaDistribuicao = utilizarNaDistribuicao;
      }

      
   /**
    * Recupera o valor do atributo utilizarNoAgendamento
    * @return o utilizarNoAgendamento
    */
      public Boolean getUtilizarNoAgendamento() {
         return utilizarNoAgendamento;
      }

      
   /**
    * Atribui o novo valor de utilizarNoAgendamento
    * @param utilizarNoAgendamento utilizarNoAgendamento que será atribuído
    */
      public void setUtilizarNoAgendamento(Boolean utilizarNoAgendamento) {
         this.utilizarNoAgendamento = utilizarNoAgendamento;
      }

      
   /**
    * Recupera o valor do atributo idTipo
    * @return o idTipo
    */
      public Long getIdTipo() {
         return idTipo;
      }

      
   /**
    * Atribui o novo valor de idTipo
    * @param idTipo idTipo que será atribuído
    */
      public void setIdTipo(Long idTipo) {
         this.idTipo = idTipo;
      }

      
   /**
    * Recupera o valor do atributo idPredio
    * @return o idPredio
    */
      public Long getIdPredio() {
         return idPredio;
      }

   /**
    * Atribui o novo valor de idPredio
    * @param idPredio idPredio que será atribuído
    */
      public void setIdPredio(Long idPredio) {
         this.idPredio = idPredio;
      }
}
