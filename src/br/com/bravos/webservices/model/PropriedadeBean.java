/**
 * 
 */
package br.com.bravos.webservices.model;

/**
 * @author JamessonSena
 *
 */
public class PropriedadeBean extends _BeanAbstract{


	private int idUsuario,	idPropriedade;
	private String nomePropriedade, responsavel, emailResponsavel, latitude, longitude, dataCadastro;
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
	/**
	 * @return the dataCadastro
	 */
	public String getDataCadastro() {
		return dataCadastro;
	}
	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	@Override
	public String toString() {
		return String.format(
				"PropriedadeBean [idUsuario=%s, idPropriedade=%s, nomePropriedade=%s, responsavel=%s, emailResponsavel=%s, latitude=%s, longitude=%s, dataCadastro=%s, super()=%s]",
				idUsuario, idPropriedade, nomePropriedade, responsavel, emailResponsavel, latitude, longitude,
				dataCadastro, super.toString());
	}

	

}
