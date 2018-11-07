
package br.ufrn.ct.cronos.distribuirturmas.vo;

import java.io.Serializable;
import java.util.List;

public class DadosSala implements Serializable {

   private static final long serialVersionUID = 1L;
   private String semestre = null;
   private String sala = null;

   private List<DadosTurma> distribuicoes = null;

   public DadosSala() {
      super();
   }

   /**
    * Recupera o valor do atributo semestre
    * @return o semestre
    */
   public String getSemestre() {
      return semestre;
   }

   /**
    * Atribui o novo valor de semestre
    * @param semestre semestre que será atribuído
    */
   public void setSemestre(String semestre) {
      this.semestre = semestre;
   }

   /**
    * Recupera o valor do atributo sala
    * @return o sala
    */
   public String getSala() {
      return sala;
   }

   /**
    * Atribui o novo valor de sala
    * @param sala sala que será atribuído
    */
   public void setSala(String sala) {
      this.sala = sala;
   }

   /**
    * Recupera o valor do atributo distribuicoes
    * @return o distribuicoes
    */
   public List<DadosTurma> getDistribuicoes() {
      return distribuicoes;
   }

   /**
    * Atribui o novo valor de distribuicoes
    * @param distribuicoes distribuicoes que será atribuído
    */
   public void setDistribuicoes(List<DadosTurma> distribuicoes) {
      this.distribuicoes = distribuicoes;
   }
}
