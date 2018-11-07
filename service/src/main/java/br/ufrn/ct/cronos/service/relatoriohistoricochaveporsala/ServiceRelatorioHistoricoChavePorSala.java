package br.ufrn.ct.cronos.service.relatoriohistoricochaveporsala;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import br.ufrn.ct.cronos.dao.ChaveDao;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.dao.HistoricoChaveDao;
import br.ufrn.ct.cronos.dao.OperacaoDao;
import br.ufrn.ct.cronos.dao.SalaDao;
import br.ufrn.ct.cronos.dao.UsuarioDao;
import br.ufrn.ct.cronos.entity.Chave;
import br.ufrn.ct.cronos.entity.Funcionario;
import br.ufrn.ct.cronos.entity.HistoricoChave;
import br.ufrn.ct.cronos.entity.Operacao;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.entity.Usuario;
import br.ufrn.ct.cronos.relatoriohistoricochaveporsala.vo.DadosHistoricoChavePorSala;
import br.ufrn.ct.cronos.relatoriohistoricochaveporsala.vo.RelatorioHistoricoChavePorSala;
import br.ufrn.ct.cronos.relatoriohistoricochaveporsala.vo.RespostaRelatorioHistoricoChavePorSala;
import br.ufrn.ct.cronos.service.ManipuladorDatas;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceRelatorioHistoricoChavePorSala extends
         AbstractService<RelatorioHistoricoChavePorSala, RespostaRelatorioHistoricoChavePorSala> {

   private HistoricoChaveDao historicoChaveDao;
   private ChaveDao chaveDao;
   private SalaDao salaDao;
   private FuncionarioDao funcionarioDao;
   private OperacaoDao operacaoDao;
   private UsuarioDao usuarioDao;

   @Override
   public RespostaRelatorioHistoricoChavePorSala processa(RelatorioHistoricoChavePorSala solicitacao) throws NegocioException {
      List<HistoricoChave> lista = this.historicoChaveDao.getHistoricoChavePorDataESala(solicitacao.getData(), solicitacao.getIdSala());
      List<DadosHistoricoChavePorSala> dadosHistoricoChave = new ArrayList<DadosHistoricoChavePorSala>(0);

      for (HistoricoChave historicoChave : lista) {
         Chave chave = this.chaveDao.get(historicoChave.getIdChave());
         Sala sala = this.salaDao.get(chave.getIdSala());
         Operacao operacao = this.operacaoDao.get(historicoChave.getIdOperacao());
         DadosHistoricoChavePorSala dado = new DadosHistoricoChavePorSala();

         if (historicoChave.getIdResponsavel() != null && !historicoChave.getIdResponsavel().equals(new Long(0))) {
            dado.setResponsavel(this.funcionarioDao.get(historicoChave.getIdResponsavel()).getNome());
         } else {
            dado.setResponsavel("INDEFINIDO");
         }
         dado.setDescricaoChave(chave.getDescricao());
         dado.setSala(sala.getNome());
         dado.setHorario(ManipuladorDatas.retornaStringDataFormatoHoraMinutoSegundo(historicoChave.getHoraRealizacao()));
         dado.setOperacao(operacao.getNome());

         Usuario usuario = this.usuarioDao.get(historicoChave.getIdUsuario());
         Funcionario funcionarioDoUsuario = this.funcionarioDao.get(usuario.getIdFuncionario());
         dado.setUsuarioSistema(funcionarioDoUsuario.getNome());

         dadosHistoricoChave.add(dado);
      }
      Collections.sort(dadosHistoricoChave, new Comparator<DadosHistoricoChavePorSala>() {

         public int compare(DadosHistoricoChavePorSala hc1, DadosHistoricoChavePorSala hc2) {
            // return hc1.getSala().compareTo(hc2.getSala());
            int resultado = hc1.getSala().compareTo(hc2.getSala());
            return resultado == 0 ? hc1.getHorario().compareTo(hc2.getHorario()) : resultado;
         }
      });

      return new RespostaRelatorioHistoricoChavePorSala(dadosHistoricoChave);
   }

   @Override
   public void valida(RelatorioHistoricoChavePorSala solicitacao) throws NegocioException {
   }

   public void setHistoricoChaveDao(HistoricoChaveDao historicoChaveDao) {
      this.historicoChaveDao = historicoChaveDao;
   }

   public void setSalaDao(SalaDao salaDao) {
      this.salaDao = salaDao;
   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

   public void setChaveDao(ChaveDao chaveDao) {
      this.chaveDao = chaveDao;
   }

   public void setOperacaoDao(OperacaoDao operacaoDao) {
      this.operacaoDao = operacaoDao;
   }

   public void setUsuarioDao(UsuarioDao usuarioDao) {
      this.usuarioDao = usuarioDao;
   }

}
