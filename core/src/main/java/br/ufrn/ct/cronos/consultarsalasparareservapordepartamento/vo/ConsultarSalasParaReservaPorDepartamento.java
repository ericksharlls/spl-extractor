package br.ufrn.ct.cronos.consultarsalasparareservapordepartamento.vo;

import dev.home.componente.service.entity.Request;

public class ConsultarSalasParaReservaPorDepartamento extends Request {

   /** @TODO Comentar atributo */
   private static final long serialVersionUID = 1L;

   private String horarioUm;
   private String horarioDois;
   private String horarioTres;
   private String nomeUsuario;
   private Long idPredio, idPeriodo;

   public ConsultarSalasParaReservaPorDepartamento() {
      super();
   }


   /**
    * Recupera o valor do atributo horarioUm
    * @return o horarioUm
    */
   public String getHorarioUm() {
      return horarioUm;
   }


   /**
    * Atribui o novo valor de horarioUm
    * @param horarioUm horarioUm que será atribuído
    */
   public void setHorarioUm(String horarioUm) {
      this.horarioUm = horarioUm;
   }


   /**
    * Recupera o valor do atributo horarioDois
    * @return o horarioDois
    */
   public String getHorarioDois() {
      return horarioDois;
   }


   /**
    * Atribui o novo valor de horarioDois
    * @param horarioDois horarioDois que será atribuído
    */
   public void setHorarioDois(String horarioDois) {
      this.horarioDois = horarioDois;
   }


   /**
    * Recupera o valor do atributo horarioTres
    * @return o horarioTres
    */
   public String getHorarioTres() {
      return horarioTres;
   }


   /**
    * Atribui o novo valor de horarioTres
    * @param horarioTres horarioTres que será atribuído
    */
   public void setHorarioTres(String horarioTres) {
      this.horarioTres = horarioTres;
   }


   /**
    * Recupera o valor do atributo nomeUsuario
    * @return o nomeUsuario
    */
   public String getNomeUsuario() {
      return nomeUsuario;
   }

   /**
    * Atribui o novo valor de nomeUsuario
    * @param nomeUsuario nomeUsuario que será atribuído
    */
   public void setNomeUsuario(String nomeUsuario) {
      this.nomeUsuario = nomeUsuario;
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
}
