
package br.ufrn.ct.cronos.atualizarturma.vo;

import java.util.List;
import dev.home.componente.service.entity.Request;

public class AtualizarTurma extends Request {

   private static final long serialVersionUID = 1L;
   private Long id;
   private String nomeDisciplina;
   private String codigoDisciplina;
   private String local;
   private Long idTipo, idPredio;
   private Boolean distribuir;
   private String horario;
   private String turma;
   private Integer capacidade;
   private List<DadosDocente> docentes;

   public AtualizarTurma() {
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
    * Recupera o valor do atributo turma
    * @return o turma
    */
   public String getTurma() {
      return turma;
   }

   /**
    * Atribui o novo valor de turma
    * @param turma turma que será atribuído
    */
   public void setTurma(String turma) {
      this.turma = turma;
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
    * Recupera o valor do atributo docentes
    * @return o docentes
    */
   public List<DadosDocente> getDocentes() {
      return docentes;
   }

   /**
    * Atribui o novo valor de docentes
    * @param docentes docentes que será atribuído
    */
   public void setDocentes(List<DadosDocente> docentes) {
      this.docentes = docentes;
   }
}
