package br.ufrn.ct.cronos.service.listardepartamento;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.dao.DepartamentoDao;
import br.ufrn.ct.cronos.entity.Departamento;
import br.ufrn.ct.cronos.listardepartamento.vo.DadosListarDepartamento;
import br.ufrn.ct.cronos.listardepartamento.vo.ListarDepartamento;
import br.ufrn.ct.cronos.listardepartamento.vo.RespostaListarDepartamento;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceListarDepartamento extends AbstractService<ListarDepartamento, RespostaListarDepartamento> {

   private DepartamentoDao departamentoDao;

   public RespostaListarDepartamento processa(ListarDepartamento solicitacao) throws NegocioException {
      List<Departamento> lista = this.departamentoDao.getAllOrdemAlfabetica();
      List<DadosListarDepartamento> dadosListarDepartamento = new ArrayList<DadosListarDepartamento>(lista.size());
      for (Departamento departamento : lista) {
         DadosListarDepartamento dados = new DadosListarDepartamento();
         dados.setId(departamento.getId());
         dados.setNome(departamento.getNome());
         dadosListarDepartamento.add(dados);
      }
      return new RespostaListarDepartamento(dadosListarDepartamento);
   }

   public void valida(ListarDepartamento solicitacao) throws NegocioException {

   }

   public void setDepartamentoDao(DepartamentoDao departamentoDao) {
      this.departamentoDao = departamentoDao;
   }

}
