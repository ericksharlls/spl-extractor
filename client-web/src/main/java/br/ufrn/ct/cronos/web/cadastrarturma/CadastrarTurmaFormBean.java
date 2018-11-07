package br.ufrn.ct.cronos.web.cadastrarturma;
 
import br.ufrn.ct.cronos.cadastrarturma.vo.CadastrarTurma;
import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class CadastrarTurmaFormBean extends AbstractFormBean {
		
   private static final long serialVersionUID = 1L;
   private String horario, docente, nomeDisciplina, codigoDisciplina, departamento, departamento2, numero;
   private Integer capacidade;
   private Long idTipo, idPredio, idPeriodo, idDepartamento, idDocente;
   private Boolean distribuir;
		
   public CadastrarTurmaFormBean() {
      super();
   }
 
   public String cadastrarTurma() throws NegocioException {
      CadastrarTurma solicitacao = new CadastrarTurma();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      service.executa(solicitacao);
			
      limpar();
      addInfoMessage("Opera\u00E7\u00E3o realizada com sucesso.");
      return null;
   }

   public String cancelar() {
      return ControlNavigationFormBean.pageConsultarTurma();
   }
 
   private void popularVo(CadastrarTurma solicitacao) {
      solicitacao.setCapacidade(this.capacidade);
      solicitacao.setCodigoDisciplina(this.codigoDisciplina);
      solicitacao.setIdDepartamento(this.idDepartamento);
      solicitacao.setDistribuir(this.distribuir);
      solicitacao.setDocente(this.docente);
      solicitacao.setHorario(this.horario);
      solicitacao.setIdTipo(this.idTipo);
      solicitacao.setNomeDisciplina(this.nomeDisciplina);
      solicitacao.setNumero(this.numero);
      solicitacao.setIdPredio(this.idPredio);
      solicitacao.setIdPeriodo(this.idPeriodo);
      solicitacao.setIdDocente(this.idDocente);
   }
		
   private void limpar() {
      this.horario = new String("-");
      this.docente = new String("-");
      this.nomeDisciplina = new String("-");
      this.codigoDisciplina = new String("-");
      this.departamento = new String("-");
      this.departamento2 = new String("-");
      this.capacidade = new Integer(0);
      this.numero = new String();
      this.idTipo = null;
      this.idPeriodo = null;
      this.idPredio = null;
      this.distribuir = false;
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
		
   public String getDepartamento() {
      return this.departamento;
   }
		
   public void setDepartamento(String departamento) {
      this.departamento = departamento;
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
 
   public Long getIdTipo() {
      return this.idTipo;
   }
		
   public void setIdTipo(Long idTipo) {
      this.idTipo = idTipo;
   }
		
   public void setDepartamento2(String departamento2) {
      this.departamento2 = departamento2;
   }
		
   public String getDepartamento2() {
      return this.departamento2;
   }

   public Long getIdPredio() {
      return idPredio;
   }

   public void setIdPredio(Long idPredio) {
      this.idPredio = idPredio;
   }

   public Long getIdPeriodo() {
      return idPeriodo;
   }

   public void setIdPeriodo(Long idPeriodo) {
      this.idPeriodo = idPeriodo;
   }

   public Boolean getDistribuir() {
      return distribuir;
   }

   public void setDistribuir(Boolean distribuir) {
      this.distribuir = distribuir;
   }

   public Long getIdDepartamento() {
      return idDepartamento;
   }

   public void setIdDepartamento(Long idDepartamento) {
      this.idDepartamento = idDepartamento;
   }

   public Long getIdDocente() {
      return idDocente;
   }

   public void setIdDocente(Long idDocente) {
      this.idDocente = idDocente;
   }
		
}
