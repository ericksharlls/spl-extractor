package br.ufrn.ct.cronos.consultarsala.vo;

import java.io.Serializable;

public class DadosConsultarSala implements Serializable {
      
      private static final long serialVersionUID = 1L;
      private Long id;
      private String nome, tipoQuadro, perfil, predio;
      private int capacidade;
      private Boolean utilizarNaDistribuicao, utilizarNoAgendamento;
 
      public DadosConsultarSala(){
         super();
      }

      public DadosConsultarSala(Long id, String nome, String tipoQuadro, String perfil, String predio, int capacidade,
         Boolean utilizarNaDistribuicao, Boolean utilizarNoAgendamento) {
         super();
         this.id = id;
         this.nome = nome;
         this.tipoQuadro = tipoQuadro;
         this.perfil = perfil;
         this.predio = predio;
         this.capacidade = capacidade;
         this.utilizarNaDistribuicao = utilizarNaDistribuicao;
         this.utilizarNoAgendamento = utilizarNoAgendamento;
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
      
      public String getTipoQuadro() {
         return tipoQuadro;
      }

      public void setTipoQuadro(String tipoQuadro) {
         this.tipoQuadro = tipoQuadro;
      }
      
      public String getPerfil() {
         return perfil;
      }
      
      public void setPerfil(String perfil) {
         this.perfil = perfil;
      }
      
      public String getPredio() {
         return predio;
      }
      
      public void setPredio(String predio) {
         this.predio = predio;
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
      
}
