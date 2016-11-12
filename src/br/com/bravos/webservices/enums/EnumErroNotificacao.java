/**
 * 
 */
package br.com.bravos.webservices.enums;

/**
 * @author JamessonSena
 *
 */

public enum EnumErroNotificacao {
	  _1 ("Falha (Genérico)"),
	  _2 ("Propriedade não existe"),
	  _3 ("Area não existe"),
	  _4 ("Sensor não existe"),
	  _5 ("Status não existe"),
	  _6_SQLException ("Inconsistência no SQL"),
	  _7_ClassNotFoundException ("Erro ao localizar o Driver de conexão"),	
	  _8_ParseException ("Erro no Formato da Data. [padrão: dd/MM/yyyy] "),
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
