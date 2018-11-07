package br.ufrn.ct.cronos.relatoriodepartamentos.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseList;

public class RespostaRelatorioDepartamentos extends ResponseList<DadosDepartamento> {

   private static final long serialVersionUID = 1L;

   public RespostaRelatorioDepartamentos(List<DadosDepartamento> dados) {
      setDados(dados);
   }

}
