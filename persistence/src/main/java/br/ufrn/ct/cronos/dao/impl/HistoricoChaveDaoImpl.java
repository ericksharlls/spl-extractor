package br.ufrn.ct.cronos.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.Query;
import br.ufrn.ct.cronos.dao.HistoricoChaveDao;
import br.ufrn.ct.cronos.entity.HistoricoChave;
import dev.home.componente.dao.hibernate.impl.AbstractDao;

public class HistoricoChaveDaoImpl extends AbstractDao<Long, HistoricoChave> implements HistoricoChaveDao {
	
	public HistoricoChaveDaoImpl() {
		super();
	}

	@Override
	public HistoricoChave getUltimaOcorrenciaChave(Long idChave){
      // String sql =
      // "select hc from HistoricoChave hc WHERE hc.horaRealizacao = (select MAX(hc.horaRealizacao) from HistoricoChave hc WHERE hc.idChave = :idChave)";
      String sql = "select hc from HistoricoChave hc WHERE hc.idChave = :idChave ORDER BY hc.horaRealizacao DESC";
		Query query = getSession().createQuery(sql);
		
		query.setLong("idChave", idChave);
      query.setFirstResult(0);
      query.setMaxResults(1);

		HistoricoChave retorno = (HistoricoChave) query.uniqueResult();

		return retorno;
	}
	
	@Override
	public List<HistoricoChave> getEntradasPorPeriodoEChave(Date dataInicial, Date dataFinal, Long idChave){
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String sql = "select hc from HistoricoChave hc WHERE hc.horaRealizacao BETWEEN :dataInicial AND :dataFinal AND hc.idChave = :idChave AND hc.idOperacao = 1";
		List<HistoricoChave> retorno = new ArrayList<HistoricoChave>();
		
		Query query = getSession().createQuery(sql);
		query.setString("dataInicial", formatter.format(dataInicial));
		query.setString("dataFinal", formatter.format(dataFinal));
		query.setLong("idChave", idChave);
		retorno = query.list();
		
		return retorno;
	}
	
	@Override
	public HistoricoChave getUltimaSaidaPorEntrada(HistoricoChave historicoChave){
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String sql = "select hc from HistoricoChave hc WHERE hc.horaRealizacao = (select MAX(hc.horaRealizacao) from HistoricoChave hc WHERE hc.idChave = :idChave AND hc.horaRealizacao < :horaRealizacaoOperacao AND hc.idOperacao = :idOperacao)";
		
		Query query = getSession().createQuery(sql);
		
		query.setLong("idChave", historicoChave.getIdChave());
		query.setString("horaRealizacaoOperacao", formatter.format(historicoChave.getHoraRealizacao()));
		query.setLong("idOperacao", new Long(2));
		
		HistoricoChave retorno = (HistoricoChave) query.uniqueResult();
		
		return retorno;
	}
	
	@Override
	public boolean existeOcorrenciaChave(Long idChave){
		String sql = "select count(hc.id) from HistoricoChave hc WHERE hc.idChave = :idChave";
		Query query = getSession().createQuery(sql);
		
		query.setLong("idChave", idChave);
		
		Long total = (Long) query.uniqueResult();
		if(total > 0){
			return true;
		}
		return false;
	}

   @Override
   public List<HistoricoChave> getHistoricoChavePorData(Date data) {
      List<HistoricoChave> retorno = new ArrayList<HistoricoChave>(0);
      DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Calendar dataInicial = new GregorianCalendar();
      Calendar dataFinal = new GregorianCalendar();
      dataInicial.setTime(data);
      dataFinal.setTime(data);
      dataInicial.set(Calendar.HOUR_OF_DAY, 0);
      dataInicial.set(Calendar.MINUTE, 0);
      dataInicial.set(Calendar.SECOND, 0);
      dataFinal.set(Calendar.HOUR_OF_DAY, 23);
      dataFinal.set(Calendar.MINUTE, 59);
      dataFinal.set(Calendar.SECOND, 59);
      
      String sql = "select hc from HistoricoChave hc WHERE hc.horaRealizacao BETWEEN :dataInicial AND :dataFinal ORDER BY hc.idChave";
      Query query = getSession().createQuery(sql);
      formatter.setCalendar(dataInicial);
      query.setString("dataInicial", formatter.format(dataInicial.getTime()));
      formatter.setCalendar(dataFinal);
      query.setString("dataFinal", formatter.format(dataFinal.getTime()));
      retorno = query.list();
      return retorno;
   }

   @Override
   public List<HistoricoChave> getHistoricoChavePorDataESala(Date data, Long idSala) {
      List<HistoricoChave> retorno = new ArrayList<HistoricoChave>(0);
      DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Calendar dataInicial = new GregorianCalendar();
      Calendar dataFinal = new GregorianCalendar();
      dataInicial.setTime(data);
      dataFinal.setTime(data);
      dataInicial.set(Calendar.HOUR_OF_DAY, 0);
      dataInicial.set(Calendar.MINUTE, 0);
      dataInicial.set(Calendar.SECOND, 0);
      dataFinal.set(Calendar.HOUR_OF_DAY, 23);
      dataFinal.set(Calendar.MINUTE, 59);
      dataFinal.set(Calendar.SECOND, 59);

      String sql =
         "select hc from HistoricoChave hc WHERE hc.idChave IN (select c.id from Chave c WHERE c.idSala = :idSala) AND hc.horaRealizacao BETWEEN :dataInicial AND :dataFinal ORDER BY hc.idChave";
      Query query = getSession().createQuery(sql);
      query.setLong("idSala", idSala);
      formatter.setCalendar(dataInicial);
      query.setString("dataInicial", formatter.format(dataInicial.getTime()));
      formatter.setCalendar(dataFinal);
      query.setString("dataFinal", formatter.format(dataFinal.getTime()));
      retorno = query.list();
      return retorno;
   }
	
}
