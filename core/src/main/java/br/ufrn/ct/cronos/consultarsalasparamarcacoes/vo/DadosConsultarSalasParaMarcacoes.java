
package br.ufrn.ct.cronos.consultarsalasparamarcacoes.vo;

import java.io.Serializable;

public class DadosConsultarSalasParaMarcacoes implements Serializable {

   private static final long serialVersionUID = 1L;
   private Long id;
   private String nome;
   private String perfilSala;
   private String tipoQuadro;
   private Integer capacidade;

   public DadosConsultarSalasParaMarcacoes() {
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
    * Recupera o valor do atributo perfilSala
    * @return o perfilSala
    */
   public String getPerfilSala() {
      return perfilSala;
   }

   /**
    * Atribui o novo valor de perfilSala
    * @param perfilSala perfilSala que será atribuído
    */
   public void setPerfilSala(String perfilSala) {
      this.perfilSala = perfilSala;
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

}
