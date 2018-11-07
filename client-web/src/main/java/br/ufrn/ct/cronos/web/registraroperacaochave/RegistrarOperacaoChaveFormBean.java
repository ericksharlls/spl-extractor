package br.ufrn.ct.cronos.web.registraroperacaochave;
 
import java.util.Date;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import br.ufrn.ct.cronos.registraroperacaochave.vo.RegistrarOperacaoChave;
import br.ufrn.ct.cronos.service.registraroperacaochave.ServiceRegistrarOperacaoChave;
import br.ufrn.ct.cronos.web.PropertiesLoader;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.factory.BeanFactory;
import dev.home.componente.web.infra.AbstractFormBean;

public class RegistrarOperacaoChaveFormBean extends AbstractFormBean {
	
   private static final long serialVersionUID = 1L;
   private Boolean temResponsavel;
   private String codigoChaveEntrada, codigoChaveSaida;
   private Long identificadorResponsavel;
   private ServiceRegistrarOperacaoChave operacao;
		
   public RegistrarOperacaoChaveFormBean() {
      super();
      this.setTemResponsavel(Boolean.parseBoolean(PropertiesLoader.propertiesLoader().getProperty("EntradaSaidaChaveTemResponsavel")));
      if (this.getTemResponsavel()) {
         this.operacao = (ServiceRegistrarOperacaoChave) BeanFactory.getInstance().getBean("RegistrarOperacaoChaveComResponsavel");
      } else {
         this.operacao = (ServiceRegistrarOperacaoChave) BeanFactory.getInstance().getBean("RegistrarOperacaoChaveSemResponsavel");
      }
   }

   public String registrarOperacao() throws NegocioException {
      RegistrarOperacaoChave solicitacao = new RegistrarOperacaoChave();
      popularVo(solicitacao);
      this.operacao.valida(solicitacao);
      this.operacao.processa(solicitacao);
      addInfoMessage("Opera\u00E7\u00E3o realizada com sucesso.");
      return null;
   }
		
   private void popularVo(RegistrarOperacaoChave solicitacao) {
      if (getCodigoChaveEntrada().equals("")) {
         solicitacao.setCodigoChave(this.getCodigoChaveSaida());
         solicitacao.setTemResponsavel(this.getTemResponsavel());
         solicitacao.setHoraRealizacao(new Date());
         solicitacao.setIdOperacao(new Long(2));
         solicitacao.setIdentificadorResponsavel(this.identificadorResponsavel);
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         solicitacao.setLoginUsuario(authentication.getName());
		}
      if (getCodigoChaveSaida().equals("")) {
         solicitacao.setCodigoChave(this.getCodigoChaveEntrada());
         solicitacao.setTemResponsavel(this.getTemResponsavel());
         solicitacao.setHoraRealizacao(new Date());
         solicitacao.setIdOperacao(new Long(1));
         solicitacao.setIdentificadorResponsavel(this.identificadorResponsavel);
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         solicitacao.setLoginUsuario(authentication.getName());
		}
   }

   public void setCodigoChaveEntrada(String codigoChaveEntrada) {
      this.codigoChaveEntrada = codigoChaveEntrada;
   }

   public String getCodigoChaveEntrada() {
      return this.codigoChaveEntrada;
   }

   public void setCodigoChaveSaida(String codigoChaveSaida) {
      this.codigoChaveSaida = codigoChaveSaida;
   }

   public String getCodigoChaveSaida() {
      return this.codigoChaveSaida;
   }

   public Long getIdentificadorResponsavel() {
      return identificadorResponsavel;
   }

   public void setIdentificadorResponsavel(Long identificadorResponsavel) {
      this.identificadorResponsavel = identificadorResponsavel;
   }

   public Boolean getTemResponsavel() {
      return temResponsavel;
   }

   public void setTemResponsavel(Boolean temResponsavel) {
      this.temResponsavel = temResponsavel;
   }

}

