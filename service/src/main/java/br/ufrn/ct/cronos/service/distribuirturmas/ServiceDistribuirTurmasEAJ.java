package br.ufrn.ct.cronos.service.distribuirturmas;

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
import br.ufrn.ct.cronos.service.ManipuladorDatas;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.utils.date.DateUtils;

public class ServiceDistribuirTurmasEAJ implements IServiceDistribuirTurmas {

   private DisponibilidadeSalaDao disponibilidadeSalaDao;
   private TurmaDao turmaDao;
   private PeriodoDao periodoDao;
   private FeriadoDao feriadoDao;
   private HorarioDao horarioDao;

   @Override
   public void valida(Long idPeriodo, Long idPredio) throws NegocioException {
      System.out.println("!! METODO VALIDA -- Service da EAJ !!");
      if (idPeriodo == 0 || idPeriodo == null) {
         throw new NegocioException(ErrorCode.ID_PERIODO);
      }
      if (idPredio == 0 || idPredio == null) {
         throw new NegocioException(ErrorCode.ID_PREDIO);
      }
   }

   @Override
   public void processa(Long idPeriodo, Long idPredio) throws NegocioException {
      System.out.println("!! METODO PROCESSA -- Service da EAJ !!");
      Auxiliar auxiliar = new Auxiliar();
      Periodo periodo = this.periodoDao.get(idPeriodo);

      // ---------------------------------------------
      // Passo 1 na Distribuicao - Distribuir as turmas q nao sao consecutivas e q NAO EXISTIAM no semestre letivo anterior
      for (Turma t : this.turmaDao.getTurmasNaoDistribuidas(idPeriodo, idPredio)) {
         if (!this.disponibilidadeSalaDao.verificarTurmaTemSala(t.getId()) && auxiliar.validarHorarioComSabado(t.getHorario())) {
            Sala salaDisponivel = this.disponibilidadeSalaDao.getSalaDisponivelPorTurma(t);
            if (salaDisponivel != null) {

               for (int h = 0; h < auxiliar.contadorDeGruposComSabado(t.getHorario()); h++) {
                  List<Long> idsHorarios = new ArrayList<Long>(0);
                  String grupo = auxiliar.retornaGrupoComSabado(t.getHorario(), h);
                  String turno = auxiliar.retornaTurno(grupo);
                  String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);

                  for (int k = 0; k < arrayHorarios.length; k++) {
                     idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(arrayHorarios[k]))
                              .getId());
                  }
                  List<Date> feriados = this.feriadoDao.getDatasFeriadosByPeriodo(periodo.getId());
                  List<Date> datasParaReserva =
                     ManipuladorDatas.retornaDatasLetivasPorDias(DateUtils.datasEntre(periodo.getDataInicio(),
                        periodo.getDataTermino()), feriados, grupo);
                  Collections.sort(datasParaReserva, new Comparator<Date>() {

                     public int compare(Date d1, Date d2) {
                        return d1.compareTo(d2);
                     }
                  });
                  for (Long idHorario : idsHorarios) {
                     DisponibilidadeSala disponibilidadeSala = new DisponibilidadeSala();
                     disponibilidadeSala.setIdHorarioSala(idHorario);
                     disponibilidadeSala.setIdPeriodo(t.getIdPeriodo());
                     disponibilidadeSala.setIdSala(salaDisponivel.getId());
                     disponibilidadeSala.setIdTurma(t.getId());

                     for (Date data : datasParaReserva) {
                        disponibilidadeSala.setDataReserva(data);
                        this.disponibilidadeSalaDao.save(disponibilidadeSala);
                     }
                  }
               }

               t.setDistribuir(false);
               this.turmaDao.merge(t);
            }

         }
      }
   }

   public void setDisponibilidadeSalaDao(DisponibilidadeSalaDao disponibilidadeSalaDao) {
      this.disponibilidadeSalaDao = disponibilidadeSalaDao;
   }

   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

   public void setHorarioDao(HorarioDao horarioDao) {
      this.horarioDao = horarioDao;
   }

   public void setFeriadoDao(FeriadoDao feriadoDao) {
      this.feriadoDao = feriadoDao;
   }

}
