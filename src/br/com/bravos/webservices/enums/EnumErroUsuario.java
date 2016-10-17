/**
 * 
 */
package br.com.bravos.webservices.enums;

/**
 * @author JamessonSena
 *
 */
public enum EnumErroUsuario {
		  _1 ("Falha (Gen�rico)"),
		  _2 ("Usu�rio j� existe"),
		  _3 ("Usu�rio n�o cadastrado"),
		  _4 ("Senha inv�lida"),
		  _5_JSONException ("Formato JSON invalido ou campo faltando, por favor verificar"),
		  _6_SQLException ("Inconsist�ncia no SQL"),
		  _7_ClassNotFoundException ("Erro ao localizar o Driver de conex�o");	
		 private String name = "";
		   
		  //Construtor
		 EnumErroUsuario(String name){
		    this.name = name;
		  }
		   
		  public String toString(){
		    return name;
		  }
	
}

