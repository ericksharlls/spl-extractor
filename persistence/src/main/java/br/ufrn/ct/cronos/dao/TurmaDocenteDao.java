package br.ufrn.ct.cronos.dao;

import java.util.List;
import br.ufrn.ct.cronos.entity.TurmaDocente;
import dev.home.componente.dao.hibernate.Dao;

public interface TurmaDocenteDao extends Dao<Long, TurmaDocente> {

   public void removerRelacionamentoDocenteComTurma(Long idTurma);

   public List<TurmaDocente> getTurmaDocentePorTurma(Long idTurma);

}
