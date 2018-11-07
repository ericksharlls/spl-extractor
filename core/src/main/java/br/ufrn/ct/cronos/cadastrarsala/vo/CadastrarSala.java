
package br.ufrn.ct.cronos.cadastrarsala.vo;

import dev.home.componente.service.entity.Request;

public class CadastrarSala extends Request {

   private static final long serialVersionUID = 1L;
   private String nome;
   private String descricao;
   private String tipoQuadro;
   private int capacidade;
   private Boolean conexaoInternet, utilizarNaDistribuicao, utilizarNoAgendamento;
   private Long idTipoSala;
   private Long idPredio;

   public CadastrarSala() {
      super();
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
   public int getCapacidade() {
      return capacidade;
   }

   /**
    * Atribui o novo valor de capacidade
    * @param capacidade capacidade que será atribuído
    */
   public void setCapacidade(int capacidade) {
      this.capacidade = capacidade;
   }

   /**
    * Recupera o valor do atributo conexaoInternet
    * @return o conexaoInternet
    */
   public Boolean getConexaoInternet() {
      return conexaoInternet;
   }

   /**
    * Atribui o novo valor de conexaoInternet
    * @param conexaoInternet conexaoInternet que será atribuído
    */
   public void setConexaoInternet(Boolean conexaoInternet) {
      this.conexaoInternet = conexaoInternet;
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
    * Recupera o valor do atributo idTipoSala
    * @return o idTipoSala
    */
   public Long getIdTipoSala() {
      return idTipoSala;
   }

   /**
    * Atribui o novo valor de idTipoSala
    * @param idTipoSala idTipoSala que será atribuído
    */
   public void setIdTipoSala(Long idTipoSala) {
      this.idTipoSala = idTipoSala;
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
