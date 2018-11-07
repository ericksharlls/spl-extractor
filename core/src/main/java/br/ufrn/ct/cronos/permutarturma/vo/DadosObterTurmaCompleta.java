
package br.ufrn.ct.cronos.permutarturma.vo;

import java.io.Serializable;

public class DadosObterTurmaCompleta implements Serializable {

   private static final long serialVersionUID = 1L;
   private Long id;
   private String horario;
   private String docente;
   private String nomeDisciplina;
   private String codigoDisciplina;
   private String perfilTurma;
   private String sala;
   private String capacidadesDasSalas;
   private Integer capacidadeSala;
   private Integer alunosMatriculados;
   private String numero;

   public DadosObterTurmaCompleta() {
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
    * Recupera o valor do atributo perfilTurma
    * @return o perfilTurma
    */
   public String getPerfilTurma() {
      return perfilTurma;
   }

   /**
    * Atribui o novo valor de perfilTurma
    * @param perfilTurma perfilTurma que será atribuído
    */
   public void setPerfilTurma(String perfilTurma) {
      this.perfilTurma = perfilTurma;
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
    * Recupera o valor do atributo capacidadesDasSalas
    * @return o capacidadesDasSalas
    */
   public String getCapacidadesDasSalas() {
      return capacidadesDasSalas;
   }

   /**
    * Atribui o novo valor de capacidadesDasSalas
    * @param capacidadesDasSalas capacidadesDasSalas que será atribuído
    */
   public void setCapacidadesDasSalas(String capacidadesDasSalas) {
      this.capacidadesDasSalas = capacidadesDasSalas;
   }

   /**
    * Recupera o valor do atributo capacidadeSala
    * @return o capacidadeSala
    */
   public Integer getCapacidadeSala() {
      return capacidadeSala;
   }

   /**
    * Atribui o novo valor de capacidadeSala
    * @param capacidadeSala capacidadeSala que será atribuído
    */
   public void setCapacidadeSala(Integer capacidadeSala) {
      this.capacidadeSala = capacidadeSala;
   }

   /**
    * Recupera o valor do atributo alunosMatriculados
    * @return o alunosMatriculados
    */
   public Integer getAlunosMatriculados() {
      return alunosMatriculados;
   }

   /**
    * Atribui o novo valor de alunosMatriculados
    * @param alunosMatriculados alunosMatriculados que será atribuído
    */
   public void setAlunosMatriculados(Integer alunosMatriculados) {
      this.alunosMatriculados = alunosMatriculados;
   }

   /**
    * Recupera o valor do atributo numero
    * @return o numero
    */
   public String getNumero() {
      return numero;
   }

   /**
    * Atribui o novo valor de numero
    * @param numero numero que será atribuído
    */
   public void setNumero(String numero) {
      this.numero = numero;
   }
}
