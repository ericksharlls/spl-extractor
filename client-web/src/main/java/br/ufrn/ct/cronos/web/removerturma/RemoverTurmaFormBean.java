package br.ufrn.ct.cronos.web.removerturma;

import br.ufrn.ct.cronos.obterturma.vo.ObterTurma;
import br.ufrn.ct.cronos.obterturma.vo.RespostaObterTurma;
import br.ufrn.ct.cronos.removerturma.vo.RemoverTurma;
import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class RemoverTurmaFormBean extends AbstractFormBean {
	
   private static final long serialVersionUID = 1L;
		
   private Long id;
   private String horario, docente, nomeDisciplina, codigoDisciplina, perfil, sala, numero;
   private Integer capacidade;
		
   public RemoverTurmaFormBean() {
      super();
   }
		
   public String removerTurma() throws NegocioException {
      try {
         RemoverTurma solicitacao = new RemoverTurma();
         popularVo(solicitacao);
         Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
         service.executa(solicitacao);
		
         limpar();
         addInfoMessage("Opera\u00E7\u00E3o realizada com sucesso.");
      } catch (NegocioException e) {
         obterTurma();
         throw new NegocioException(e.getErrorCode());
      }
      return null;
   }

   public void obterTurma() throws NegocioException {
      ObterTurma solicitacao = new ObterTurma();
      solicitacao.setId(this.id);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaObterTurma resposta = (RespostaObterTurma) service.executa(solicitacao);
      this.id = resposta.getObjeto().getId();
      this.horario = resposta.getObjeto().getHorario();
      this.docente = resposta.getObjeto().getDocente();
      this.nomeDisciplina = resposta.getObjeto().getNomeDisciplina();
      this.codigoDisciplina = resposta.getObjeto().getCodigoDisciplina();
      this.capacidade = resposta.getObjeto().getCapacidade();
      this.numero = resposta.getObjeto().getTurma();
      this.perfil = resposta.getObjeto().getPerfil();
      this.sala = resposta.getObjeto().getSala();
   }

   public String cancelar() {
      return ControlNavigationFormBean.pageConsultarTurma();
   }

   private void popularVo(RemoverTurma solicitacao) {
      solicitacao.setId(this.id);
   }

   private void limpar() {
      this.id = null;
      this.horario = new String("-");
      this.docente = new String("-");
      this.nomeDisciplina = new String("-");
      this.codigoDisciplina = new String("-");
      this.perfil = new String("-");
      this.sala = new String("-");
      this.capacidade = new Integer(0);
      this.numero = new String("-");
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getHorario() {
      return this.horario;
   }

   public void setHorario(String horario) {
      this.horario = horario;
   }

   public String getDocente() {
      return this.docente;
   }

   public void setDocente(String docente) {
      this.docente = docente;
   }

   public String getNomeDisciplina() {
      return this.nomeDisciplina;
   }

   public void setNomeDisciplina(String nomeDisciplina) {
      this.nomeDisciplina = nomeDisciplina;
   }

   public String getCodigoDisciplina() {
      return this.codigoDisciplina;
   }

   public void setCodigoDisciplina(String codigoDisciplina) {
      this.codigoDisciplina = codigoDisciplina;
   }

   public Integer getCapacidade() {
      return this.capacidade;
   }

   public void setCapacidade(Integer capacidade) {
      this.capacidade = capacidade;
   }

   public String getNumero() {
      return this.numero;
   }

   public void setNumero(String numero) {
      this.numero = numero;
   }

   public void setPerfil(String perfil) {
      this.perfil = perfil;
   }

   public String getPerfil() {
      return this.perfil;
   }

   public String getSala() {
      return sala;
   }

   public void setSala(String sala) {
      this.sala = sala;
   }
		
}
