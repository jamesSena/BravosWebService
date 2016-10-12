package br.com.bravos.webservices.model;

/**
 * @author JamessonSena
 *
 */
public class UsuarioBean {
	private String login, senha, email, nome;
	private int idPropriedade, idPerfil;
	private boolean ativo;
	
	
	
	
	public UsuarioBean(String login, String senha, String email, String nome, int idPropriedade, int idPerfil,
			boolean ativo) {
		super();
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.nome = nome;
		this.idPropriedade = idPropriedade;
		this.idPerfil = idPerfil;
		this.ativo = ativo;
	}
	
	
	public UsuarioBean() {
		super();
	}


	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdPropriedade() {
		return idPropriedade;
	}
	public void setIdPropriedade(int idPropriedade) {
		this.idPropriedade = idPropriedade;
	}
	public int getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	@Override
	public String toString() {
		return "UsuarioBean [login=" + login + ", senha=" + senha + ", email=" + email + ", nome=" + nome
				+ ", idPropriedade=" + idPropriedade + ", idPerfil=" + idPerfil + ", ativo=" + ativo + "]";
	}



}
