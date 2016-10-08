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
public class SensorBean {

	private int idOperacao;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSensor;
	private int idCodArea;
	private String nome, localizacao;
	/**
	 * 
	 */
	public SensorBean() {
		// TODO Auto-generated constructor stub
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
	 * @return the idSensor
	 */
	public int getIdSensor() {
		return idSensor;
	}
	/**
	 * @param idSensor the idSensor to set
	 */
	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}
	/**
	 * @return the idCodArea
	 */
	public int getIdCodArea() {
		return idCodArea;
	}
	/**
	 * @param idCodArea the idCodArea to set
	 */
	public void setIdCodArea(int idCodArea) {
		this.idCodArea = idCodArea;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
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

}
