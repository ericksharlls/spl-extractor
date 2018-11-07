package br.ufrn.ct.cronos.dao;

import java.util.Date;
import java.util.List;
import br.ufrn.ct.cronos.entity.Agendamento;
import br.ufrn.ct.cronos.entity.Sala;
import dev.home.componente.dao.hibernate.Dao;

public interface AgendamentoDao extends Dao<Long, Agendamento> {
   
   public List<Agendamento> getAgendamentos(Long idsala, String turno);
   public List<Sala> getSalasDisponiveisPorHorarioEData(String horario, List<Date> datasParaAgendamento);
   public List<Sala> getSalasDisponiveisPorHorarioEData(String horario, String horarioOpcional, List<Date> datasParaAgendamento);
   public boolean verificarDisponibilidadeSala(Integer dia, String turno, Integer horario, Date dataAgendamento, Long idSala);
   public void removerAgendamentosAnteriores();
   public void apagarAgendamentosPorInteressado(Agendamento agendamento);
     
   
   // Para semestre 2013.2
   public List<Sala> getSalasDisponiveisPorHorarioEData(Long idPredio, Long idPeriodo, List<Long> idsHorarios,
      List<Date> datasParaAgendamento);

   public Integer contadorAgendamentosPorPeriodoPredioInteressadoMotivoEIntervaloDatas(Integer startPage, Integer maxPage,
      Long idPeriodo, Long idPredio, String interessado, String motivo, Date dataInicial, Date dataFinal);

   public List<Agendamento> getAgendamentosPorPeriodoPredioInteressadoMotivoEIntervaloDatas(Integer startPage, Integer maxPage,
      Long idPeriodo, Long idPredio, String interessado, String motivo, Date dataInicial, Date dataFinal);

}
