
package br.ufrn.ct.cronos.consultarfuncionario.vo;

import java.io.Serializable;

public class DadosConsultarFuncionario implements Serializable {

   private static final long serialVersionUID = 1L;

   private Long id;
   private String nome;
   private String email;
   private String matricula;
   private String telefone;
   private String cpf;
   private String tipoFuncionario;

   public DadosConsultarFuncionario() {
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
    * Recupera o valor do atributo nome
    * @return o nome
    */
   public String getNome() {
      return nome;
   }

   /**
    * Atribui o novo valor de nome
    * @param nome nome que será atribuído
    */
   public void setNome(String nome) {
      this.nome = nome;
   }

   /**
    * Recupera o valor do atributo email
    * @return o email
    */
   public String getEmail() {
      return email;
   }

   /**
    * Atribui o novo valor de email
    * @param email email que será atribuído
    */
   public void setEmail(String email) {
      this.email = email;
   }

   /**
    * Recupera o valor do atributo matricula
    * @return o matricula
    */
   public String getMatricula() {
      return matricula;
   }

   /**
    * Atribui o novo valor de matricula
    * @param matricula matricula que será atribuído
    */
   public void setMatricula(String matricula) {
      this.matricula = matricula;
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
    * Recupera o valor do atributo cpf
    * @return o cpf
    */
   public String getCpf() {
      return cpf;
   }

   /**
    * Atribui o novo valor de cpf
    * @param cpf cpf que será atribuído
    */
   public void setCpf(String cpf) {
      this.cpf = cpf;
   }

   /**
    * Recupera o valor do atributo tipoFuncionario
    * @return o tipoFuncionario
    */
   public String getTipoFuncionario() {
      return tipoFuncionario;
   }

   /**
    * Atribui o novo valor de tipoFuncionario
    * @param tipoFuncionario tipoFuncionario que será atribuído
    */
   public void setTipoFuncionario(String tipoFuncionario) {
      this.tipoFuncionario = tipoFuncionario;
   }

}
