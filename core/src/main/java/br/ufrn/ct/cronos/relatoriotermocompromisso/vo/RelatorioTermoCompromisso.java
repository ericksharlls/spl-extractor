package br.ufrn.ct.cronos.relatoriotermocompromisso.vo;

import java.util.Date;
import java.util.List;
import dev.home.componente.service.entity.Request;

public class RelatorioTermoCompromisso extends Request {

   private static final long serialVersionUID = 1L;

   private Long idSala, idPeriodo, idInteressado;
   private String horario;
   private String horarioOpcional;
   private List<Date> datasParaAgendamento;
   private String motivo;
   private Date dataAgendamentoInicio, dataAgendamentoTermino;
   private List<String> diasDaSemana;

   public RelatorioTermoCompromisso() {
      super();
   }

   /**
    * Recupera o valor do atributo idSala
    * @return o idSala
    */
   public Long getIdSala() {
      return idSala;
   }

   /**
    * Atribui o novo valor de idSala
    * @param idSala idSala que será atribuído
    */
   public void setIdSala(Long idSala) {
      this.idSala = idSala;
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
    * Recupera o valor do atributo idInteressado
    * @return o idInteressado
    */
   public Long getIdInteressado() {
      return idInteressado;
   }

   /**
    * Atribui o novo valor de idInteressado
    * @param idInteressado idInteressado que será atribuído
    */
   public void setIdInteressado(Long idInteressado) {
      this.idInteressado = idInteressado;
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
    * Recupera o valor do atributo horarioOpcional
    * @return o horarioOpcional
    */
   public String getHorarioOpcional() {
      return horarioOpcional;
   }

   /**
    * Atribui o novo valor de horarioOpcional
    * @param horarioOpcional horarioOpcional que será atribuído
    */
   public void setHorarioOpcional(String horarioOpcional) {
      this.horarioOpcional = horarioOpcional;
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
    * Recupera o valor do atributo motivo
    * @return o motivo
    */
   public String getMotivo() {
      return motivo;
   }

   /**
    * Atribui o novo valor de motivo
    * @param motivo motivo que será atribuído
    */
   public void setMotivo(String motivo) {
      this.motivo = motivo;
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
}
