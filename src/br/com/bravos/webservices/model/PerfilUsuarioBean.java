package br.com.bravos.webservices.model;

/**
 * @author JamessonSena
 *
 */
public class PerfilUsuarioBean {
	private int idOperacao, idPerfil, tipo;
	private String descricao;

	/**
	 * 
	 */
	public PerfilUsuarioBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param idOperacao
	 * @param idPerfil
	 * @param tipo
	 * @param descricao
	 */
	public PerfilUsuarioBean(int idOperacao, int idPerfil, int tipo, String descricao) {
		this.idOperacao = idOperacao;
		this.idPerfil = idPerfil;
		this.tipo = tipo;
		this.descricao = descricao;
	}


	/**
	 * @return the idOperacao
	 */
	public int getIdOperacao() {
		return idOperacao;
	}

	/**
	 * @param idOperacao the idOperacao to set
	 */
	public void setIdOperacao(int idOperacao) {
		this.idOperacao = idOperacao;
	}

	/**
	 * @return the idPerfil
	 */
	public int getIdPerfil() {
		return idPerfil;
	}

	/**
	 * @param idPerfil the idPerfil to set
	 */
	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	/**
	 * @return the tipo
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "PerfilUsuarioBean [idOperacao=" + idOperacao + ", idPerfil=" + idPerfil + ", tipo=" + tipo
				+ ", descricao=" + descricao + "]";
	}

}
