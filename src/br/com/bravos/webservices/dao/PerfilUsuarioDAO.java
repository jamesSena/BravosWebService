package br.com.bravos.webservices.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class PerfilUsuarioDAO extends ConexaoDAO {
	
	public PerfilUsuarioDAO(){

	}
	
	public PerfilUsuarioDAO(Connection connection){
		super.setConnection(connection);
	}
	
	public String execPerfilUsuario(){
//		
//		try {
//			
////			CallableStatement infected = null;
////			infected = super.getConnection().prepareCall(" CALL spPerfilUsuario (?,?,?,?)");
////			infected.registerOutParameter(1, java.sql.Types.INTEGER);
////			infected.registerOutParameter(2, java.sql.Types.INTEGER);
////			infected.registerOutParameter(3, java.sql.Types.INTEGER);
////			infected.registerOutParameter(4, java.sql.Types.VARCHAR);
//
////			infected.execute();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("Erro ao conectar a procedure: " + e.toString());
//		}
		return "sucesso";
	}
	
}
