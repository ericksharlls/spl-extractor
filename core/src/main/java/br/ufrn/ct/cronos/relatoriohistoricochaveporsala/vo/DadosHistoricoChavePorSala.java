package br.ufrn.ct.cronos.relatoriohistoricochaveporsala.vo;

import java.io.Serializable;


public class DadosHistoricoChavePorSala implements Serializable {

   private static final long serialVersionUID = 1L;

   private String sala = null;
   private String descricaoChave = null;
   private String horario = null;
   private String operacao = null;
   private String responsavel = null;
   private String usuarioSistema = null;
   private String horaEntrega = null;
   private String horaDevolucao = null;

   public DadosHistoricoChavePorSala() {
      super();
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
    * Recupera o valor do atributo descricaoChave
    * @return o descricaoChave
    */
   public String getDescricaoChave() {
      return descricaoChave;
   }


   /**
    * Atribui o novo valor de descricaoChave
    * @param descricaoChave descricaoChave que será atribuído
    */
   public void setDescricaoChave(String descricaoChave) {
      this.descricaoChave = descricaoChave;
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
    * Recupera o valor do atributo operacao
    * @return o operacao
    */
   public String getOperacao() {
      return operacao;
   }


   /**
    * Atribui o novo valor de operacao
    * @param operacao operacao que será atribuído
    */
   public void setOperacao(String operacao) {
      this.operacao = operacao;
   }


   /**
    * Recupera o valor do atributo responsavel
    * @return o responsavel
    */
   public String getResponsavel() {
      return responsavel;
   }


   /**
    * Atribui o novo valor de responsavel
    * @param responsavel responsavel que será atribuído
    */
   public void setResponsavel(String responsavel) {
      this.responsavel = responsavel;
   }

   /**
    * Recupera o valor do atributo usuarioSistema
    * @return o usuarioSistema
    */
   public String getUsuarioSistema() {
      return usuarioSistema;
   }

   /**
    * Atribui o novo valor de usuarioSistema
    * @param usuarioSistema usuarioSistema que será atribuído
    */
   public void setUsuarioSistema(String usuarioSistema) {
      this.usuarioSistema = usuarioSistema;
   }

   /**
    * Recupera o valor do atributo horaEntrega
    * @return o horaEntrega
    */
   public String getHoraEntrega() {
      return horaEntrega;
   }


   /**
    * Atribui o novo valor de horaEntrega
    * @param horaEntrega horaEntrega que será atribuído
    */
   public void setHoraEntrega(String horaEntrega) {
      this.horaEntrega = horaEntrega;
   }


   /**
    * Recupera o valor do atributo horaDevolucao
    * @return o horaDevolucao
    */
   public String getHoraDevolucao() {
      return horaDevolucao;
   }


   /**
    * Atribui o novo valor de horaDevolucao
    * @param horaDevolucao horaDevolucao que será atribuído
    */
   public void setHoraDevolucao(String horaDevolucao) {
      this.horaDevolucao = horaDevolucao;
   }

}
