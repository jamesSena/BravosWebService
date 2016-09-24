/**
 * 
 */
package br.com.bravos.webservices.model;

/**
 * @author JamessonSena
 *
 */
public class AreaBean {
	private String nomeArea;
	private int idOperacao,idArea,idPropriedade;

	
	/**
	 * 
	 */
	public AreaBean() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param nomeArea
	 * @param idOperacao
	 * @param idArea
	 * @param idPropriedade
	 */
	public AreaBean(String nomeArea, int idOperacao, int idArea, int idPropriedade) {
		this.nomeArea = nomeArea;
		this.idOperacao = idOperacao;
		this.idArea = idArea;
		this.idPropriedade = idPropriedade;
	}


	/**
	 * @return the nomeArea
	 */
	public String getNomeArea() {
		return nomeArea;
	}


	/**
	 * @param nomeArea the nomeArea to set
	 */
	public void setNomeArea(String nomeArea) {
		this.nomeArea = nomeArea;
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
	 * @return the idArea
	 */
	public int getIdArea() {
		return idArea;
	}


	/**
	 * @param idArea the idArea to set
	 */
	public void setIdArea(int idArea) {
		this.idArea = idArea;
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
	
	

}
