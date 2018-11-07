
package br.ufrn.ct.cronos.cadastrarperiodo.vo;

import java.util.Date;
import dev.home.componente.service.entity.Request;

public class CadastrarPeriodo extends Request {

   private static final long serialVersionUID = 1L;
   private String nome;
   private String descricao;
   private Date dataInicio;
   private Date dataTermino;
   private Integer ano, numeroPeriodo;
   private Boolean periodoLetivo;

   public CadastrarPeriodo() {
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

   /**
    * Recupera o valor do atributo periodoLetivo
    * @return o periodoLetivo
    */
   public Boolean getPeriodoLetivo() {
      return periodoLetivo;
   }

   /**
    * Atribui o novo valor de periodoLetivo
    * @param periodoLetivo periodoLetivo que será atribuído
    */
   public void setPeriodoLetivo(Boolean periodoLetivo) {
      this.periodoLetivo = periodoLetivo;
   }

}
