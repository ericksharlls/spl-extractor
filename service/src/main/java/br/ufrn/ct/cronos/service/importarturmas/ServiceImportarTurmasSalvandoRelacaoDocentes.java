package br.ufrn.ct.cronos.service.importarturmas;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import br.ufrn.ct.adaptadorturmas.AdaptadorAPIUFRN;
import br.ufrn.ct.adaptadorturmas.DocenteDTO;
import br.ufrn.ct.adaptadorturmas.RetornaTurmasSIGAA;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.dao.TurmaDocenteDao;
import br.ufrn.ct.cronos.entity.Funcionario;
import br.ufrn.ct.cronos.entity.Periodo;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.entity.TurmaDocente;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceImportarTurmasSalvandoRelacaoDocentes implements IServiceImportarTurmas {

   private PeriodoDao periodoDao;
   private RetornaTurmasSIGAA retornaTurmasSIGAA;
   private FuncionarioDao funcionarioDao;
   private TurmaDocenteDao turmaDocenteDao;
   private TurmaDao turmaDao;

   @Override
   public void valida() throws NegocioException {

   }

   @Override
   public void processa(Integer idCentro, String clientId, String clientSecret) throws NegocioException {
      // Mapa dos Departamentos
      Map<String, Integer> departamentos = new HashMap<String, Integer>();

      departamentos.put("54", 1); // Departamento de Arquitetura
      departamentos.put("5205", 2); // Departamento de Engenharia Biomédica
      departamentos.put("52", 3); // Departamento de Engenharia Civil
      departamentos.put("56", 4); // Departamento de Engenharia de Computação e Automação
      departamentos.put("5204", 5); // Departamento de Engenharia de Comunicações
      departamentos.put("1589", 6); // Departamento de Engenharia de Materiais
      departamentos.put("5075", 7); // Departamento de Engenharia de Petróleo
      departamentos.put("112", 8); // Departamento de Engenharia Elétrica
      departamentos.put("50", 9); // Departamento de Engenharia Mecânica
      departamentos.put("115", 10); // Departamento de Engenharia Produção
      departamentos.put("57", 11); // Departamento de Engenharia Química
      departamentos.put("424", 12); // Departamento de Engenharia Têxtil
      departamentos.put("5632", 13); // Coordenação do Curso de Engenharia Mecatrônica
      // departamentos.put("", 14); // DIVERSOS

      Periodo periodo = this.periodoDao.getPeriodoLetivoAtualOuProximo();
      this.retornaTurmasSIGAA = new AdaptadorAPIUFRN(idCentro, periodo.getAno(), periodo.getNumero(), clientId, clientSecret);
      List<br.ufrn.ct.adaptadorturmas.TurmaDTO> turmasSIGAA = retornaTurmasSIGAA.retornaTurmas();

      Iterator<br.ufrn.ct.adaptadorturmas.TurmaDTO> iterator;

      /**
       * Restrigindo as turmas só para os departamentos incluidos na variavel departamentos do tipo Map<String, Integer> (Linha 28) Esse
       * procedimento é importante para eliminar, por exemplo, os departamentos q sao Pos-Graduacoes.
       */
      iterator = turmasSIGAA.iterator();
      while (iterator.hasNext()) {
         if (!departamentos.containsKey(String.valueOf(iterator.next().getIdUnidade()))) {
            iterator.remove();
         }
      }

      /**
       * Eliminando as turmas que NAO sao de graduacao. Para o CT, apenas as turmas de graduacao sao consideradas
       */
      iterator = turmasSIGAA.iterator();
      while (iterator.hasNext()) {
         if (!iterator.next().getNivel().equals("G")) {
            iterator.remove();
         }
      }

      /**
       * Eliminando as turmas AGRUPADORAS. Turmas AGRUPADORAS funcionam como turmas "mãe" de outras turmas. Apenas turmas "filhas" devem ser
       * consideradas, pois os alunos são matriculados nas "filhas", e fazem o uso da turma virtual apenas das "filhas" tambem.
       */
      iterator = turmasSIGAA.iterator();
      while (iterator.hasNext()) {
         if (iterator.next().getAgrupadora().equals(true)) {
            iterator.remove();
         }
      }

      /**
       * Removendo os relacionamentos existentes entre turmas e docentes e, em seguida, adicionando os novos relacionamentos.
       */
      for (br.ufrn.ct.adaptadorturmas.TurmaDTO turmaDTO : turmasSIGAA) {
         if (turmaDTO.getDocentesList().size() > 0) {
            Turma turma = this.turmaDao.getByIdTurmaSIGAA(new Long(turmaDTO.getId()));
            if (turma != null) {
               this.turmaDocenteDao.removerRelacionamentoDocenteComTurma(turma.getId());
               for (DocenteDTO docenteDTO : turmaDTO.getDocentesList()) {
                  TurmaDocente turmaDocente = new TurmaDocente();
                  Funcionario funcionario = this.funcionarioDao.getByIdSigaaFuncionario(docenteDTO.getIdServidor());
                  turmaDocente.setIdDocente(funcionario.getId());
                  turmaDocente.setIdTurma(turma.getId());
                  this.turmaDocenteDao.save(turmaDocente);
               }
            }
         }
      }

   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

   public void setTurmaDocenteDao(TurmaDocenteDao turmaDocenteDao) {
      this.turmaDocenteDao = turmaDocenteDao;
   }

   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }

}
