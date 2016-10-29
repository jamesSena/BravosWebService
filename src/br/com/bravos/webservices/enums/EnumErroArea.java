package br.com.bravos.webservices.enums;

/**
 * @author JamessonSena
 *
 */
public enum EnumErroArea {
	  _1 ("Falha (Gen�rico)"),
	  _2 ("Area j� existe"),
	  _3 ("Area n�o cadastrado"),
	  _4 (""),
	  _5_JSONException ("Formato JSON invalido ou campo faltando, por favor verificar"),
	  _6_SQLException ("Inconsist�ncia no SQL"),
	  _7_ClassNotFoundException ("Erro ao localizar o Driver de conex�o"),	
	  _8_ParseException ("Erro no Formato da Data. [padr�o: dd/MM/yyyy] ");	

	private String name = "";
	   
	//Construtor
	EnumErroArea(String name){this.name = name;  }
	
	//Retorno
	public String toString(){ return name;}

}
