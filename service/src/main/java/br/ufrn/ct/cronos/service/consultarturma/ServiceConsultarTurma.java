
package br.ufrn.ct.cronos.service.consultarturma;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.consultarturma.vo.ConsultarTurma;
import br.ufrn.ct.cronos.consultarturma.vo.DadosConsultarTurma;
import br.ufrn.ct.cronos.consultarturma.vo.RespostaConsultarTurma;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.dao.PerfilSalaTurmaDao;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.dao.TurmaDocenteDao;
import br.ufrn.ct.cronos.entity.PerfilSalaTurma;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.entity.TurmaDocente;
import br.ufrn.ct.cronos.service.ManipuladorNomes;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceConsultarTurma extends AbstractService<ConsultarTurma, RespostaConsultarTurma> {

   private TurmaDao turmaDao;
   private PerfilSalaTurmaDao perfilSalaTurmaDao;
   private TurmaDocenteDao turmaDocenteDao;
   private FuncionarioDao funcionarioDao;

   public RespostaConsultarTurma processa(ConsultarTurma solicitacao) throws NegocioException {
      List<Turma> lista =
         this.turmaDao
                  .getTurmasPorDepartamentoEPeriodoOrderByNomeDisciplina(solicitacao.getStartPage(), solicitacao.getMaxPage(),
                     solicitacao.getIdDepartamento(), solicitacao.getIdPeriodo());

      Integer totalNumeroLinhas =
         this.turmaDao.contadorTurmasPorDepartamentoEPeriodo(solicitacao.getIdDepartamento(), solicitacao.getIdPeriodo());

      List<DadosConsultarTurma> dadosConsultarTurma = new ArrayList<DadosConsultarTurma>(lista.size());

      for (Turma turma : lista) {
         DadosConsultarTurma dados = new DadosConsultarTurma();
         dados.setId(turma.getId());
         // Linha abaixo comentada em 07/07/2017. Docente passou a ficar em uma tabela separada
         // dados.setDocente(turma.getDocente());

         List<TurmaDocente> listaTurmaDocente = this.turmaDocenteDao.getTurmaDocentePorTurma(turma.getId());
         String docente = "";
         if (listaTurmaDocente.isEmpty()) {
            docente = "A DEFINIR DOCENTE";
         } else if (listaTurmaDocente.size() == 1) {
            for (TurmaDocente td : listaTurmaDocente) {
               docente += this.funcionarioDao.get(td.getIdDocente()).getNome() + " ";
            }
         } else {
            int contador = listaTurmaDocente.size();
            for (TurmaDocente td : listaTurmaDocente) {
               contador--;
               if (contador > 0) {
                  docente += ManipuladorNomes.abreviaNome(this.funcionarioDao.get(td.getIdDocente()).getNome()) + ", ";
               } else {
                  docente += ManipuladorNomes.abreviaNome(this.funcionarioDao.get(td.getIdDocente()).getNome());
               }
            }
         }
         dados.setDocente(docente);

         dados.setHorario(turma.getHorario());
         dados.setDistribuir(turma.getDistribuir());
         dados.setNomeDisciplina(turma.getCodigoDisciplina() + " - " + turma.getNomeDisciplina());
         dados.setLocal(turma.getLocal());
         dados.setIdPredio(turma.getIdPredio());
         dados.setNumero(turma.getNumero());

         PerfilSalaTurma perfil = this.perfilSalaTurmaDao.get(turma.getIdTipo());
         dados.setTipoTurma(perfil.getNome());
         dados.setIdTipo(perfil.getId());

         dadosConsultarTurma.add(dados);
      }
      return new RespostaConsultarTurma(dadosConsultarTurma, totalNumeroLinhas);
   }

   public void valida(ConsultarTurma solicitacao) throws NegocioException {

   }

   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }

   public void setPerfilSalaTurmaDao(PerfilSalaTurmaDao perfilSalaTurmaDao) {
      this.perfilSalaTurmaDao = perfilSalaTurmaDao;
   }

   public void setTurmaDocenteDao(TurmaDocenteDao turmaDocenteDao) {
      this.turmaDocenteDao = turmaDocenteDao;
   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

}
