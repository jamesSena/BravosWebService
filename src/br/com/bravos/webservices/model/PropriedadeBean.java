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
	 * 
	 */
	public PropriedadeBean() {
		// TODO Auto-generated constructor stub
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdPropriedade() {
		return idPropriedade;
	}

	public void setIdPropriedade(int idPropriedade) {
		this.idPropriedade = idPropriedade;
	}

	public String getNomePropriedade() {
		return nomePropriedade;
	}

	public void setNomePropriedade(String nomePropriedade) {
		this.nomePropriedade = nomePropriedade;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getEmailResponsavel() {
		return emailResponsavel;
	}

	public void setEmailResponsavel(String emailResponsavel) {
		this.emailResponsavel = emailResponsavel;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

 

}
