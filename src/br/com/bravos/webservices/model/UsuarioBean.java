package br.com.bravos.webservices.model;

/**
 * @author JamessonSena
 *
 */
public class UsuarioBean extends _BeanAbstract {
	private String login, senha, email, nome, token;
	private int idUsuario, idPropriedade, idPerfil;
	private boolean ativo;
	
	/**
	 * @param success
	 * @param detail 
	 * @param reason  - codigo de erro
	 * @param login
	 * @param senha
	 * @param email
	 * @param nome
	 * @param idUsuario
	 * @param idPropriedade
	 * @param idPerfil
	 * @param ativo
	 */
	public UsuarioBean(boolean success, String detail, String reason, String login, String senha, String email,
			String nome, int idUsuario, int idPropriedade, int idPerfil, boolean ativo) {
		super(success, detail, reason);
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.nome = nome;
		this.idUsuario = idUsuario;
		this.idPropriedade = idPropriedade;
		this.idPerfil = idPerfil;
		this.ativo = ativo;
	}


	/**
	 * @param success
	 * @param detail
	 * @param reason
	 */
	public UsuarioBean(boolean success, String detail, String reason) {
		super(success, detail, reason);
	}
	
	public UsuarioBean() {
	}
	
	
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}


	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}


	/**
	 * @return
	 */
	public int getIdUsuario() {
		return idUsuario;
	}


	/**
	 * @param idUsuario
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	/**
	 * @return
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return
	 */
	public String getSenha() {
		return senha;
	}
	/**
	 * @param senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return
	 */
	public int getIdPropriedade() {
		return idPropriedade;
	}
	/**
	 * @param idPropriedade
	 */
	public void setIdPropriedade(int idPropriedade) {
		this.idPropriedade = idPropriedade;
	}
	/**
	 * @return
	 */
	public int getIdPerfil() {
		return idPerfil;
	}
	/**
	 * @param idPerfil
	 */
	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}
	/**
	 * @return
	 */
	public boolean isAtivo() {
		return ativo;
	}
	/**
	 * @param ativo
	 */
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}


	@Override
	public String toString() {
		return String.format(
				"UsuarioBean [login=%s, senha=%s, email=%s, nome=%s, idUsuario=%s, idPropriedade=%s, idPerfil=%s, ativo=%s, super()=%s]",
				login, senha, email, nome, idUsuario, idPropriedade, idPerfil, ativo, super.toString());
	}





}
