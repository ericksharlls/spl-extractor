package br.ufrn.ct.cronos.service.consultarsala;
import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.consultarsala.vo.ConsultarSala;
import br.ufrn.ct.cronos.consultarsala.vo.DadosConsultarSala;
import br.ufrn.ct.cronos.consultarsala.vo.RespostaConsultarSala;
import br.ufrn.ct.cronos.dao.PerfilSalaTurmaDao;
import br.ufrn.ct.cronos.dao.PredioDao;
import br.ufrn.ct.cronos.dao.SalaDao;
import br.ufrn.ct.cronos.entity.PerfilSalaTurma;
import br.ufrn.ct.cronos.entity.Predio;
import br.ufrn.ct.cronos.entity.Sala;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceConsultarSala extends AbstractService<ConsultarSala, RespostaConsultarSala>{
      
   private SalaDao salaDao;
   private PredioDao predioDao;
   private PerfilSalaTurmaDao perfilSalaTurmaDao;
      
   @Override
   public RespostaConsultarSala processa(ConsultarSala solicitacao) throws NegocioException {
      List<Sala> lista = this.salaDao.getByPredio(solicitacao.getIdPredio(), solicitacao.getStartPage(), solicitacao.getMaxPage());
      Integer totalNumeroLinhas = this.salaDao.countByPredio(solicitacao.getIdPredio());
      List<DadosConsultarSala> dadosConsultarSala = new ArrayList<DadosConsultarSala>(lista.size());

      for (Sala sala : lista) {
         DadosConsultarSala dados = new DadosConsultarSala();
         dados.setId(sala.getId());
         dados.setNome(sala.getNome());
         dados.setCapacidade(sala.getCapacidade());
         dados.setTipoQuadro(sala.getTipoQuadro());
         dados.setUtilizarNaDistribuicao(sala.getUtilizarNaDistribuicao());
         dados.setUtilizarNoAgendamento(sala.getUtilizarNoAgendamento());

         Predio predio = this.predioDao.get(sala.getIdPredio());
         dados.setPredio(predio.getNome());
         PerfilSalaTurma tipo = this.perfilSalaTurmaDao.get(sala.getIdTipo());
         dados.setPerfil(tipo.getNome());

         dadosConsultarSala.add(dados);
      }
      return new RespostaConsultarSala(dadosConsultarSala, totalNumeroLinhas);
   }
      
   @Override
   public void valida(ConsultarSala solicitacao) throws NegocioException {

   }

   public void setSalaDao(SalaDao salaDao) {
      this.salaDao = salaDao;
   }

   public void setPredioDao(PredioDao predioDao) {
      this.predioDao = predioDao;
   }

   public void setPerfilSalaTurmaDao(PerfilSalaTurmaDao perfilSalaTurmaDao) {
      this.perfilSalaTurmaDao = perfilSalaTurmaDao;
   }

}
