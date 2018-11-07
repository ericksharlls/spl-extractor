package br.ufrn.ct.cronos.web.consultarsaladisponiveis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.model.SelectItem;
import org.richfaces.model.DataProvider;
import org.richfaces.model.ExtendedTableDataModel;
import org.richfaces.model.selection.SimpleSelection;
import br.ufrn.ct.cronos.alocarturma.vo.AlocarTurma;
import br.ufrn.ct.cronos.consultarsalasdisponiveis.vo.ConsultarSalasDisponiveis;
import br.ufrn.ct.cronos.consultarsalasdisponiveis.vo.DadosConsultarSalasDisponiveis;
import br.ufrn.ct.cronos.consultarsalasdisponiveis.vo.RespostaConsultarSalasDisponiveis;
import br.ufrn.ct.cronos.listarturma.vo.DadosListarTurma;
import br.ufrn.ct.cronos.listarturma.vo.ListarTurma;
import br.ufrn.ct.cronos.listarturma.vo.RespostaListarTurma;
import br.ufrn.ct.cronos.obterturma.vo.DadosObterTurma;
import br.ufrn.ct.cronos.obterturma.vo.ObterTurma;
import br.ufrn.ct.cronos.obterturma.vo.RespostaObterTurma;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractConsultarFormBean;

public class BackupConsultarSalasDisponiveisFormBean_22_01_2016 extends AbstractConsultarFormBean {

   private static final long serialVersionUID = 1L;
   private Long idTurma;
   private Long idSala;
   private Long idPeriodo;
   private Long idPredio;
   private Long idDepartamento;
   private DadosObterTurma turma;
   private SimpleSelection selection;
   private ExtendedTableDataModel<DadosConsultarSalasDisponiveis> dataModel;
   private List<DadosConsultarSalasDisponiveis> salas = new ArrayList<DadosConsultarSalasDisponiveis>();
   private List<DadosConsultarSalasDisponiveis> salasSeleciondas = new ArrayList<DadosConsultarSalasDisponiveis>();

   public BackupConsultarSalasDisponiveisFormBean_22_01_2016() {
      super();
      if (this.turma == null) {
         this.turma = new DadosObterTurma();
         this.turma.setNomeDisciplina(" - ");
         this.turma.setDocente(" - ");
         this.turma.setHorario(" - ");
         this.turma.setPerfil(" - ");
         this.turma.setSala(" - ");
         this.turma.setCapacidade(new Integer(0));
      }
   }

   public ExtendedTableDataModel<DadosConsultarSalasDisponiveis> getDadosDataModel() throws NegocioException {
      ConsultarSalasDisponiveis solicitacao = new ConsultarSalasDisponiveis();
      solicitacao.setIdTurma(getIdTurma());
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaConsultarSalasDisponiveis resposta = (RespostaConsultarSalasDisponiveis) service.executa(solicitacao);
      this.salas = resposta.getDados();

      this.dataModel = new ExtendedTableDataModel<DadosConsultarSalasDisponiveis>(new DataProvider<DadosConsultarSalasDisponiveis>() {

         private static final long serialVersionUID = 1L;

         @Override
         public DadosConsultarSalasDisponiveis getItemByKey(Object key) {
            for (DadosConsultarSalasDisponiveis c : salas) {
               if (key.equals(getKey(c))) {
                  return c;
               }
            }
            return null;
         }

         public List<DadosConsultarSalasDisponiveis> getItemsByRange(int firstRow, int endRow) {
            return salas.subList(firstRow, endRow);
         }

         @Override
         public Object getKey(DadosConsultarSalasDisponiveis item) {
            return item.getNome();
         }

         @Override
         public int getRowCount() {
            return salas.size();
         }

      });
      return this.dataModel;
   }

   public String alocar() throws NegocioException {
      AlocarTurma solicitacao = new AlocarTurma();
      for (DadosConsultarSalasDisponiveis d : getSalasSeleciondas()) {
         this.idSala = d.getId();
      }
      solicitacao.setIdTurma(this.idTurma);
      solicitacao.setIdSala(this.idSala);

      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      service.executa(solicitacao);

      addInfoMessage("Opera\u00E7\u00E3o realizada com sucesso.");

      this.salasSeleciondas.clear();
      ObterTurma solicitacaoObterTurma = new ObterTurma();
      solicitacaoObterTurma.setId(this.idTurma);
      Service<Response, Request> serviceObterTurma = ServiceUtils.getService(solicitacaoObterTurma.getClass().getSimpleName());
      RespostaObterTurma resposta = (RespostaObterTurma) serviceObterTurma.executa(solicitacaoObterTurma);
      this.setTurma(resposta.getObjeto());

      return null;
   }

