
package br.ufrn.ct.cronos.service.consultarfuncionario;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import br.ufrn.ct.cronos.consultarfuncionario.vo.ConsultarFuncionario;
import br.ufrn.ct.cronos.consultarfuncionario.vo.DadosConsultarFuncionario;
import br.ufrn.ct.cronos.consultarfuncionario.vo.RespostaConsultarFuncionario;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.dao.TipoFuncionarioDao;
import br.ufrn.ct.cronos.entity.Funcionario;
import br.ufrn.ct.cronos.entity.TipoFuncionario;
import br.ufrn.ct.cronos.service.FormatadorMascaras;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceConsultarFuncionario extends AbstractService<ConsultarFuncionario, RespostaConsultarFuncionario> {

   private FuncionarioDao funcionarioDao;
   private TipoFuncionarioDao tipoFuncionarioDao;

   public RespostaConsultarFuncionario processa(ConsultarFuncionario solicitacao) throws NegocioException {
      // List<Funcionario> lista = this.funcionarioDao.findAllOrdemAlfabetica(solicitacao.getStartPage(), solicitacao.getMaxPage());
      List<Funcionario> lista =
         this.funcionarioDao.getByTipoFuncionario(solicitacao.getIdTipoFuncionario(), solicitacao.getStartPage(), solicitacao.getMaxPage());
      // Integer totalNumeroLinhas = this.funcionarioDao.count();
      Integer totalNumeroLinhas = this.funcionarioDao.countByTipoFuncionario(solicitacao.getIdTipoFuncionario());
      List<DadosConsultarFuncionario> dadosConsultarFuncionario = new ArrayList<DadosConsultarFuncionario>(lista.size());

      for (Funcionario funcionario : lista) {
         DadosConsultarFuncionario dados = new DadosConsultarFuncionario();
         dados.setId(funcionario.getId());
         dados.setEmail(funcionario.getEmail());
         dados.setNome(funcionario.getNome());
         dados.setMatricula(funcionario.getMatricula());

         try {
            if (StringUtils.isBlank(funcionario.getTelefone())) {
               dados.setTelefone("");
            } else {
               dados.setTelefone(FormatadorMascaras.aplicarMascaraTelefoneComDDD(funcionario.getTelefone()));
            }
            if (StringUtils.isBlank(funcionario.getCpf())) {
               dados.setCpf("");
            } else {
               dados.setCpf(FormatadorMascaras.aplicarMascaraCPF(funcionario.getCpf()));
            }
         } catch (ParseException e) {
            e.printStackTrace();
         }
         TipoFuncionario tipoFuncionario = this.tipoFuncionarioDao.get(funcionario.getIdTipoFuncionario());
         dados.setTipoFuncionario(tipoFuncionario.getNome());

         dadosConsultarFuncionario.add(dados);
      }

      return new RespostaConsultarFuncionario(dadosConsultarFuncionario, totalNumeroLinhas);
   }

   public void valida(ConsultarFuncionario solicitacao) throws NegocioException {

   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

   public void setTipoFuncionarioDao(TipoFuncionarioDao tipoFuncionarioDao) {
      this.tipoFuncionarioDao = tipoFuncionarioDao;
   }
}
