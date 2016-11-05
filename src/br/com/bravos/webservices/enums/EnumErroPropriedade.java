package br.com.bravos.webservices.enums;

/**
 * @author JamessonSena
 *
 */
public enum EnumErroPropriedade {
	  _1 ("Falha (Gen�rico)"),
	  _2 ("Propriedade j� existe"),
	  _3 ("Propriedade n�o cadastrado"),
	  _4 ("E-mail j� associado a uma propriedade"),
	  _5_JSONException ("Formato JSON invalido ou campo faltando, por favor verificar"),
	  _6_SQLException ("Inconsist�ncia no SQL"),
	  _7_ClassNotFoundException ("Erro ao localizar o Driver de conex�o"),	
	  _8_ParseException ("Erro no Formato da Data. [padr�o: dd/MM/yyyy] ");	

	private String name = "";
	   
	  //Construtor
	EnumErroPropriedade(String name){
	    this.name = name;
	  }
	   
	  public String toString(){
	    return name;
	  }
}