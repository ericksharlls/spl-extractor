
package br.ufrn.ct.cronos.service.permutarturma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaDao;
import br.ufrn.ct.cronos.dao.FeriadoDao;
import br.ufrn.ct.cronos.dao.HorarioDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.distribuirturmas.vo.Auxiliar;
import br.ufrn.ct.cronos.entity.DisponibilidadeSala;
import br.ufrn.ct.cronos.entity.Periodo;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.permutarturma.vo.PermutarTurma;
import br.ufrn.ct.cronos.permutarturma.vo.RespostaPermutarTurma;
import br.ufrn.ct.cronos.service.ManipuladorDatas;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.utils.date.DateUtils;

public class ServicePermutarTurma extends AbstractService<PermutarTurma, RespostaPermutarTurma> {

   private TurmaDao turmaDao;
   private DisponibilidadeSalaDao disponibilidadeSalaDao;
   private HorarioDao horarioDao;
   private FeriadoDao feriadoDao;
   private PeriodoDao periodoDao;

   public RespostaPermutarTurma processa(PermutarTurma solicitacao) throws NegocioException {
      Auxiliar auxiliar = new Auxiliar();
      Turma t1 = this.turmaDao.get(solicitacao.getIdTurma1());
      Turma t2 = this.turmaDao.get(solicitacao.getIdTurma2());

      Sala s1 = this.disponibilidadeSalaDao.getSalaPorTurma(solicitacao.getIdTurma1());
      Sala s2 = this.disponibilidadeSalaDao.getSalaPorTurma(solicitacao.getIdTurma2());

      Periodo periodo = this.periodoDao.get(t1.getIdPeriodo());

      for (DisponibilidadeSala d : this.disponibilidadeSalaDao.getDisponibilidadesSalaPorTurma(t1)) {
         this.disponibilidadeSalaDao.delete(d);
      }

      for (DisponibilidadeSala d : this.disponibilidadeSalaDao.getDisponibilidadesSalaPorTurma(t2)) {
         this.disponibilidadeSalaDao.delete(d);
      }
      
      for (int h = 0; h < auxiliar.contadorDeGruposComSabado(t1.getHorario()); h++) {
         List<Long> idsHorarios = new ArrayList<Long>(0);
         String grupo = auxiliar.retornaGrupoComSabado(t1.getHorario(), h);
         String turno = auxiliar.retornaTurno(grupo);
         String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);

         for (int k = 0; k < arrayHorarios.length; k++) {
            idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(arrayHorarios[k])).getId());
         }

         List<Date> feriados = this.feriadoDao.getDatasFeriadosByPeriodo(periodo.getId());
         List<Date> datasParaReserva = ManipuladorDatas.retornaDatasLetivasPorDias(DateUtils.datasEntre(periodo.getDataInicio(),
            periodo.getDataTermino()), feriados, grupo);

         Collections.sort(datasParaReserva, new Comparator<Date>() {

            public int compare(Date d1, Date d2) {
               return d1.compareTo(d2);
            }
         });

         for (Long idHorario : idsHorarios) {
            DisponibilidadeSala disponibilidadeSala = new DisponibilidadeSala();
            disponibilidadeSala.setIdHorarioSala(idHorario);
            disponibilidadeSala.setIdPeriodo(t1.getIdPeriodo());
            disponibilidadeSala.setIdSala(s2.getId());
            disponibilidadeSala.setIdTurma(t1.getId());

            for (Date data : datasParaReserva) {
               disponibilidadeSala.setDataReserva(data);
               this.disponibilidadeSalaDao.save(disponibilidadeSala);
            }
         }

      }

      for (int h = 0; h < auxiliar.contadorDeGruposComSabado(t2.getHorario()); h++) {
         List<Long> idsHorarios = new ArrayList<Long>(0);
         String grupo = auxiliar.retornaGrupoComSabado(t2.getHorario(), h);
         String turno = auxiliar.retornaTurno(grupo);
         String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);

         for (int k = 0; k < arrayHorarios.length; k++) {
            idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(arrayHorarios[k])).getId());
         }

         List<Date> feriados = this.feriadoDao.getDatasFeriadosByPeriodo(periodo.getId());
         List<Date> datasParaReserva = ManipuladorDatas.retornaDatasLetivasPorDias(DateUtils.datasEntre(periodo.getDataInicio(),
            periodo.getDataTermino()), feriados, grupo);

         Collections.sort(datasParaReserva, new Comparator<Date>() {
            public int compare(Date d1, Date d2) {
               return d1.compareTo(d2);
            }
         });

         for (Long idHorario : idsHorarios) {
            DisponibilidadeSala disponibilidadeSala = new DisponibilidadeSala();
            disponibilidadeSala.setIdHorarioSala(idHorario);
            disponibilidadeSala.setIdPeriodo(t2.getIdPeriodo());
            disponibilidadeSala.setIdSala(s1.getId());
            disponibilidadeSala.setIdTurma(t2.getId());

            for (Date data : datasParaReserva) {
               disponibilidadeSala.setDataReserva(data);
               this.disponibilidadeSalaDao.save(disponibilidadeSala);
            }
         }

      }

      return new RespostaPermutarTurma();
   }

   public void valida(PermutarTurma solicitacao) throws NegocioException {
      Turma t1 = this.turmaDao.get(solicitacao.getIdTurma1());
      Turma t2 = this.turmaDao.get(solicitacao.getIdTurma2());
      if (!t1.getHorario().equals(t2.getHorario())) {
         throw new NegocioException(ErrorCode.HORARIOS_NAO_IGUAIS);
      }
      if (!t1.getIdTipo().equals(t2.getIdTipo())) {
         throw new NegocioException(ErrorCode.PERFIS_DIFERENTES);
      }
      if (t1.getId().equals(t2.getId())) {
         throw new NegocioException(ErrorCode.TURMAS_IGUAIS);
      }
   }

   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }

   public void setDisponibilidadeSalaDao(DisponibilidadeSalaDao disponibilidadeSalaDao) {
      this.disponibilidadeSalaDao = disponibilidadeSalaDao;
   }

   public void setHorarioDao(HorarioDao horarioDao) {
      this.horarioDao = horarioDao;
   }

   public void setFeriadoDao(FeriadoDao feriadoDao) {
      this.feriadoDao = feriadoDao;
   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }
}
