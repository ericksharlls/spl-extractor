package br.ufrn.ct.cronos.web.removersala;
 
import br.ufrn.ct.cronos.obtersala.vo.ObterSala;
import br.ufrn.ct.cronos.obtersala.vo.RespostaObterSala;
import br.ufrn.ct.cronos.removersala.vo.RemoverSala;
import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class RemoverSalaFormBean extends AbstractFormBean {
	
   private static final long serialVersionUID = 1L;
		
   private Long id;
   private String nome, tipoQuadro, perfil, predio;
   private int capacidade;
		
   public RemoverSalaFormBean() {
      super();
   }
		
   public String removerSala() throws NegocioException {
      try {
         RemoverSala solicitacao = new RemoverSala();
         popularVo(solicitacao);
         Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
         service.executa(solicitacao);
         limpar();
         addInfoMessage("Opera\u00E7\u00E3o realizada com sucesso.");
      } catch (NegocioException e) {
         obterSala();
         throw new NegocioException(e.getErrorCode());
      }
      return null;
   }

   public String cancelar() {
      return ControlNavigationFormBean.pageConsultarSala();
   }
		
   public void obterSala() throws NegocioException {
      ObterSala solicitacao = new ObterSala();
      solicitacao.setId(this.id);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaObterSala resposta = (RespostaObterSala) service.executa(solicitacao);
      this.id = resposta.getObjeto().getId();
      this.nome = resposta.getObjeto().getNome();
      this.capacidade = resposta.getObjeto().getCapacidade();
      this.tipoQuadro = resposta.getObjeto().getTipoQuadro();
      this.perfil = resposta.getObjeto().getPerfil();
      this.predio = resposta.getObjeto().getPredio();
   }
		
   private void popularVo(RemoverSala solicitacao) {
      solicitacao.setId(this.id);
   }
		
   private void limpar() {
      this.id = null;
      this.nome = new String("-");
      this.tipoQuadro = new String("-");
      this.perfil = new String("-");
      this.predio = new String("-");
      this.capacidade = 0;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNome() {
      return this.nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getTipoQuadro() {
      return this.tipoQuadro;
   }

   public void setTipoQuadro(String tipoQuadro) {
      this.tipoQuadro = tipoQuadro;
   }

   public String getPerfil() {
      return this.perfil;
   }

   public void setPerfil(String perfil) {
      this.perfil = perfil;
   }

   public String getPredio() {
      return this.predio;
   }
		
   public void setPredio(String predio) {
      this.predio = predio;
   }

   public int getCapacidade() {
      return this.capacidade;
   }

   public void setCapacidade(int capacidade) {
      this.capacidade = capacidade;
   }
 
}
