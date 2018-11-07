package br.ufrn.ct.cronos.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import br.ufrn.ct.cronos.dao.HorarioDao;
import br.ufrn.ct.cronos.entity.Horario;
import dev.home.componente.dao.hibernate.impl.AbstractDao;

public class HorarioDaoImpl extends AbstractDao<Long, Horario> implements HorarioDao {
	
	public HorarioDaoImpl() {
		super();
	}
	
	@Override
	public Horario getByHora(Date hora){
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String sql = "select h from Horario h WHERE h.inicioHorario <= :hora AND h.terminoHorario >= :hora";
		
		Query query = getSession().createQuery(sql);
		query.setString("hora", formatter.format(hora));
		
		Horario retorno = (Horario) query.uniqueResult();
		return retorno;
	}
 
	@Override
	public List<Horario> getHorariosAnteriores(Horario horario){
		String sql = "select h from Horario h WHERE h.id <= :idHorario";
		List<Horario> retorno = new ArrayList<Horario>(0);
 
		Query query = getSession().createQuery(sql);
		query.setLong("idHorario", horario.getId());
		
		retorno = query.list();
		
		return retorno;
	}
 
	@Override
	public List<Horario> getHorariosPosteriores(Horario horario){
		String sql = "select h from Horario h WHERE h.id >= :idHorario";
		List<Horario> retorno = new ArrayList<Horario>(0);
		
		Query query = getSession().createQuery(sql);
		query.setLong("idHorario", horario.getId());
		
		retorno = query.list();
		
		return retorno;
	}
	
	@Override
	public List<Horario> getIntervaloHorarios(Horario horarioInicial, Horario horarioFinal){
		String sql = "select h from Horario h WHERE h.id BETWEEN :idHorarioInicial AND :idHorarioFinal";
		List<Horario> retorno = new ArrayList<Horario>(0);
		
		Query query = getSession().createQuery(sql);
		query.setLong("idHorarioInicial", horarioInicial.getId());
		query.setLong("idHorarioFinal", horarioFinal.getId());
		
		retorno = query.list();
		
		return retorno;
	}
	
	@Override
	public Horario getByTurnoEHorario(String turno, Integer horario){
		String sql = "select h from Horario h WHERE h.horario = :horario AND h.turno = :turno";
		
		Query query = getSession().createQuery(sql);
		query.setInteger("horario", horario);
		query.setString("turno", turno);
		
		Horario retorno = (Horario) query.uniqueResult();
		
		return retorno;
	}

   @Override
   public List<Horario> getByTurno(String turno) {
      String sql = "select h from Horario h WHERE h.turno = :turno";

      Query query = getSession().createQuery(sql);
      query.setString("turno", turno);

      return query.list();
   }

}

