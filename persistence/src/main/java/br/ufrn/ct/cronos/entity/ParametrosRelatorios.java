package br.ufrn.ct.cronos.entity;

import java.io.Serializable;

public class ParametrosRelatorios implements Serializable {

   private static final long serialVersionUID = 1L;
   private Long id;
   private String identificador, texto;

   public ParametrosRelatorios() {
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
    * Recupera o valor do atributo identificador
    * @return o identificador
    */
   public String getIdentificador() {
      return identificador;
   }

   /**
    * Atribui o novo valor de identificador
    * @param identificador identificador que será atribuído
    */
   public void setIdentificador(String identificador) {
      this.identificador = identificador;
   }

   /**
    * Recupera o valor do atributo texto
    * @return o texto
    */
   public String getTexto() {
      return texto;
   }

   /**
    * Atribui o novo valor de texto
    * @param texto texto que será atribuído
    */
   public void setTexto(String texto) {
      this.texto = texto;
   }

}
