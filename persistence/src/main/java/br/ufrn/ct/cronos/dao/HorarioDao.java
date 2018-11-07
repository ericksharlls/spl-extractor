package br.ufrn.ct.cronos.dao;

import java.util.Date;
import java.util.List;
import br.ufrn.ct.cronos.entity.Horario;
import dev.home.componente.dao.hibernate.Dao;

public interface HorarioDao extends Dao<Long, Horario>{
		
	public Horario getByHora(Date hora);

	public List<Horario> getIntervaloHorarios(Horario horarioInicial, Horario horarioFinal);

	public List<Horario> getHorariosPosteriores(Horario horario);

	public List<Horario> getHorariosAnteriores(Horario horario);

	public Horario getByTurnoEHorario(String turno, Integer horario);
	
   public List<Horario> getByTurno(String turno);

}
