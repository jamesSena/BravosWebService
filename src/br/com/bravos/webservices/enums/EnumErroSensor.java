package br.com.bravos.webservices.enums;

public enum EnumErroSensor {
	  _1 ("Falha (Genérico)"),
	  _2 ("Area já existe"),
	  _3 ("Area não cadastrado"),
	  _4 (""),
	  _5_JSONException ("Formato JSON invalido ou campo faltando, por favor verificar"),
	  _6_SQLException ("Inconsistência no SQL"),
	  _7_ClassNotFoundException ("Erro ao localizar o Driver de conexão"),	
	  _8_ParseException ("Erro no Formato da Data. [padrão: dd/MM/yyyy] ");	

	private String name = "";
	   
	//Construtor
	EnumErroSensor(String name){this.name = name;  }
	
	//Retorno
	public String toString(){ return name;}

}

