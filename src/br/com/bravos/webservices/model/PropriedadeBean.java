/**
 * 
 */
package br.com.bravos.webservices.model;

/**
 * @author JamessonSena
 *
 */
public class PropriedadeBean {
	private int idOperacao,	idPropriedade;
	private String nomePropriedade, responsavel, emailResponsavel, localizacao;

	/**
	 * 
	 */
	public PropriedadeBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param idOperacao
	 * @param idPropriedade
	 * @param nomePropriedade
	 * @param responsavel
	 * @param emailResponsavel
	 * @param localizacao
	 */
	public PropriedadeBean(int idOperacao, int idPropriedade, String nomePropriedade, String responsavel,
			String emailResponsavel, String localizacao) {
		this.idOperacao = idOperacao;
		this.idPropriedade = idPropriedade;
		this.nomePropriedade = nomePropriedade;
		this.responsavel = responsavel;
		this.emailResponsavel = emailResponsavel;
		this.localizacao = localizacao;
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
	 * @return the idPropriedade
	 */
	public int getIdPropriedade() {
		return idPropriedade;
	}

	/**
	 * @param idPropriedade the idPropriedade to set
	 */
	public void setIdPropriedade(int idPropriedade) {
		this.idPropriedade = idPropriedade;
	}

	/**
	 * @return the nomePropriedade
	 */
	public String getNomePropriedade() {
		return nomePropriedade;
	}

	/**
	 * @param nomePropriedade the nomePropriedade to set
	 */
	public void setNomePropriedade(String nomePropriedade) {
		this.nomePropriedade = nomePropriedade;
	}

	/**
	 * @return the responsavel
	 */
	public String getResponsavel() {
		return responsavel;
	}

	/**
	 * @param responsavel the responsavel to set
	 */
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	/**
	 * @return the emailResponsavel
	 */
	public String getEmailResponsavel() {
		return emailResponsavel;
	}

	/**
	 * @param emailResponsavel the emailResponsavel to set
	 */
	public void setEmailResponsavel(String emailResponsavel) {
		this.emailResponsavel = emailResponsavel;
	}

	/**
	 * @return the localizacao
	 */
	public String getLocalizacao() {
		return localizacao;
	}

	/**
	 * @param localizacao the localizacao to set
	 */
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	@Override
	public String toString() {
		return "PropriedadeBean [idOperacao=" + idOperacao + ", idPropriedade=" + idPropriedade + ", nomePropriedade="
				+ nomePropriedade + ", responsavel=" + responsavel + ", emailResponsavel=" + emailResponsavel
				+ ", localizacao=" + localizacao + "]";
	}

}
