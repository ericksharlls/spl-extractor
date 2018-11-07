
package br.ufrn.ct.cronos.consultarsalasparamarcacoes.vo;

import java.util.Date;
import java.util.List;
import dev.home.componente.service.entity.Request;

public class ConsultarSalasParaMarcacoes extends Request {

   private static final long serialVersionUID = 1L;
   private String horarioTurma;
   private String horarioTurmaOpcional;
   private Long idPredio, idPeriodo;
   private List<Date> datasParaAgendamento;
   private List<String> diasDaSemana;
   private Date dataAgendamentoInicio, dataAgendamentoTermino;

   public ConsultarSalasParaMarcacoes() {
      super();
   }

   /**
    * Recupera o valor do atributo horarioTurma
    * @return o horarioTurma
    */
   public String getHorarioTurma() {
      return horarioTurma;
   }

   /**
    * Atribui o novo valor de horarioTurma
    * @param horarioTurma horarioTurma que será atribuído
    */
   public void setHorarioTurma(String horarioTurma) {
      this.horarioTurma = horarioTurma;
   }

   /**
    * Recupera o valor do atributo horarioTurmaOpcional
    * @return o horarioTurmaOpcional
    */
   public String getHorarioTurmaOpcional() {
      return horarioTurmaOpcional;
   }

   /**
    * Atribui o novo valor de horarioTurmaOpcional
    * @param horarioTurmaOpcional horarioTurmaOpcional que será atribuído
    */
   public void setHorarioTurmaOpcional(String horarioTurmaOpcional) {
      this.horarioTurmaOpcional = horarioTurmaOpcional;
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
    * Recupera o valor do atributo datasParaAgendamento
    * @return o datasParaAgendamento
    */
   public List<Date> getDatasParaAgendamento() {
      return datasParaAgendamento;
   }

   /**
    * Atribui o novo valor de datasParaAgendamento
    * @param datasParaAgendamento datasParaAgendamento que será atribuído
    */
   public void setDatasParaAgendamento(List<Date> datasParaAgendamento) {
      this.datasParaAgendamento = datasParaAgendamento;
   }

   /**
    * Recupera o valor do atributo diasDaSemana
    * @return o diasDaSemana
    */
   public List<String> getDiasDaSemana() {
      return diasDaSemana;
   }

   /**
    * Atribui o novo valor de diasDaSemana
    * @param diasDaSemana diasDaSemana que será atribuído
    */
   public void setDiasDaSemana(List<String> diasDaSemana) {
      this.diasDaSemana = diasDaSemana;
   }

   /**
    * Recupera o valor do atributo dataAgendamentoInicio
    * @return o dataAgendamentoInicio
    */
   public Date getDataAgendamentoInicio() {
      return dataAgendamentoInicio;
   }

   /**
    * Atribui o novo valor de dataAgendamentoInicio
    * @param dataAgendamentoInicio dataAgendamentoInicio que será atribuído
    */
   public void setDataAgendamentoInicio(Date dataAgendamentoInicio) {
      this.dataAgendamentoInicio = dataAgendamentoInicio;
   }

   /**
    * Recupera o valor do atributo dataAgendamentoTermino
    * @return o dataAgendamentoTermino
    */
   public Date getDataAgendamentoTermino() {
      return dataAgendamentoTermino;
   }

   /**
    * Atribui o novo valor de dataAgendamentoTermino
    * @param dataAgendamentoTermino dataAgendamentoTermino que será atribuído
    */
   public void setDataAgendamentoTermino(Date dataAgendamentoTermino) {
      this.dataAgendamentoTermino = dataAgendamentoTermino;
   }

}
