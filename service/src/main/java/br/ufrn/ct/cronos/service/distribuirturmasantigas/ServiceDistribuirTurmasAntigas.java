package br.ufrn.ct.cronos.service.distribuirturmasantigas;

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
import br.ufrn.ct.cronos.distribuirturmasantigas.vo.DistribuirTurmasAntigas;
import br.ufrn.ct.cronos.distribuirturmasantigas.vo.RespostaDistribuirTurmasAntigas;
import br.ufrn.ct.cronos.entity.DisponibilidadeSala;
import br.ufrn.ct.cronos.entity.Periodo;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.service.ManipuladorDatas;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.utils.date.DateUtils;

public class ServiceDistribuirTurmasAntigas extends AbstractService<DistribuirTurmasAntigas, RespostaDistribuirTurmasAntigas> {

   private TurmaDao turmaDao;
   private DisponibilidadeSalaDao disponibilidadeSalaDao;
   private HorarioDao horarioDao;
   private PeriodoDao periodoDao;
   private FeriadoDao feriadoDao;

   @Override
   public RespostaDistribuirTurmasAntigas processa(DistribuirTurmasAntigas solicitacao) throws NegocioException {
      Auxiliar auxiliar = new Auxiliar();

      for (Turma turma : this.turmaDao.getAllBySalaTempIsNotNull(solicitacao.getIdPeriodo())) {
         Periodo periodo = this.periodoDao.get(turma.getIdPeriodo());
         for (int h = 0; h < auxiliar.contadorDeGruposComSabado(turma.getHorario()); h++) {
            List<Long> idsHorarios = new ArrayList<Long>(0);
            String grupo = auxiliar.retornaGrupoComSabado(turma.getHorario(), h);
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
               disponibilidadeSala.setIdPeriodo(turma.getIdPeriodo());
               disponibilidadeSala.setIdSala(turma.getIdSalaTemp());
               disponibilidadeSala.setIdTurma(turma.getId());

               for (Date data : datasParaReserva) {
                  disponibilidadeSala.setDataReserva(data);
                  this.disponibilidadeSalaDao.save(disponibilidadeSala);
               }
            }

         }
         turma.setDistribuir(false);
         this.turmaDao.merge(turma);
      }

      return new RespostaDistribuirTurmasAntigas();
   }

   @Override
   public void valida(DistribuirTurmasAntigas solicitacao) throws NegocioException {
   }

   /**
    * Atribui o novo valor de turmaDao
    * @param turmaDao turmaDao que será atribuído
    */
   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }

   /**
    * Atribui o novo valor de disponibilidadeSalaDao
    * @param disponibilidadeSalaDao disponibilidadeSalaDao que será atribuído
    */
   public void setDisponibilidadeSalaDao(DisponibilidadeSalaDao disponibilidadeSalaDao) {
      this.disponibilidadeSalaDao = disponibilidadeSalaDao;
   }

   /**
    * Atribui o novo valor de horarioDao
    * @param horarioDao horarioDao que será atribuído
    */
   public void setHorarioDao(HorarioDao horarioDao) {
      this.horarioDao = horarioDao;
   }

   /**
    * Atribui o novo valor de periodoDao
    * @param periodoDao periodoDao que será atribuído
    */
   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

   /**
    * Atribui o novo valor de feriadoDao
    * @param feriadoDao feriadoDao que será atribuído
    */
   public void setFeriadoDao(FeriadoDao feriadoDao) {
      this.feriadoDao = feriadoDao;
   }

}
