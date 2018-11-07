package br.ufrn.ct.cronos.entity;

import java.io.Serializable;

public class Turma implements Serializable {
   
   private static final long serialVersionUID = 1L;
      
   private Long id;
   private String horario;
   private String docente;
   private String nomeDisciplina;
   private String codigoDisciplina;
   private String local;
   private Integer capacidade;
   private String numero;
   private Integer alunosMatriculados;
   private Boolean distribuir;
   private Long idTipo;
   private Long idPredio;
   private Long idPeriodo;
   private Long idDepartamento;
   private Long idSalaTemp;
   private Long idTurmaSIGAA;
      
   public Turma() {
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
    * Recupera o valor do atributo local
    * @return o local
    */
   public String getLocal() {
      return local;
   }

   /**
    * Atribui o novo valor de local
    * @param local local que será atribuído
    */
   public void setLocal(String local) {
      this.local = local;
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
    * Recupera o valor do atributo distribuir
    * @return o distribuir
    */
   public Boolean getDistribuir() {
      return distribuir;
   }

   /**
    * Atribui o novo valor de distribuir
    * @param distribuir distribuir que será atribuído
    */
   public void setDistribuir(Boolean distribuir) {
      this.distribuir = distribuir;
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

   /**
    * Recupera o valor do atributo idPeriodo
    * @return o idPeriodo
    */
   public Long getIdPeriodo() {
      return idPeriodo;
   }

   /**
    * Atribui o novo valor de idPeriodo
    * @param idPeriodo idPeriodo que será atribuído
    */
   public void setIdPeriodo(Long idPeriodo) {
      this.idPeriodo = idPeriodo;
   }

   /**
    * Recupera o valor do atributo idDepartamento
    * @return o idDepartamento
    */
   public Long getIdDepartamento() {
      return idDepartamento;
   }

   /**
    * Atribui o novo valor de idDepartamento
    * @param idDepartamento idDepartamento que será atribuído
    */
   public void setIdDepartamento(Long idDepartamento) {
      this.idDepartamento = idDepartamento;
   }

   /**
    * Recupera o valor do atributo idSalaTemp
    * @return o idSalaTemp
    */
   public Long getIdSalaTemp() {
      return idSalaTemp;
   }

   /**
    * Atribui o novo valor de idSalaTemp
    * @param idSalaTemp idSalaTemp que será atribuído
    */
   public void setIdSalaTemp(Long idSalaTemp) {
      this.idSalaTemp = idSalaTemp;
   }

   /**
    * Recupera o valor do atributo idTurmaSIGAA
    * @return o idTurmaSIGAA
    */
   public Long getIdTurmaSIGAA() {
      return idTurmaSIGAA;
   }

   /**
    * Atribui o novo valor de idTurmaSIGAA
    * @param idTurmaSIGAA idTurmaSIGAA que será atribuído
    */
   public void setIdTurmaSIGAA(Long idTurmaSIGAA) {
      this.idTurmaSIGAA = idTurmaSIGAA;
   }
}
