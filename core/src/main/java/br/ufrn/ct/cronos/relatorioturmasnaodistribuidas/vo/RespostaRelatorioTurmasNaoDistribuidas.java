package br.ufrn.ct.cronos.relatorioturmasnaodistribuidas.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseList;

public class RespostaRelatorioTurmasNaoDistribuidas extends ResponseList<DadosDepartamento> {

   private static final long serialVersionUID = 1L;

   public RespostaRelatorioTurmasNaoDistribuidas(List<DadosDepartamento> dados) {
      setDados(dados);
   }

}
