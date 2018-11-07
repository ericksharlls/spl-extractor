package br.ufrn.ct.cronos.web.cadastrarsala;
 
import br.ufrn.ct.cronos.cadastrarsala.vo.CadastrarSala;
import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;
 
public class CadastrarSalaFormBean extends AbstractFormBean {

		private static final long serialVersionUID = 1L;
		
		private String nome, descricao, tipoQuadro;
		private int capacidade;
   private Boolean conexaoInternet, utilizarNaDistribuicao, utilizarNoAgendamento;
		private Long idTipoSala, idPredio;
		
		public CadastrarSalaFormBean() {
			super();
		}
		
		public String cadastrarSala() throws NegocioException {
			CadastrarSala solicitacao = new CadastrarSala();
			popularVo(solicitacao);
			Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
			service.executa(solicitacao);

			limpar();
			addInfoMessage("Opera\u00E7\u00E3o realizada com sucesso.");
			return null;
		}

		public String cancelar() {
			return ControlNavigationFormBean.pageConsultarSala();
		}
		
		private void popularVo(CadastrarSala solicitacao) {
			solicitacao.setNome(this.nome);
			solicitacao.setCapacidade(this.capacidade);
			solicitacao.setDescricao(this.descricao);
			solicitacao.setTipoQuadro(this.tipoQuadro);
			solicitacao.setConexaoInternet(this.conexaoInternet);
			solicitacao.setIdTipoSala(this.idTipoSala);
      solicitacao.setIdPredio(this.idPredio);
      solicitacao.setUtilizarNaDistribuicao(this.utilizarNaDistribuicao);
      solicitacao.setUtilizarNoAgendamento(this.utilizarNoAgendamento);
		}
		
		private void limpar() {
			this.nome = null;
			this.descricao = null;
			this.tipoQuadro = null;
			this.capacidade = 0;
			this.conexaoInternet = null;
			this.idTipoSala = null;
			this.idPredio = null;
      this.utilizarNaDistribuicao = false;
      this.utilizarNoAgendamento = false;
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
		
		public Boolean getConexaoInternet() {
		   return this.conexaoInternet;
		}
		
		public void setConexaoInternet(Boolean conexaoInternet) {
		   this.conexaoInternet = conexaoInternet;
		}
		
		public String getTipoQuadro() {
		   return this.tipoQuadro;
		}
		
		public void setTipoQuadro(String tipoQuadro) {
		   this.tipoQuadro = tipoQuadro;
		}

      public Boolean getUtilizarNoAgendamento() {
         return utilizarNoAgendamento;
      }

      public void setUtilizarNoAgendamento(Boolean utilizarNoAgendamento) {
         this.utilizarNoAgendamento = utilizarNoAgendamento;
      }

      public Boolean getUtilizarNaDistribuicao() {
         return utilizarNaDistribuicao;
      }

      public void setUtilizarNaDistribuicao(Boolean utilizarNaDistribuicao) {
         this.utilizarNaDistribuicao = utilizarNaDistribuicao;
      }

}
