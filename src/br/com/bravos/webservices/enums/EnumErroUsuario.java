/**
 * 
 */
package br.com.bravos.webservices.enums;

/**
 * @author JamessonSena
 *
 */
public enum EnumErroUsuario {
		  _1 ("Falha (Genérico)"),
		  _2 ("Usuário já existe"),
		  _3 ("Usuário não cadastrado"),
		  _4 ("Senha inválida"),
		  _5_JSONException ("Formato JSON invalido ou campo faltando, por favor verificar"),
		  _6_SQLException ("Inconsistência no SQL"),
		  _7_ClassNotFoundException ("Erro ao localizar o Driver de conexão");	
		 private String name = "";
		   
		  //Construtor
		 EnumErroUsuario(String name){
		    this.name = name;
		  }
		   
		  public String toString(){
		    return name;
		  }
	
}

