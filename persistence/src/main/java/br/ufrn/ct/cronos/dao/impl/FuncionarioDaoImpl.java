package br.ufrn.ct.cronos.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.entity.Funcionario;
import dev.home.componente.dao.hibernate.impl.AbstractDao;

public class FuncionarioDaoImpl extends AbstractDao<Long, Funcionario> implements FuncionarioDao {
	
	private static final String MATRICULA = "matricula";
	private static final String NOME = "nome";
   private static final String ID_TIPO_FUNCIONARIO = "idTipoFuncionario";
	
	public FuncionarioDaoImpl() {
		super();
	}
	
	@Override
	public Funcionario getByMatricula(String matricula){
		DetachedCriteria criteria = criarDetachedCriteria();
		criteria.add(Expression.eq(FuncionarioDaoImpl.MATRICULA, matricula));
		Funcionario funcionario = (Funcionario)findUniqueByDetachedCriteria(criteria);
		return funcionario;
	}
	
	@Override
	public List<Funcionario> getAllOrdemAlfabetica(){
		DetachedCriteria criteria = criarDetachedCriteria();
		criteria.addOrder(Order.asc(FuncionarioDaoImpl.NOME));
		List<Funcionario> retorno = findByDetachedCriteria(criteria);
		return retorno;
	}

   @Override
   public List<Funcionario> findAllOrdemAlfabetica(Integer startPage, Integer maxPage) {
      DetachedCriteria criteria = criarDetachedCriteria();
      criteria.addOrder(Order.asc(FuncionarioDaoImpl.NOME));
      List<Funcionario> retorno = findByDetachedCriteria(criteria, startPage, maxPage);
      return retorno;
   }

   @Override
   public Funcionario getByNome(String nome) {
      Funcionario retorno = null;
      String sql = "select f from Funcionario f WHERE f.nome LIKE :nome";
      Query query = getSession().createQuery(sql);
      query.setString("nome", "%" + nome + "%");

      retorno = (Funcionario) query.uniqueResult();

      return retorno;
   }

   @Override
   public Funcionario getByIdSigaaFuncionario(Long idSigaaFuncionario) {
      Funcionario retorno = null;
      String sql = "select f from Funcionario f WHERE f.idSigaaFuncionario = :idSigaaFuncionario";
      Query query = getSession().createQuery(sql);
      query.setLong("idSigaaFuncionario", idSigaaFuncionario);

      retorno = (Funcionario) query.uniqueResult();

      return retorno;
   }

   @Override
   public List<Funcionario> getByTipoFuncionario(Long idTipoFuncionario, Integer startPage, Integer maxPage) {
      DetachedCriteria criteria = criarDetachedCriteria();
      criteria.add(Expression.eq(FuncionarioDaoImpl.ID_TIPO_FUNCIONARIO, idTipoFuncionario));
      criteria.addOrder(Order.asc(FuncionarioDaoImpl.NOME));
      List<Funcionario> retorno = findByDetachedCriteria(criteria, startPage, maxPage);
      return retorno;
   }

   @Override
   public Integer countByTipoFuncionario(Long idTipoFuncionario) {
      DetachedCriteria criteria = criarDetachedCriteria();
      criteria.add(Expression.eq(FuncionarioDaoImpl.ID_TIPO_FUNCIONARIO, idTipoFuncionario));
      criteria.addOrder(Order.asc(FuncionarioDaoImpl.NOME));
      List<Funcionario> retorno = findByDetachedCriteria(criteria);
      Integer total = retorno.size();
      return total;
   }

   @Override
   public List<Funcionario> getAllByTipoFuncionario(Long idTipoFuncionario) {
      DetachedCriteria criteria = criarDetachedCriteria();
      criteria.add(Expression.eq(FuncionarioDaoImpl.ID_TIPO_FUNCIONARIO, idTipoFuncionario));
      criteria.addOrder(Order.asc(FuncionarioDaoImpl.NOME));
      List<Funcionario> retorno = findByDetachedCriteria(criteria);
      return retorno;
   }

}
