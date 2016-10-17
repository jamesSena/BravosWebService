/**
 * 
 */
package br.com.bravos.webservices.model;

/**
 * @author JamessonSena
 *
 */
public class AreaBean extends _BeanAbstract{
	private String nomeArea;
	private int idArea,idPropriedade;
	

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
		return String.format("AreaBean [nomeArea=%s, idArea=%s, idPropriedade=%s, super()=%s]", nomeArea, idArea,
				idPropriedade, super.toString());
	}
	

	

}
