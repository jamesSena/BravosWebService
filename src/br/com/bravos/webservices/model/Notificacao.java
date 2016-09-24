/**
 * 
 */
package br.com.bravos.webservices.model;

/**
 * @author JamessonSena
 *
 */
public class Notificacao {
	private int idOperacao,	idNotificacao, idSensor, idPropriedade,idArea, idStatus;

	/**
	 * 
	 */
	public Notificacao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param idOperacao
	 * @param idNotificacao
	 * @param idSensor
	 * @param idPropriedade
	 * @param idArea
	 * @param idStatus
	 */
	public Notificacao(int idOperacao, int idNotificacao, int idSensor, int idPropriedade, int idArea, int idStatus) {
		this.idOperacao = idOperacao;
		this.idNotificacao = idNotificacao;
		this.idSensor = idSensor;
		this.idPropriedade = idPropriedade;
		this.idArea = idArea;
		this.idStatus = idStatus;
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
	 * @return the idNotificacao
	 */
	public int getIdNotificacao() {
		return idNotificacao;
	}

	/**
	 * @param idNotificacao the idNotificacao to set
	 */
	public void setIdNotificacao(int idNotificacao) {
		this.idNotificacao = idNotificacao;
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
	 * @return the idStatus
	 */
	public int getIdStatus() {
		return idStatus;
	}

	/**
	 * @param idStatus the idStatus to set
	 */
	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	@Override
	public String toString() {
		return "Notificacao [idOperacao=" + idOperacao + ", idNotificacao=" + idNotificacao + ", idSensor=" + idSensor
				+ ", idPropriedade=" + idPropriedade + ", idArea=" + idArea + ", idStatus=" + idStatus + "]";
	}
	
	
}
