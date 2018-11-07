package br.ufrn.ct.cronos.consultarusuario.vo;

import java.io.Serializable;

public class DadosConsultarUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String login;
	private String nomeFuncionario;
	private String perfil;
	private Boolean ativo;
	
	public DadosConsultarUsuario(){}
	
	public DadosConsultarUsuario(Long id, String login, String nomeFuncionario,
			String perfil, Boolean ativo) {
		super();
		this.id = id;
		this.login = login;
		this.nomeFuncionario = nomeFuncionario;
		this.perfil = perfil;
		this.ativo = ativo;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	
	public String getPerfil() {
		return perfil;
	}
	
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
