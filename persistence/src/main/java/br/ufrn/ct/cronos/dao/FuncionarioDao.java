package br.ufrn.ct.cronos.dao;

import java.util.List;
import br.ufrn.ct.cronos.entity.Funcionario;
import dev.home.componente.dao.hibernate.Dao;

public interface FuncionarioDao extends Dao<Long, Funcionario>{
	
	public Funcionario getByMatricula(String matricula);
	
	public List<Funcionario> getAllOrdemAlfabetica();
	
   public List<Funcionario> findAllOrdemAlfabetica(Integer startPage, Integer maxPage);

   public Funcionario getByNome(String nome);

   public Funcionario getByIdSigaaFuncionario(Long idSigaaFuncionario);

   public List<Funcionario> getByTipoFuncionario(Long idTipoFuncionario, Integer startPage, Integer maxPage);

   public Integer countByTipoFuncionario(Long idTipoFuncionario);

   public List<Funcionario> getAllByTipoFuncionario(Long idTipoFuncionario);

}
