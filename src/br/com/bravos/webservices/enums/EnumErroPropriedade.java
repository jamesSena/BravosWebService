package br.com.bravos.webservices.enums;

/**
 * @author JamessonSena
 *
 */
public enum EnumErroPropriedade {
	  _1 ("Falha (Genérico)"),
	  _2 ("Propriedade já existe"),
	  _3 ("Propriedade não cadastrado"),
	  _4 ("E-mail já associado a uma propriedade"),
	  _5_JSONException ("Formato JSON invalido ou campo faltando, por favor verificar"),
	  _6_SQLException ("Inconsistência no SQL"),
	  _7_ClassNotFoundException ("Erro ao localizar o Driver de conexão"),	
	  _8_ParseException ("Erro no Formato da Data. [padrão: dd/MM/yyyy] ");	

	private String name = "";
	   
	  //Construtor
	EnumErroPropriedade(String name){
	    this.name = name;
	  }
	   
	  public String toString(){
	    return name;
	  }
}