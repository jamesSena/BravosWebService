package br.com.bravos.webservices.model;

/**
 * @author JamessonSena
 *
 */
public class UsuarioLoginBean {
	private String usuario, email, senha;

	/**
	 * 
	 */
	public UsuarioLoginBean() {
		super();
	}

	/**
	 * @param usuario
	 * @param email
	 * @param senha
	 */
	public UsuarioLoginBean(String usuario, String email, String senha) {
		super();
		this.usuario = usuario;
		this.email = email;
		this.senha = senha;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Login [usuario=" + usuario + ", email=" + email + ", senha=" + senha + "]";
	}

	
}