   public void selecionar() throws NegocioException {
      getSalasSeleciondas().clear();
      Iterator<Object> iterator = getSelection().getKeys();
      while (iterator.hasNext()) {
         Object key = iterator.next();
         getSalasSeleciondas().add(getDadosDataModel().getObjectByKey(key));
      }
   }

   public void limpar() {
      this.idTurma = new Long(0);
      this.idSala = new Long(0);
      this.salasSeleciondas.clear();
      this.selection.clear();
      this.selection = new SimpleSelection();
      }

   public String atualizarTurma() throws NegocioException {
      ObterTurma solicitacao = new ObterTurma();
      solicitacao.setId(this.idTurma);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaObterTurma resposta = (RespostaObterTurma) service.executa(solicitacao);
      this.setTurma(resposta.getObjeto());
      return null;
      }

   public List<SelectItem> getTurmasPorPeriodoEPredio() throws NegocioException {
      List<SelectItem> itens = new ArrayList<SelectItem>();
      ListarTurma solicitacao = new ListarTurma();
      if (this.idPeriodo == null) {
         solicitacao.setIdPeriodo(new Long(0));
      } else {
         solicitacao.setIdPeriodo(this.idPeriodo);
      }
      if (this.idPredio == null) {
         solicitacao.setIdPredio(new Long(0));
      } else {
         solicitacao.setIdPredio(this.idPredio);
      }
      if (this.idPredio == null) {
         solicitacao.setIdDepartamento(new Long(0));
      } else {
         solicitacao.setIdDepartamento(this.idDepartamento);
      }
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaListarTurma resposta = (RespostaListarTurma) service.executa(solicitacao);
      List<DadosListarTurma> dados = resposta.getDados();
      if (!dados.isEmpty()) {
         itens = new ArrayList<SelectItem>(dados.size());
         for (DadosListarTurma d : dados) {
            itens.add(new SelectItem(d.getId(), d.getNome()));
         }
      }
      return itens;
   }

   public String limparDadosTurma() throws NegocioException {
      this.turma = new DadosObterTurma();
      this.turma.setNomeDisciplina(" - ");
      this.turma.setDocente(" - ");
      this.turma.setHorario(" - ");
      this.turma.setPerfil(" - ");
      this.turma.setSala(" - ");
      this.turma.setCapacidade(new Integer(0));
      return null;
   }

   /*
    * public String atualizarSalasDisponiveis() throws NegocioException{ ConsultarSalasDisponiveis solicitacao = new
    * ConsultarSalasDisponiveis(); solicitacao.setIdTurma(this.getIdTurma()); Service<Response, Request> service =
    * ServiceUtils.getService(solicitacao.getClass().getSimpleName()); RespostaConsultarSalasDisponiveis resposta =
    * (RespostaConsultarSalasDisponiveis) service.executa(solicitacao); this.salas = resposta.getDados(); return null; }
    */

   @Override
   public int getLinhas() {
      return 0;
   }

   public void setIdTurma(Long idTurma) {
      this.idTurma = idTurma;
   }

   public Long getIdTurma() {
      return this.idTurma;
   }

   public void setIdSala(Long idSala) {
      this.idSala = idSala;
   }

   public Long getIdSala() {
      return this.idSala;
   }

   public void setSelection(SimpleSelection selection) {
      this.selection = selection;
   }

   public SimpleSelection getSelection() {
      return this.selection;
   }

   public void setSalas(List<DadosConsultarSalasDisponiveis> salas) {
      this.salas = salas;
   }

   public List<DadosConsultarSalasDisponiveis> getSalas() {
      return this.salas;
   }

   public void setSalasSeleciondas(List<DadosConsultarSalasDisponiveis> salasSeleciondas) {
      this.salasSeleciondas = salasSeleciondas;
   }

   public List<DadosConsultarSalasDisponiveis> getSalasSeleciondas() {
      return this.salasSeleciondas;
   }

   public void setTurma(DadosObterTurma turma) {
      this.turma = turma;
   }

   public DadosObterTurma getTurma() {
      return this.turma;
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

   public Long getIdDepartamento() {
      return idDepartamento;
   }

   public void setIdDepartamento(Long idDepartamento) {
      this.idDepartamento = idDepartamento;
   }

}
