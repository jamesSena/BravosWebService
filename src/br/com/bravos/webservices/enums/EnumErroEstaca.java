<<<<<<< HEAD
package br.com.bravos.webservices.enums;

/**
 * @author JamessonSena
 *
 */
public enum EnumErroEstaca {
	  _1 ("Falha (Gen�rico)"),
	  _2 ("estaca j� existe"),
	  _3 ("estaca n�o cadastrado"),
	  _4 ("area nao cadastrada"),
	  _5_JSONException ("Formato JSON invalido ou campo faltando, por favor verificar"),
	  _6_SQLException ("Inconsist�ncia no SQL"),
	  _7_ClassNotFoundException ("Erro ao localizar o Driver de conex�o"),	
	  _8_ParseException ("Erro no Formato da Data. [padr�o: dd/MM/yyyy] ");	

	private String name = "";
	   
	//Construtor
	EnumErroEstaca(String name){this.name = name;  }
	
	//Retorno
	public String toString(){ return name;}

}
=======
package br.com.bravos.webservices.enums;

/**
 * @author JamessonSena
 *
 */
public enum EnumErroEstaca {
	  _1 ("Falha (Gen�rico)"),
	  _2 ("Arestacaea j� existe"),
	  _3 ("estaca n�o cadastrado"),
	  _4 ("area nao cadastrada"),
	  _5_JSONException ("Formato JSON invalido ou campo faltando, por favor verificar"),
	  _6_SQLException ("Inconsist�ncia no SQL"),
	  _7_ClassNotFoundException ("Erro ao localizar o Driver de conex�o"),	
	  _8_ParseException ("Erro no Formato da Data. [padr�o: dd/MM/yyyy] ");	

	private String name = "";
	   
	//Construtor
	EnumErroEstaca(String name){this.name = name;  }
	
	//Retorno
	public String toString(){ return name;}

}
>>>>>>> 32994819c99a6dc40ccc58877545f7d2cd73eb9f
