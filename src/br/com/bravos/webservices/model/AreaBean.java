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
public class AreaBean extends _BeanAbstract{
	private String nomeArea;
	private int idArea,idPropriedade;
	private List<EstacaBean> estacaBean;
	
	public AreaBean() {
		this.setToken(new Token().gerarToken(idArea));
	}
	public AreaBean(String token) {
		if(token.isEmpty()){this.setToken(token);return;}
		this.setToken(new Token().gerarToken(idArea));

	}
	/**
	 * @param success
	 * @param detail
	 * @param reason
	 * @param nomeArea
	 * @param idArea
	 * @param idPropriedade
	 */
	public AreaBean(boolean success, String detail, String reason, String token, String nomeArea, int idArea, int idPropriedade) {
		super(success, detail, reason);
		this.nomeArea = nomeArea;
		this.idArea = idArea;
		this.idPropriedade = idPropriedade;
	}
	/**
	 * @param success
	 * @param detail
	 * @param reason
	 */
	public AreaBean(boolean success, String detail, String reason) {
		super(success, detail, reason);
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
	@Override
	public String toString() {
		return String.format(
				"AreaBean [nomeArea=%s, idArea=%s, idPropriedade=%s, isSuccess()=%s, getDetail()=%s, getReason()=%s, getToken()=%s]",
				nomeArea, idArea, idPropriedade, isSuccess(), getDetail(), getReason(), getToken());
	}
	public List<EstacaBean> getEstacaBean() {
		return estacaBean;
	}
	public void setEstacaBean(List<EstacaBean> estacaBean) {
		this.estacaBean = estacaBean;
	}

	
	
	

}
