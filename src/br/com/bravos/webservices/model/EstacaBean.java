/**
 * 
 */
package br.com.bravos.webservices.model;

import java.util.Date;

/**
 * @author JamessonSena
 *
 */

public class EstacaBean extends _BeanAbstract {
	private int idEstaca, idArea;
	private String nome, latitude, longitude;
	private Date dataCadastro;
	
	
	
	
	
<<<<<<< HEAD
	
	/**
	 * 
	 */
	public EstacaBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
=======
	public EstacaBean() {super();}
	
	/**
>>>>>>> 32994819c99a6dc40ccc58877545f7d2cd73eb9f
	 * @param success
	 * @param detail
	 * @param reason
	 */
	public EstacaBean(boolean success, String detail, String reason) {
		super(success, detail, reason);
<<<<<<< HEAD
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param success
	 * @param detail
	 * @param reason
	 * @param idEstaca
	 * @param idArea
	 * @param nome
	 * @param latitude
	 * @param longitude
	 * @param dataCadastro
	 */
	public EstacaBean(boolean success, String detail, String reason, int idEstaca, int idArea, String nome,
			String latitude, String longitude, Date dataCadastro) {
		super(success, detail, reason);
		this.idEstaca = idEstaca;
		this.idArea = idArea;
		this.nome = nome;
		this.latitude = latitude;
		this.longitude = longitude;
		this.dataCadastro = dataCadastro;
	}
=======
		}
	
>>>>>>> 32994819c99a6dc40ccc58877545f7d2cd73eb9f
	/**
	 * @return the dataCadastro
	 */
	public Date getDataCadastro() {
		return dataCadastro;
	}
	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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
	@Override
	public String toString() {
		return String.format(
				"EstacaBean [idEstaca=%s, idArea=%s, nome=%s, latitude=%s, longitude=%s, dataCadastro=%s, super()=%s]",
				idEstaca, idArea, nome, latitude, longitude, dataCadastro, super.toString());
	}
	



}