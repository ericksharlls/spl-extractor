
package br.ufrn.ct.cronos.obteragendamento.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DadosObterAgendamento implements Serializable {

   private static final long serialVersionUID = 1L;
   private Long id;
   private String interessado, telefone, motivo, sala;
   private List<String> datas = new ArrayList<String>(0);

   public DadosObterAgendamento() {
      super();
   }

   /**
    * Recupera o valor do atributo id
    * @return o id
    */
   public Long getId() {
      return id;
   }

   /**
    * Atribui o novo valor de id
    * @param id id que será atribuído
    */
   public void setId(Long id) {
      this.id = id;
   }

   /**
    * Recupera o valor do atributo interessado
    * @return o interessado
    */
   public String getInteressado() {
      return interessado;
   }

   /**
    * Atribui o novo valor de interessado
    * @param interessado interessado que será atribuído
    */
   public void setInteressado(String interessado) {
      this.interessado = interessado;
   }

   /**
    * Recupera o valor do atributo telefone
    * @return o telefone
    */
   public String getTelefone() {
      return telefone;
   }

   /**
    * Atribui o novo valor de telefone
    * @param telefone telefone que será atribuído
    */
   public void setTelefone(String telefone) {
      this.telefone = telefone;
   }

   /**
    * Recupera o valor do atributo motivo
    * @return o motivo
    */
   public String getMotivo() {
      return motivo;
   }

   /**
    * Atribui o novo valor de motivo
    * @param motivo motivo que será atribuído
    */
   public void setMotivo(String motivo) {
      this.motivo = motivo;
   }

   /**
    * Recupera o valor do atributo sala
    * @return o sala
    */
   public String getSala() {
      return sala;
   }

   /**
    * Atribui o novo valor de sala
    * @param sala sala que será atribuído
    */
   public void setSala(String sala) {
      this.sala = sala;
   }

   /**
    * Recupera o valor do atributo datas
    * @return o datas
    */
   public List<String> getDatas() {
      return datas;
   }

   /**
    * Atribui o novo valor de datas
    * @param datas datas que será atribuído
    */
   public void setDatas(List<String> datas) {
      this.datas = datas;
   }
}
