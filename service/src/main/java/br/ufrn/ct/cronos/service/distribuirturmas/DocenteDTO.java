package br.ufrn.ct.cronos.service.distribuirturmas;

import java.io.Serializable;


public class DocenteDTO implements Serializable {

   private static final long serialVersionUID = 1L;

   private String nome;
   private Long idTurma, chDedicada;

   public DocenteDTO() {
      super();
   }

   public String getNome() {
      return nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public Long getIdTurma() {
      return idTurma;
   }

   public void setIdTurma(Long idTurma) {
      this.idTurma = idTurma;
   }

   public Long getChDedicada() {
      return chDedicada;
   }

   public void setChDedicada(Long chDedicada) {
      this.chDedicada = chDedicada;
   }

}
