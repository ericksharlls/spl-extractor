package br.ufrn.ct.cronos.web.atualizarsala;
 
import br.ufrn.ct.cronos.atualizarsala.vo.AtualizarSala;
import br.ufrn.ct.cronos.obtersala.vo.ObterSala;
import br.ufrn.ct.cronos.obtersala.vo.RespostaObterSala;
import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class AtualizarSalaFormBean extends AbstractFormBean {
		
	private static final long serialVersionUID = 1L;
			
		private Long id;
		private String nome, descricao, tipoQuadro; 
		private int capacidade;
		private Boolean utilizarNaDistribuicao, utilizarNoAgendamento;
		private Long idTipoSala, idPredio;
		
		public AtualizarSalaFormBean() {
			super();
		}
 
		public String atualizarSala() throws NegocioException {
			AtualizarSala solicitacao = new AtualizarSala();
			popularVo(solicitacao);
			Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
			service.executa(solicitacao);
			addInfoMessage("Opera\u00E7\u00E3o realizada com sucesso.");
			return ControlNavigationFormBean.pageConsultarSala();
		}
		
		public String cancelar() {
			return ControlNavigationFormBean.pageConsultarSala();
		}
		
		public void obterSala() throws NegocioException {
			ObterSala solicitacao = new ObterSala();
			solicitacao.setId(this.id);
			Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
			RespostaObterSala resposta = (RespostaObterSala) service.executa(solicitacao);
			this.nome = resposta.getObjeto().getNome();
			this.id = resposta.getObjeto().getId();
			this.descricao = resposta.getObjeto().getDescricao();
			this.capacidade = resposta.getObjeto().getCapacidade();
			this.tipoQuadro = resposta.getObjeto().getTipoQuadro();
			this.idTipoSala = resposta.getObjeto().getIdTipoSala();
			this.idPredio = resposta.getObjeto().getIdPredio();
			this.utilizarNaDistribuicao = resposta.getObjeto().getUtilizarNaDistribuicao();
			this.utilizarNoAgendamento = resposta.getObjeto().getUtilizarNoAgendamento();
		}
		
		private void popularVo(AtualizarSala solicitacao) {
			solicitacao.setNome(this.nome);
			solicitacao.setId(this.id);
			solicitacao.setDescricao(this.descricao);
			solicitacao.setCapacidade(this.capacidade);
			solicitacao.setTipoQuadro(this.tipoQuadro);
			solicitacao.setIdTipoSala(this.idTipoSala);
			solicitacao.setIdPredio(this.idPredio);
			solicitacao.setUtilizarNaDistribuicao(this.utilizarNaDistribuicao);
			solicitacao.setUtilizarNoAgendamento(this.utilizarNoAgendamento);
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
		
		public String getDescricao() {
			return this.descricao;
		}
		
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		
		public int getCapacidade() {
			return this.capacidade;
		}
		
		public void setCapacidade(int capacidade) {
			this.capacidade = capacidade;
		}
		
		public Long getIdTipoSala() {
			return this.idTipoSala;
		}
		
		public void setIdTipoSala(Long idTipoSala) {
			this.idTipoSala = idTipoSala;
		}
		
		public Long getIdPredio() {
			return this.idPredio;
		}
		
		public void setIdPredio(Long idPredio) {
			this.idPredio = idPredio;
		}
		
		public String getTipoQuadro() {
			return this.tipoQuadro;
		}
		
		public void setTipoQuadro(String tipoQuadro) {
			this.tipoQuadro = tipoQuadro;
		}

      public Boolean getUtilizarNaDistribuicao() {
         return utilizarNaDistribuicao;
      }

      public void setUtilizarNaDistribuicao(Boolean utilizarNaDistribuicao) {
         this.utilizarNaDistribuicao = utilizarNaDistribuicao;
      }

      public Boolean getUtilizarNoAgendamento() {
         return utilizarNoAgendamento;
      }

      public void setUtilizarNoAgendamento(Boolean utilizarNoAgendamento) {
         this.utilizarNoAgendamento = utilizarNoAgendamento;
      }
	
}

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-client-web-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.web.atualizarsala.AtualizarSalaFormBean
 * JD-Core Version:    0.6.2
 */