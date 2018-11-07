package br.ufrn.ct.cronos.service.relatoriodepartamentos;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.dao.DepartamentoDao;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaDao;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.dao.HorarioDao;
import br.ufrn.ct.cronos.dao.ParametrosRelatoriosDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.dao.TurmaDocenteDao;
import br.ufrn.ct.cronos.distribuirturmas.vo.Auxiliar;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.entity.TurmaDocente;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.relatoriodepartamentos.vo.DadosDepartamento;
import br.ufrn.ct.cronos.relatoriodepartamentos.vo.DadosTurma;
import br.ufrn.ct.cronos.relatoriodepartamentos.vo.RelatorioDepartamentos;
import br.ufrn.ct.cronos.relatoriodepartamentos.vo.RespostaRelatorioDepartamentos;
import br.ufrn.ct.cronos.service.ManipuladorNomes;
import br.ufrn.ct.cronos.service.ParametrosRelatoriosEnum;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceRelatorioDepartamentos extends AbstractService<RelatorioDepartamentos, RespostaRelatorioDepartamentos> {

   private TurmaDao turmaDao;
   private DisponibilidadeSalaDao disponibilidadeSalaDao;
   private PeriodoDao periodoDao;
   private DepartamentoDao departamentoDao;
   private ParametrosRelatoriosDao parametrosRelatoriosDao;
   private HorarioDao horarioDao;
   private TurmaDocenteDao turmaDocenteDao;
   private FuncionarioDao funcionarioDao;

   public RespostaRelatorioDepartamentos processa(RelatorioDepartamentos solicitacao) throws NegocioException {
      List<DadosDepartamento> dadosDepartamentos = new ArrayList<DadosDepartamento>(0);
      List<DadosTurma> dadosTurmas = new ArrayList<DadosTurma>(0);
      Auxiliar auxiliar = new Auxiliar();

      DadosDepartamento departamento = new DadosDepartamento();

      departamento.setPrimeiraLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.PRIMEIRA_LINHA_CABECALHO.name()).getTexto());
      departamento.setSegundaLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.SEGUNDA_LINHA_CABECALHO.name()).getTexto());
      departamento.setTerceiraLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.TERCEIRA_LINHA_CABECALHO.name()).getTexto());
      departamento.setDepartamento(this.departamentoDao.get(solicitacao.getIdDepartamento()).getNome().toUpperCase());
      departamento.setSemestre(this.periodoDao.get(solicitacao.getIdPeriodo()).getNome());
      
      List<Turma> turmasPorDepartamento =
         this.turmaDao.getTurmasPorDepartamento(solicitacao.getIdPredio(), solicitacao.getIdPeriodo(), solicitacao.getIdDepartamento());

      for (Turma turma : turmasPorDepartamento) {
         List<Sala> salas = this.disponibilidadeSalaDao.getSalasPorTurma(turma.getId());
         DadosTurma dadosTurma = new DadosTurma();

         dadosTurma.setDisciplina(turma.getCodigoDisciplina() + " - " + turma.getNomeDisciplina());
         // Linha abaixo comentada em 07/07/2017. Docente passou a ficar em uma tabela separada
         // dadosTurma.setDocente(turma.getDocente());
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
         dadosTurma.setDocente(docente);

         dadosTurma.setHorario(turma.getHorario());
         dadosTurma.setNumero(turma.getNumero());

         if (salas.size() > 0) {
            Sala[] arraySalas = (Sala[]) salas.toArray(new Sala[salas.size()]);
            dadosTurma.setLocal("");
            for (int i = 0; i < arraySalas.length; i++) {
               dadosTurma.setLocal(dadosTurma.getLocal() + arraySalas[i].getNome());
               for (int h = 0; h < auxiliar.contadorDeGruposComSabado(turma.getHorario()); h++) {
                  String grupo = auxiliar.retornaGrupoComSabado(turma.getHorario(), h);
                  String turno = auxiliar.retornaTurno(grupo);
                  String[] horariosDoGrupo = auxiliar.retornaArrayHorarios(grupo);
                  String[] diasDoGrupo = auxiliar.retornaArrayDias(grupo);
                  List<String> stringsDias = new ArrayList<String>(0);
                  for (int z = 0; z < diasDoGrupo.length; z++) {
                     stringsDias.add(diasDoGrupo[z]);
                  }
                  List<Long> idsHorarios = new ArrayList<Long>(0);
                  for (int z = 0; z < horariosDoGrupo.length; z++) {
                     idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(horariosDoGrupo[z])).getId());
                  }
                  List<String> horarios =
                     this.disponibilidadeSalaDao.getHorariosPorTurmaESala(turma, arraySalas[i], turno, idsHorarios, stringsDias);
                  String hs = "";
                  String dias = "";
                  for (int r = 0; r < horarios.size(); r++) {
                     if (!auxiliar.jaExisteHorario(hs, auxiliar.retornaHorario(horarios.get(r)))) {
                        hs += auxiliar.retornaHorario(horarios.get(r));
                     }
                     if (r == horarios.size() - 1) {
                        String dia = auxiliar.retornaDia(horarios.get(r));
                        if (!auxiliar.jaExisteDia(dias, dia)) {
                           dias += dia;
                        }
                        dadosTurma.setLocal(dadosTurma.getLocal() + " (" + dias + auxiliar.retornaTurno(horarios.get(r)) + hs + ") ");
                     } else {
                        String dia = auxiliar.retornaDia(horarios.get(r));
                        if (!auxiliar.jaExisteDia(dias, dia)) {
                           dias += dia;
                        }
                     }
                  }
               }
            }
         } else {
            dadosTurma.setLocal("INDEFINIDO");
         }

         // Sala sala = this.disponibilidadeSalaDao.getSalaPorTurma(t.getId());

         // if (sala != null)
         // dadosTurma.setLocal(sala.getNome());
         // else {
         // dadosTurma.setLocal(t.getLocal());
         // }

         dadosTurmas.add(dadosTurma);
      }

      departamento.setTurmas(dadosTurmas);
      dadosDepartamentos.add(departamento);

      return new RespostaRelatorioDepartamentos(dadosDepartamentos);
   }

   public void valida(RelatorioDepartamentos solicitacao) throws NegocioException {
      if (solicitacao.getIdPeriodo() == 0 || solicitacao.getIdPeriodo() == null) {
         throw new NegocioException(ErrorCode.ID_PERIODO);
      }
      if (solicitacao.getIdPredio() == 0 || solicitacao.getIdPredio() == null) {
         throw new NegocioException(ErrorCode.ID_PREDIO);
      }
      if (solicitacao.getIdDepartamento() == 0 || solicitacao.getIdDepartamento() == null) {
         throw new NegocioException(ErrorCode.DEPARTAMENTO_VAZIO);
      }
      if (this.disponibilidadeSalaDao.findAll().size() < 1) {
         throw new NegocioException(ErrorCode.DISTRIBUICAO_NAO_REALIZADA);
      }
   }

   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }

   public void setDisponibilidadeSalaDao(DisponibilidadeSalaDao disponibilidadeSalaDao) {
      this.disponibilidadeSalaDao = disponibilidadeSalaDao;
   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

   public void setDepartamentoDao(DepartamentoDao departamentoDao) {
      this.departamentoDao = departamentoDao;
   }

   public void setParametrosRelatoriosDao(ParametrosRelatoriosDao parametrosRelatoriosDao) {
      this.parametrosRelatoriosDao = parametrosRelatoriosDao;
   }

   public void setHorarioDao(HorarioDao horarioDao) {
      this.horarioDao = horarioDao;
   }

   public void setTurmaDocenteDao(TurmaDocenteDao turmaDocenteDao) {
      this.turmaDocenteDao = turmaDocenteDao;
   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

}
