package br.ufrn.ct.cronos.web.consultarsalasparareservapordepartamento;

import java.util.ArrayList;
import java.util.List;
import org.richfaces.model.DataProvider;
import org.richfaces.model.ExtendedTableDataModel;
import org.richfaces.model.selection.SimpleSelection;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import br.ufrn.ct.cronos.consultarsalasparareservapordepartamento.vo.ConsultarSalasParaReservaPorDepartamento;
import br.ufrn.ct.cronos.consultarsalasparareservapordepartamento.vo.DadosConsultarSalasParaReservaPorDepartamento;
import br.ufrn.ct.cronos.consultarsalasparareservapordepartamento.vo.RespostaConsultarSalasParaReservaPorDepartamento;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractConsultarFormBean;

public class ConsultarSalasParaReservaPorDepartamentoFormBean extends AbstractConsultarFormBean {

   /** @TODO Comentar atributo */
   private static final long serialVersionUID = 1L;

   private String horarioUm, horarioDois, horarioTres;
   private Long idSala, idPeriodo, idPredio;

   private SimpleSelection selection;
   private ExtendedTableDataModel<DadosConsultarSalasParaReservaPorDepartamento> dataModel;
   private List<DadosConsultarSalasParaReservaPorDepartamento> salas = new ArrayList<DadosConsultarSalasParaReservaPorDepartamento>();
   private List<DadosConsultarSalasParaReservaPorDepartamento> salasSelecionadas =
      new ArrayList<DadosConsultarSalasParaReservaPorDepartamento>();

   public ExtendedTableDataModel<DadosConsultarSalasParaReservaPorDepartamento> getDadosDataModel() throws NegocioException {
      dataModel =
         new ExtendedTableDataModel<DadosConsultarSalasParaReservaPorDepartamento>(
            new DataProvider<DadosConsultarSalasParaReservaPorDepartamento>() {

         private static final long serialVersionUID = 1L;

         @Override
               public DadosConsultarSalasParaReservaPorDepartamento getItemByKey(Object key) {
                  for (DadosConsultarSalasParaReservaPorDepartamento c : salas) {
               if (key.equals(getKey(c))) {
                  return c;
               }
            }
            return null;
         }

               public List<DadosConsultarSalasParaReservaPorDepartamento> getItemsByRange(int firstRow, int endRow) {
            return salas.subList(firstRow, endRow);
         }

         @Override
               public Object getKey(DadosConsultarSalasParaReservaPorDepartamento item) {
            return item.getNome();
         }

         @Override
         public int getRowCount() {
            return salas.size();
         }
      });

      return this.dataModel;
   }

   public String consultar() throws NegocioException {
      ConsultarSalasParaReservaPorDepartamento solicitacao = new ConsultarSalasParaReservaPorDepartamento();

      solicitacao.setIdPeriodo(this.idPeriodo);
      solicitacao.setIdPredio(this.idPredio);
      solicitacao.setHorarioUm(this.horarioUm);
      solicitacao.setHorarioDois(this.horarioDois);
      solicitacao.setHorarioTres(this.horarioTres);

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      solicitacao.setNomeUsuario(authentication.getName());

      this.salas.clear();

      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaConsultarSalasParaReservaPorDepartamento resposta =
         (RespostaConsultarSalasParaReservaPorDepartamento) service.executa(solicitacao);
      this.salas = resposta.getDados();

      return null;
   }

   @Override
   public int getLinhas() {
      return 0;
   }

   /**
    * Recupera o valor do atributo horarioUm
    * @return o horarioUm
    */
   public String getHorarioUm() {
      return horarioUm;
   }

   /**
    * Atribui o novo valor de horarioUm
    * @param horarioUm horarioUm que será atribuído
    */
   public void setHorarioUm(String horarioUm) {
      this.horarioUm = horarioUm;
   }

   /**
    * Recupera o valor do atributo horarioDois
    * @return o horarioDois
    */
   public String getHorarioDois() {
      return horarioDois;
   }

   /**
    * Atribui o novo valor de horarioDois
    * @param horarioDois horarioDois que será atribuído
    */
   public void setHorarioDois(String horarioDois) {
      this.horarioDois = horarioDois;
   }

   /**
    * Recupera o valor do atributo horarioTres
    * @return o horarioTres
    */
   public String getHorarioTres() {
      return horarioTres;
   }

   /**
    * Atribui o novo valor de horarioTres
    * @param horarioTres horarioTres que será atribuído
    */
   public void setHorarioTres(String horarioTres) {
      this.horarioTres = horarioTres;
   }

   /**
    * Recupera o valor do atributo idSala
    * @return o idSala
    */
   public Long getIdSala() {
      return idSala;
   }

   /**
    * Atribui o novo valor de idSala
    * @param idSala idSala que será atribuído
    */
   public void setIdSala(Long idSala) {
      this.idSala = idSala;
   }

   /**
    * Recupera o valor do atributo idPeriodo
    * @return o idPeriodo
    */
   public Long getIdPeriodo() {
      return idPeriodo;
   }

   /**
    * Atribui o novo valor de idPeriodo
    * @param idPeriodo idPeriodo que será atribuído
    */
   public void setIdPeriodo(Long idPeriodo) {
      this.idPeriodo = idPeriodo;
   }

   /**
    * Recupera o valor do atributo idPredio
    * @return o idPredio
    */
   public Long getIdPredio() {
      return idPredio;
   }

   /**
    * Atribui o novo valor de idPredio
    * @param idPredio idPredio que será atribuído
    */
   public void setIdPredio(Long idPredio) {
      this.idPredio = idPredio;
   }

   /**
    * Recupera o valor do atributo selection
    * @return o selection
    */
   public SimpleSelection getSelection() {
      return selection;
   }

   /**
    * Atribui o novo valor de selection
    * @param selection selection que será atribuído
    */
   public void setSelection(SimpleSelection selection) {
      this.selection = selection;
   }

   /**
    * Recupera o valor do atributo dataModel
    * @return o dataModel
    */
   public ExtendedTableDataModel<DadosConsultarSalasParaReservaPorDepartamento> getDataModel() {
      return dataModel;
   }

   /**
    * Atribui o novo valor de dataModel
    * @param dataModel dataModel que será atribuído
    */
   public void setDataModel(ExtendedTableDataModel<DadosConsultarSalasParaReservaPorDepartamento> dataModel) {
      this.dataModel = dataModel;
   }

   /**
    * Recupera o valor do atributo salas
    * @return o salas
    */
   public List<DadosConsultarSalasParaReservaPorDepartamento> getSalas() {
      return salas;
   }

   /**
    * Atribui o novo valor de salas
    * @param salas salas que será atribuído
    */
   public void setSalas(List<DadosConsultarSalasParaReservaPorDepartamento> salas) {
      this.salas = salas;
   }

   /**
    * Recupera o valor do atributo salasSelecionadas
    * @return o salasSelecionadas
    */
   public List<DadosConsultarSalasParaReservaPorDepartamento> getSalasSelecionadas() {
      return salasSelecionadas;
   }

   /**
    * Atribui o novo valor de salasSelecionadas
    * @param salasSelecionadas salasSelecionadas que será atribuído
    */
   public void setSalasSelecionadas(List<DadosConsultarSalasParaReservaPorDepartamento> salasSelecionadas) {
      this.salasSelecionadas = salasSelecionadas;
   }

}
