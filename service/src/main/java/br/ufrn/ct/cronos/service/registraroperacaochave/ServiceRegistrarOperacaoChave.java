package br.ufrn.ct.cronos.service.registraroperacaochave;

import br.ufrn.ct.cronos.dao.ChaveDao;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.dao.HistoricoChaveDao;
import br.ufrn.ct.cronos.dao.UsuarioDao;
import br.ufrn.ct.cronos.entity.Chave;
import br.ufrn.ct.cronos.entity.HistoricoChave;
import br.ufrn.ct.cronos.registraroperacaochave.vo.RegistrarOperacaoChave;
import br.ufrn.ct.cronos.registraroperacaochave.vo.RespostaRegistrarOperacaoChave;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public abstract class ServiceRegistrarOperacaoChave extends AbstractService<RegistrarOperacaoChave, RespostaRegistrarOperacaoChave> {

   protected HistoricoChaveDao historicoChaveDao;
   protected ChaveDao chaveDao;
   protected UsuarioDao usuarioDao;
   protected FuncionarioDao funcionarioDao;

   @Override
   public RespostaRegistrarOperacaoChave processa(RegistrarOperacaoChave solicitacao) throws NegocioException {
      HistoricoChave historicoChave = new HistoricoChave();
      Chave chave = this.chaveDao.getByCodigo(solicitacao.getCodigoChave());

      historicoChave.setHoraRealizacao(solicitacao.getHoraRealizacao());
      historicoChave.setIdChave(chave.getId());
      historicoChave.setIdOperacao(solicitacao.getIdOperacao());
      historicoChave.setIdUsuario(this.usuarioDao.getByLogin(solicitacao.getLoginUsuario()).getId());

      obterIdResponsavel(historicoChave, solicitacao);

      this.historicoChaveDao.save(historicoChave);

      return new RespostaRegistrarOperacaoChave();
   }

   public abstract void valida(RegistrarOperacaoChave solicitacao) throws NegocioException;

   protected abstract Long obterIdResponsavel(HistoricoChave historicoChave, RegistrarOperacaoChave solicitacao);

   public void setHistoricoChaveDao(HistoricoChaveDao historicoChaveDao) {
      this.historicoChaveDao = historicoChaveDao;
   }

   public void setChaveDao(ChaveDao chaveDao) {
      this.chaveDao = chaveDao;
   }

   public void setUsuarioDao(UsuarioDao usuarioDao) {
      this.usuarioDao = usuarioDao;
   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

}
