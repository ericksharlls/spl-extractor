
package br.ufrn.ct.cronos.registraroperacaochave.vo;

import java.util.Date;
import dev.home.componente.service.entity.Request;

public class RegistrarOperacaoChave extends Request {

   private static final long serialVersionUID = 1L;
   private Date horaRealizacao;
   private Long idOperacao;
   private boolean temResponsavel;
   private String codigoChave;
   private String loginUsuario;
   private Long identificadorResponsavel;

   public RegistrarOperacaoChave() {
      super();
   }

   /**
    * Recupera o valor do atributo horaRealizacao
    * @return o horaRealizacao
    */
   public Date getHoraRealizacao() {
      return horaRealizacao;
   }

   /**
    * Atribui o novo valor de horaRealizacao
    * @param horaRealizacao horaRealizacao que será atribuído
    */
   public void setHoraRealizacao(Date horaRealizacao) {
      this.horaRealizacao = horaRealizacao;
   }

   /**
    * Recupera o valor do atributo idOperacao
    * @return o idOperacao
    */
   public Long getIdOperacao() {
      return idOperacao;
   }

   /**
    * Atribui o novo valor de idOperacao
    * @param idOperacao idOperacao que será atribuído
    */
   public void setIdOperacao(Long idOperacao) {
      this.idOperacao = idOperacao;
   }

   /**
    * Recupera o valor do atributo temResponsavel
    * @return o temResponsavel
    */
   public boolean isTemResponsavel() {
      return temResponsavel;
   }

   /**
    * Atribui o novo valor de temResponsavel
    * @param temResponsavel temResponsavel que será atribuído
    */
   public void setTemResponsavel(boolean temResponsavel) {
      this.temResponsavel = temResponsavel;
   }

   /**
    * Recupera o valor do atributo codigoChave
    * @return o codigoChave
    */
   public String getCodigoChave() {
      return codigoChave;
   }

   /**
    * Atribui o novo valor de codigoChave
    * @param codigoChave codigoChave que será atribuído
    */
   public void setCodigoChave(String codigoChave) {
      this.codigoChave = codigoChave;
   }

   /**
    * Recupera o valor do atributo loginUsuario
    * @return o loginUsuario
    */
   public String getLoginUsuario() {
      return loginUsuario;
   }

   /**
    * Atribui o novo valor de loginUsuario
    * @param loginUsuario loginUsuario que será atribuído
    */
   public void setLoginUsuario(String loginUsuario) {
      this.loginUsuario = loginUsuario;
   }

   /**
    * Recupera o valor do atributo identificadorResponsavel
    * @return o identificadorResponsavel
    */
   public Long getIdentificadorResponsavel() {
      return identificadorResponsavel;
   }

   /**
    * Atribui o novo valor de identificadorResponsavel
    * @param identificadorResponsavel identificadorResponsavel que será atribuído
    */
   public void setIdentificadorResponsavel(Long identificadorResponsavel) {
      this.identificadorResponsavel = identificadorResponsavel;
   }
}
