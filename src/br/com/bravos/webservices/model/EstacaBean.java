/**
 * 
 */
package br.com.bravos.webservices.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author JamessonSena
 *
 */

@Entity
public class EstacaBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEstaca;
	private int idOperacao;
	private int idArea;
	private String nomeEstaca, localizacaoEstaca;

	/**
	 * 
	 */
	public EstacaBean() {
	}

	/**
	 * @param idOperacao
	 * @param idEstaca
	 * @param idArea
	 * @param nomeEstaca
	 * @param localizacaoEstaca
	 */
	public EstacaBean(int idOperacao, int idEstaca, int idArea, String nomeEstaca, String localizacaoEstaca) {
		this.idOperacao = idOperacao;
		this.idEstaca = idEstaca;
		this.idArea = idArea;
		this.nomeEstaca = nomeEstaca;
		this.localizacaoEstaca = localizacaoEstaca;
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
	 * @return the idEstaca
	 */
	public int getIdEstaca() {
		return idEstaca;
	}

	/**
	 * @param idEstaca the idEstaca to set
	 */
	public void setIdEstaca(int idEstaca) {
		this.idEstaca = idEstaca;
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
	 * @return the nomeEstaca
	 */
	public String getNomeEstaca() {
		return nomeEstaca;
	}

	/**
	 * @param nomeEstaca the nomeEstaca to set
	 */
	public void setNomeEstaca(String nomeEstaca) {
		this.nomeEstaca = nomeEstaca;
	}

	/**
	 * @return the localizacaoEstaca
	 */
	public String getLocalizacaoEstaca() {
		return localizacaoEstaca;
	}

	/**
	 * @param localizacaoEstaca the localizacaoEstaca to set
	 */
	public void setLocalizacaoEstaca(String localizacaoEstaca) {
		this.localizacaoEstaca = localizacaoEstaca;
	}

	@Override
	public String toString() {
		return "EstacaBean [idOperacao=" + idOperacao + ", idEstaca=" + idEstaca + ", idArea=" + idArea
				+ ", nomeEstaca=" + nomeEstaca + ", localizacaoEstaca=" + localizacaoEstaca + "]";
	}
	
	
	

}
