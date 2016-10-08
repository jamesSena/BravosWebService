package br.com.bravos.webservices.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoDAO {

	private String stringConexao, usuario, senha;
	private Connection connection;
	
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	//Construtor default com as informações básicas de conexao
	public ConexaoDAO(){
		stringConexao = "jdbc:sqlserver://192.168.3.20:1433;databaseName=BDBovControl";
		usuario ="sa";
		senha = "1014231563";
	}
	
	//Construtor passando as informações de conexao
	public ConexaoDAO(String stringConexao, String usuario, String senha){
		this.stringConexao =stringConexao;
		this.usuario =usuario;
		this.senha = senha;
	}
	
	//Conexão com o banco de dados sql server
	 public Connection dbConnect(){
	      
		 try {	    	  
	         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	         connection = DriverManager.getConnection(stringConexao, usuario, senha);
	         System.out.println("Conexao realizada com sucesso");
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.out.println("Erro durante a conexão com a base de dados: " + e.toString());
	      }
		 return connection;
	   }
}
