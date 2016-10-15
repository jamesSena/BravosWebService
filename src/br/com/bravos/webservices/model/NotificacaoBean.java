/**
 * 
 */
package br.com.bravos.webservices.model;

import java.util.Date;

/**
 * @author JamessonSena
 *
 */
public class NotificacaoBean extends _BeanAbstract{
	private int idUsuario, idSensor, idArea, idPropriedade, idStatus, idNotificacao;
	private Date dataInicio, dataFim;
	public NotificacaoBean(int idUsuario, int idSensor, int idArea, int idPropriedade, int idStatus, int idNotificacao,
			Date dataInicio, Date dataFim) {
		super();
		this.idUsuario = idUsuario;
		this.idSensor = idSensor;
		this.idArea = idArea;
		this.idPropriedade = idPropriedade;
		this.idStatus = idStatus;
		this.idNotificacao = idNotificacao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}
	public NotificacaoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NotificacaoBean(boolean success, String detail, String reason) {
		super(success, detail, reason);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the idUsuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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
	 * @return the dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}
	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	/**
	 * @return the dataFim
	 */
	public Date getDataFim() {
		return dataFim;
	}
	/**
	 * @param dataFim the dataFim to set
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	


}