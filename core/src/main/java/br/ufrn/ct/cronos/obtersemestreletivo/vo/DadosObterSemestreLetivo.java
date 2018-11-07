
package br.ufrn.ct.cronos.obtersemestreletivo.vo;

import java.io.Serializable;
import java.util.Date;

public class DadosObterSemestreLetivo implements Serializable {

   private static final long serialVersionUID = 1L;
   private Long id;
   private String nome;
   private String descricao;
   private Boolean isSemestreLetivo;
   private Date dataInicio;
   private Date dataTermino;
   private Integer ano, numeroPeriodo;

   public DadosObterSemestreLetivo() {
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
    * Recupera o valor do atributo isSemestreLetivo
    * @return o isSemestreLetivo
    */
   public Boolean getIsSemestreLetivo() {
      return isSemestreLetivo;
   }


   /**
    * Atribui o novo valor de isSemestreLetivo
    * @param isSemestreLetivo isSemestreLetivo que será atribuído
    */
   public void setIsSemestreLetivo(Boolean isSemestreLetivo) {
      this.isSemestreLetivo = isSemestreLetivo;
   }


   /**
    * Recupera o valor do atributo dataInicio
    * @return o dataInicio
    */
   public Date getDataInicio() {
      return dataInicio;
   }


   /**
    * Atribui o novo valor de dataInicio
    * @param dataInicio dataInicio que será atribuído
    */
   public void setDataInicio(Date dataInicio) {
      this.dataInicio = dataInicio;
   }


   /**
    * Recupera o valor do atributo dataTermino
    * @return o dataTermino
    */
   public Date getDataTermino() {
      return dataTermino;
   }


   /**
    * Atribui o novo valor de dataTermino
    * @param dataTermino dataTermino que será atribuído
    */
   public void setDataTermino(Date dataTermino) {
      this.dataTermino = dataTermino;
   }

   /**
    * Recupera o valor do atributo ano
    * @return o ano
    */
   public Integer getAno() {
      return ano;
   }

   /**
    * Atribui o novo valor de ano
    * @param ano ano que será atribuído
    */
   public void setAno(Integer ano) {
      this.ano = ano;
   }

   /**
    * Recupera o valor do atributo numeroPeriodo
    * @return o numeroPeriodo
    */
   public Integer getNumeroPeriodo() {
      return numeroPeriodo;
   }

   /**
    * Atribui o novo valor de numeroPeriodo
    * @param numeroPeriodo numeroPeriodo que será atribuído
    */
   public void setNumeroPeriodo(Integer numeroPeriodo) {
      this.numeroPeriodo = numeroPeriodo;
   }
}
