package br.ufrn.ct.cronos.obterfuncionario.vo;

import java.io.Serializable;

public class DadosObterFuncionario implements Serializable {

   private static final long serialVersionUID = 1L;

   private Long id;
   private String nome;
   private String matricula;
   private String email;
   private String cpf;
   private String dddTelefone;
   private String telefone;
   private Long idTipoFuncionario;

   public DadosObterFuncionario() {
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
    * Recupera o valor do atributo dddTelefone
    * @return o dddTelefone
    */
   public String getDddTelefone() {
      return dddTelefone;
   }

   /**
    * Atribui o novo valor de dddTelefone
    * @param dddTelefone dddTelefone que será atribuído
    */
   public void setDddTelefone(String dddTelefone) {
      this.dddTelefone = dddTelefone;
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
    * Recupera o valor do atributo idTipoFuncionario
    * @return o idTipoFuncionario
    */
   public Long getIdTipoFuncionario() {
      return idTipoFuncionario;
   }

   /**
    * Atribui o novo valor de idTipoFuncionario
    * @param idTipoFuncionario idTipoFuncionario que será atribuído
    */
   public void setIdTipoFuncionario(Long idTipoFuncionario) {
      this.idTipoFuncionario = idTipoFuncionario;
   }

}
