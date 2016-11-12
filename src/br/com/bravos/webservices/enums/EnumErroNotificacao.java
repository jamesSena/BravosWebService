/**
 * 
 */
package br.com.bravos.webservices.enums;

/**
 * @author JamessonSena
 *
 */

public enum EnumErroNotificacao {
	  _1 ("Falha (Gen�rico)"),
	  _2 ("Propriedade n�o existe"),
	  _3 ("Area n�o existe"),
	  _4 ("Sensor n�o existe"),
	  _5 ("Status n�o existe"),
	  _6_SQLException ("Inconsist�ncia no SQL"),
	  _7_ClassNotFoundException ("Erro ao localizar o Driver de conex�o"),	
	  _8_ParseException ("Erro no Formato da Data. [padr�o: dd/MM/yyyy] "),
	  _9_JSONException ("Formato JSON invalido ou campo faltando, por favor verificar");

	private String name = "";
	   
	  //Construtor
	 EnumErroNotificacao(String name){
	    this.name = name;
	  }
	   
	  public String toString(){
	    return name;
	  }
}
