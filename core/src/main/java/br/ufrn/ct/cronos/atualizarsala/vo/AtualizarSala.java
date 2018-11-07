package br.ufrn.ct.cronos.atualizarsala.vo;

import dev.home.componente.service.entity.Request;

public class AtualizarSala extends Request {
      
      private static final long serialVersionUID = 1L;
      
      private Long id;
      private String nome, descricao, tipoQuadro;
      private int capacidade;
      private Boolean utilizarNaDistribuicao, utilizarNoAgendamento;
      private Long idTipoSala;
      private Long idPredio;
      
      public AtualizarSala(){
         super();
      }

      public AtualizarSala(Long id, String nome, String descricao, String tipoQuadro, int capacidade, Boolean utilizarNaDistribuicao,
         Boolean utilizarNoAgendamento, Long idTipoSala, Long idPredio) {
         super();
         this.id = id;
         this.nome = nome;
         this.descricao = descricao;
         this.tipoQuadro = tipoQuadro;
         this.capacidade = capacidade;
         this.utilizarNaDistribuicao = utilizarNaDistribuicao;
         this.utilizarNoAgendamento = utilizarNoAgendamento;
         this.idTipoSala = idTipoSala;
         this.idPredio = idPredio;
      }

      
      public Long getId() {
         return id;
      }
      
      public void setId(Long id) {
         this.id = id;
      }
      
      public String getNome() {
         return nome;
      }
      
      public void setNome(String nome) {
         this.nome = nome;
      }
      
      public String getDescricao() {
         return descricao;
      }
      
      public void setDescricao(String descricao) {
         this.descricao = descricao;
      }
      
      public String getTipoQuadro() {
         return tipoQuadro;
      }
      
      public void setTipoQuadro(String tipoQuadro) {
         this.tipoQuadro = tipoQuadro;
      }
      
      public int getCapacidade() {
         return capacidade;
      }
      
      public void setCapacidade(int capacidade) {
         this.capacidade = capacidade;
      }
      
      public Boolean getUtilizarNaDistribuicao() {
         return utilizarNaDistribuicao;
      }
      
      public void setUtilizarNaDistribuicao(Boolean utilizarNaDistribuicao) {
         this.utilizarNaDistribuicao = utilizarNaDistribuicao;
      }
      
      public Boolean getUtilizarNoAgendamento() {
         return utilizarNoAgendamento;
      }
      
      public void setUtilizarNoAgendamento(Boolean utilizarNoAgendamento) {
         this.utilizarNoAgendamento = utilizarNoAgendamento;
      }
      
      public Long getIdTipoSala() {
         return idTipoSala;
      }
      
      public void setIdTipoSala(Long idTipoSala) {
         this.idTipoSala = idTipoSala;
      }
      
      public Long getIdPredio() {
         return idPredio;
      }

      public void setIdPredio(Long idPredio) {
         this.idPredio = idPredio;
      }

}
