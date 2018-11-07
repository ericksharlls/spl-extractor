package br.ufrn.ct.cronos.service.consultarperiodo;
 
import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.consultarperiodo.vo.ConsultarPeriodo;
import br.ufrn.ct.cronos.consultarperiodo.vo.DadosConsultarPeriodo;
import br.ufrn.ct.cronos.consultarperiodo.vo.RespostaConsultarPeriodo;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.entity.Periodo;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceConsultarPeriodo extends AbstractService<ConsultarPeriodo, RespostaConsultarPeriodo> {
   
   private PeriodoDao periodoDao;
      
   @Override
   public RespostaConsultarPeriodo processa(ConsultarPeriodo solicitacao) throws NegocioException {
      List<Periodo> lista = this.periodoDao.findAllOrderByDataTermino(solicitacao.getStartPage(), solicitacao.getMaxPage());
      Integer totalNumeroLinhas = this.periodoDao.count();
      List<DadosConsultarPeriodo> dadosConsultarSemestreLetivo = new ArrayList<DadosConsultarPeriodo>(lista.size());
      for (Periodo periodo : lista) {
         DadosConsultarPeriodo dados = new DadosConsultarPeriodo();
         dados.setId(periodo.getId());
         dados.setNome(periodo.getNome());
         dados.setDescricao(periodo.getDescricao());
         dados.setDataInicio(periodo.getDataInicio());
         dados.setDataTermino(periodo.getDataTermino());
         dados.setIsPeriodoLetivo(periodo.getIsPeriodoLetivo());

         dadosConsultarSemestreLetivo.add(dados);
      }
      return new RespostaConsultarPeriodo(dadosConsultarSemestreLetivo, totalNumeroLinhas);
   }

   @Override
   public void valida(ConsultarPeriodo solicitacao) throws NegocioException {

   }
      public void setPeriodoDao(PeriodoDao periodoDao){
      this.periodoDao = periodoDao;
   }

}
