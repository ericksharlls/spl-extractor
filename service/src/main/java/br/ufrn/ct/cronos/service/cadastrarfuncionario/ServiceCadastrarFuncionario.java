package br.ufrn.ct.cronos.service.cadastrarfuncionario;

import java.text.ParseException;
import org.apache.commons.lang.StringUtils;
import br.ufrn.ct.cronos.cadastrarfuncionario.vo.CadastrarFuncionario;
import br.ufrn.ct.cronos.cadastrarfuncionario.vo.RespostaCadastrarFuncionario;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.entity.Funcionario;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.service.FormatadorMascaras;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.utils.email.EmailUtils;

public class ServiceCadastrarFuncionario extends AbstractService<CadastrarFuncionario, RespostaCadastrarFuncionario> {

   private FuncionarioDao funcionarioDao;

   public RespostaCadastrarFuncionario processa(CadastrarFuncionario solicitacao) throws NegocioException {

      Funcionario funcionario = new Funcionario();
      funcionario.setEmail(solicitacao.getEmail());
      funcionario.setMatricula(solicitacao.getMatricula());
      funcionario.setNome(solicitacao.getNome());
      funcionario.setIdTipoFuncionario(solicitacao.getIdTipoFuncionario());
      try {
         funcionario.setCpf(FormatadorMascaras.removerMascaraCPF(solicitacao.getCpf()));
         funcionario.setTelefone(solicitacao.getDddTelefone() + FormatadorMascaras.removerMascaraTelefone(solicitacao.getTelefone()));
      } catch (ParseException e) {
         e.printStackTrace();
      }
      this.funcionarioDao.save(funcionario);

      return new RespostaCadastrarFuncionario();
   }

   public void valida(CadastrarFuncionario solicitacao) throws NegocioException {

      if (StringUtils.isBlank(solicitacao.getNome())) {
         throw new NegocioException(ErrorCode.NOME_VAZIO);
      }
      if (solicitacao.getIdTipoFuncionario() == 0 || solicitacao.getIdTipoFuncionario() == null) {
         throw new NegocioException(ErrorCode.ID_TIPO_FUNCIONARIO);
      }
      if (StringUtils.isBlank(solicitacao.getMatricula()) &&
         StringUtils.isBlank(solicitacao.getCpf())) {
         throw new NegocioException(ErrorCode.MATRICULA_OU_CPF_VAZIO);
      }
      if (StringUtils.isBlank(solicitacao.getEmail())) {
         throw new NegocioException(ErrorCode.EMAIL_VAZIO);
      }
      if (!EmailUtils.isEmailValido(solicitacao.getEmail())) {
         throw new NegocioException(ErrorCode.EMAIL_INVALIDO);
      }
      if (StringUtils.isBlank(solicitacao.getDddTelefone())) {
         throw new NegocioException(ErrorCode.DDD_TELEFONE_VAZIO);
      }
      if (StringUtils.isBlank(solicitacao.getTelefone())) {
         throw new NegocioException(ErrorCode.TELEFONE_VAZIO);
      }

   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

}
