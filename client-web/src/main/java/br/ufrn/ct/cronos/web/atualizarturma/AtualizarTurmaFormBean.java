package br.ufrn.ct.cronos.web.atualizarturma;
 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.model.SelectItem;
import br.ufrn.ct.cronos.atualizarturma.vo.AtualizarTurma;
import br.ufrn.ct.cronos.atualizarturma.vo.DadosDocente;
import br.ufrn.ct.cronos.obterfuncionario.vo.DadosObterFuncionario;
import br.ufrn.ct.cronos.obterfuncionario.vo.ObterFuncionario;
import br.ufrn.ct.cronos.obterfuncionario.vo.RespostaObterFuncionario;
import br.ufrn.ct.cronos.obterturma.vo.DadosObterTurma;
import br.ufrn.ct.cronos.obterturma.vo.ObterTurma;
import br.ufrn.ct.cronos.obterturma.vo.RespostaObterTurma;
import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class AtualizarTurmaFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;
		
   private Long id;
   private String docente, nomeDisciplina, codigoDisciplina, local, horario, turma;
   private Integer capacidade;
   private Long idTipoSala, idPredio;
   private Long idDocente;
   private List<DadosDocente> docentes;
   private Boolean distribuir;
		
   public String atualizarTurma() throws NegocioException {
      AtualizarTurma solicitacao = new AtualizarTurma();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      service.executa(solicitacao);
      return ControlNavigationFormBean.pageConsultarTurma();
   }

   public String cancelar() {
      return ControlNavigationFormBean.pageConsultarTurma();
   }
		
   public void obterTurma() throws NegocioException {
      ObterTurma solicitacao = new ObterTurma();
      solicitacao.setId(this.id);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaObterTurma resposta = (RespostaObterTurma) service.executa(solicitacao);
		
      this.id = resposta.getObjeto().getId();
      this.codigoDisciplina = resposta.getObjeto().getCodigoDisciplina();
      this.docentes = resposta.getObjeto().getDocentes();
      this.idTipoSala = resposta.getObjeto().getIdTipoSala();
      this.nomeDisciplina = resposta.getObjeto().getNomeDisciplina();
      this.distribuir = resposta.getObjeto().getDistribuir();
      this.local = resposta.getObjeto().getSala();
      this.capacidade = resposta.getObjeto().getCapacidade();
      this.turma = resposta.getObjeto().getTurma();
      this.horario = resposta.getObjeto().getHorario();
      this.idPredio = resposta.getObjeto().getIdPredio();
      this.docentes = resposta.getObjeto().getDocentes();
   }
		
   private void popularVo(AtualizarTurma solicitacao) throws NegocioException {
      DadosObterTurma dados = new DadosObterTurma();
      dados = obterObjetoTurma();

      solicitacao.setId(dados.getId());
      solicitacao.setCodigoDisciplina(dados.getCodigoDisciplina());
      solicitacao.setDocentes(this.docentes);
      solicitacao.setNomeDisciplina(dados.getNomeDisciplina());
      solicitacao.setHorario(dados.getHorario());
      solicitacao.setCapacidade(dados.getCapacidade());
      solicitacao.setTurma(dados.getTurma());
			
      solicitacao.setCapacidade(this.capacidade);
      solicitacao.setIdTipo(this.idTipoSala);
      solicitacao.setDistribuir(this.distribuir);
      solicitacao.setIdPredio(this.idPredio);
      solicitacao.setCodigoDisciplina(this.codigoDisciplina);
      solicitacao.setHorario(this.horario);
      solicitacao.setDocentes(this.docentes);
      solicitacao.setNomeDisciplina(this.nomeDisciplina);
      solicitacao.setTurma(this.turma);
   }
		
   public DadosObterTurma obterObjetoTurma() throws NegocioException {
      ObterTurma solicitacao = new ObterTurma();
      solicitacao.setId(this.id);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaObterTurma resposta = (RespostaObterTurma) service.executa(solicitacao);

      return resposta.getObjeto();
   }

   public List<SelectItem> getDocentes() {
      List<SelectItem> items = new ArrayList<SelectItem>();
      if (this.docentes == null) {
         this.docentes = new ArrayList<DadosDocente>();
      }
      for (DadosDocente docente : this.docentes) {
         SelectItem item = new SelectItem(docente.getId(), docente.getNome());
         items.add(item);
      }
      return items;
   }

   public void removerDocente() throws NegocioException {
      if (this.docentes == null) {
         this.docentes = new ArrayList<DadosDocente>();
      }
      if (this.idDocente != null) {
         Iterator<DadosDocente> iterator = this.docentes.iterator();
         while (iterator.hasNext()) {
            if (iterator.next().getId().equals(new Long(this.idDocente))) {
               iterator.remove();
            }
         }
      }
   }

   public void adicionarDocente() throws NegocioException {
      if (this.docentes == null) {
         this.docentes = new ArrayList<DadosDocente>();
      }
      DadosDocente dadosDocente = new DadosDocente();
      ObterFuncionario solicitacao = new ObterFuncionario();
      solicitacao.setId(this.idDocente);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaObterFuncionario resposta = (RespostaObterFuncionario) service.executa(solicitacao);
      DadosObterFuncionario dadosObterFuncionario = resposta.getObjeto();
      dadosDocente.setId(dadosObterFuncionario.getId());
      dadosDocente.setNome(dadosObterFuncionario.getNome());
      int verificador = 0;
      for (DadosDocente dc : this.docentes) {
         if (dc.getNome().equals(dadosDocente.getNome())) {
            verificador++;
         }
      }
      if (verificador == 0) {
         this.docentes.add(dadosDocente);
      }
      this.idDocente = null;
   }
		
		public Long getId(){
			return this.id;
		}
		
		public void setId(Long id) {
			this.id = id;
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
 
		public Long getIdTipoSala() {
			return this.idTipoSala;
		}
		
		public void setIdTipoSala(Long idTipoSala) {
			this.idTipoSala = idTipoSala;
		}
		
		public void setLocal(String local) {
			this.local = local;
		}
		
		public String getLocal() {
			return this.local;
		}
		
		public void setDistribuir(Boolean distribuir) {
			this.distribuir = distribuir;
		}
		
		public Boolean getDistribuir() {
			return this.distribuir;
		}
		
   public String getTurma() {
			return this.turma;
		}
		
   public void setTurma(String turma) {
			this.turma = turma;
		}
		
		public Integer getCapacidade() {
			return this.capacidade;
		}
		
		public void setCapacidade(Integer capacidade) {
			this.capacidade = capacidade;
		}

		public String getHorario() {
			return horario;
		}

		public void setHorario(String horario) {
			this.horario = horario;
		}

      public Long getIdPredio() {
         return idPredio;
      }

      public void setIdPredio(Long idPredio) {
         this.idPredio = idPredio;
      }

   public void setDocentes(List<DadosDocente> docentes) {
      this.docentes = docentes;
   }

   public Long getIdDocente() {
      return idDocente;
   }

   public void setIdDocente(Long idDocente) {
      this.idDocente = idDocente;
   }
		
}

