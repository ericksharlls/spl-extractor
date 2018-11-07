
package br.ufrn.ct.cronos.consultarturmaporcodigoedocente.vo;

import java.io.Serializable;

public class DadosConsultarTurmaPorCodigoEDocente implements Serializable {

   private static final long serialVersionUID = 1L;

   private Long id;
   private String docente;
   private String nomeDisciplina;
   private String codigoDisciplina;
   private String perfil;
   private String horario;
   private String sala;

   public DadosConsultarTurmaPorCodigoEDocente() {
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
    * Recupera o valor do atributo docente
    * @return o docente
    */
   public String getDocente() {
      return docente;
   }

   /**
    * Atribui o novo valor de docente
    * @param docente docente que será atribuído
    */
   public void setDocente(String docente) {
      this.docente = docente;
   }

   /**
    * Recupera o valor do atributo nomeDisciplina
    * @return o nomeDisciplina
    */
   public String getNomeDisciplina() {
      return nomeDisciplina;
   }

   /**
    * Atribui o novo valor de nomeDisciplina
    * @param nomeDisciplina nomeDisciplina que será atribuído
    */
   public void setNomeDisciplina(String nomeDisciplina) {
      this.nomeDisciplina = nomeDisciplina;
   }

   /**
    * Recupera o valor do atributo codigoDisciplina
    * @return o codigoDisciplina
    */
   public String getCodigoDisciplina() {
      return codigoDisciplina;
   }

   /**
    * Atribui o novo valor de codigoDisciplina
    * @param codigoDisciplina codigoDisciplina que será atribuído
    */
   public void setCodigoDisciplina(String codigoDisciplina) {
      this.codigoDisciplina = codigoDisciplina;
   }

   /**
    * Recupera o valor do atributo perfil
    * @return o perfil
    */
   public String getPerfil() {
      return perfil;
   }

   /**
    * Atribui o novo valor de perfil
    * @param perfil perfil que será atribuído
    */
   public void setPerfil(String perfil) {
      this.perfil = perfil;
   }

   /**
    * Recupera o valor do atributo horario
    * @return o horario
    */
   public String getHorario() {
      return horario;
   }

   /**
    * Atribui o novo valor de horario
    * @param horario horario que será atribuído
    */
   public void setHorario(String horario) {
      this.horario = horario;
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

}
