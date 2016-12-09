/**
 * 
 */
package br.com.bravos.webservices.model;

import java.util.List;

import br.com.bravos.webservices.filtro.Token;

/**
 * @author JamessonSena
 *
 */
public class SensorBean extends _BeanAbstract {

	private int idSensor,idCodArea, idUsuario,iDPropriedade;
	private String nome, longitude, latitude, Data, nomeAreaAssociada;
	private List<AreaBean> areaList; 

	
	
	public List<AreaBean> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<AreaBean> areaList) {
		this.areaList = areaList;
	}

	/**
	 * @param success
	 * @param detail
	 * @param reason
	 */
	public SensorBean(boolean success, String detail, String reason) {
		super(success, detail, reason);
	}

	public SensorBean() {
		this.setToken(new Token().gerarToken(idUsuario));
	}
	public SensorBean(String token) {
		if(token.isEmpty()){this.setToken(token);return;}
		this.setToken(new Token().gerarToken(idUsuario));

	}
	/**
	 * @param success
	 * @param detail
	 * @param reason
	 * @param idOperacao
	 * @param idSensor
	 * @param idCodArea
	 * @param idUsuario
	 * @param nome
	 * @param longitude
	 * @param latitude
	 */
	public SensorBean(boolean success, String detail, String reason, int idSensor, int idCodArea,
			int idUsuario, String nome, String longitude, String latitude) {
		super(success, detail, reason);
	
		this.idSensor = idSensor;
		this.idCodArea = idCodArea;
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	
	
	
	
	
	
	
	
	
	public String getNomeAreaAssociada() {
		return nomeAreaAssociada;
	}

	public void setNomeAreaAssociada(String nomeAreaAssociada) {
		this.nomeAreaAssociada = nomeAreaAssociada;
	}

	/**
	 * @return the iDPropriedade
	 */
	public int getiDPropriedade() {
		return iDPropriedade;
	}


	/**
	 * @param iDPropriedade the iDPropriedade to set
	 */
	public void setiDPropriedade(int iDPropriedade) {
		this.iDPropriedade = iDPropriedade;
	}


	/**
	 * @return the data
	 */
	public String getData() {
		return Data;
	}


	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		Data = data;
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
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format(
				"SensorBean [idSensor=%s, idCodArea=%s, idUsuario=%s, iDPropriedade=%s, nome=%s, longitude=%s, latitude=%s, Data=%s, super()=%s]",
				idSensor, idCodArea, idUsuario, iDPropriedade, nome, longitude, latitude, Data, super.toString());
	}


	

}
