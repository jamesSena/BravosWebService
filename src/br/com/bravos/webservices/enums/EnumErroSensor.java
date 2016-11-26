package br.com.bravos.webservices.enums;

public enum EnumErroSensor {
	  _1 ("Falha (Gen�rico)"),
	  _2 ("Sensor j� existe"),
	  _3 ("Sensor n�o cadastrado"),
	  _4 ("area nao cadastrada"),
	  _5 ("Sensor j� esta associado a area escolhida"),
	  _5_JSONException ("Formato JSON invalido ou campo faltando, por favor verificar"),
	  _6_SQLException ("Inconsist�ncia no SQL"),
	  _7_ClassNotFoundException ("Erro ao localizar o Driver de conex�o"),	
	  _8_ParseException ("Erro no Formato da Data. [padr�o: dd/MM/yyyy] ");	

	private String name = "";
	   
	//Construtor
	EnumErroSensor(String name){this.name = name;  }
	
	//Retorno
	public String toString(){ return name;}

}