package br.ufrn.ct.cronos.service.obtersemestreletivo;
 
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.entity.Periodo;
import br.ufrn.ct.cronos.obtersemestreletivo.vo.DadosObterSemestreLetivo;
import br.ufrn.ct.cronos.obtersemestreletivo.vo.ObterSemestreLetivo;
import br.ufrn.ct.cronos.obtersemestreletivo.vo.RespostaObterSemestreLetivo;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceObterSemestreLetivo extends AbstractService<ObterSemestreLetivo, RespostaObterSemestreLetivo> {
   
   private PeriodoDao periodoDao;
      
   @Override
   public RespostaObterSemestreLetivo processa(ObterSemestreLetivo solicitacao) throws NegocioException {
      Periodo periodo = this.periodoDao.get(solicitacao.getId());

      DadosObterSemestreLetivo dados = new DadosObterSemestreLetivo();
      dados.setDataInicio(periodo.getDataInicio());
      dados.setDataTermino(periodo.getDataTermino());
      dados.setDescricao(periodo.getDescricao());
      dados.setId(periodo.getId());
      dados.setNome(periodo.getNome());
      dados.setIsSemestreLetivo(periodo.getIsPeriodoLetivo());
      dados.setAno(periodo.getAno());
      dados.setNumeroPeriodo(periodo.getNumero());

      return new RespostaObterSemestreLetivo(dados);
   }

   @Override
   public void valida(ObterSemestreLetivo solicitacao) throws NegocioException {

   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }
      
}
