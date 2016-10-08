package br.com.bravos.webservices.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import br.com.bravos.webservices.model.UsuarioLoginBean;

/**
 * @author JamessonSena
 *
 */

public class UsuarioDAO extends ConexaoDAO {
	private UsuarioLoginBean usuarioLoginBean;

	public UsuarioDAO(Connection connection) {
		super.setConnection(connection);
	}



	//Busca usuario especifico
	
	//-- idoperacao = 1 -> Cadastrar Usuario
	public UsuarioLoginBean execUsuarioCadastrar(int idOperacao, int idUsuario, String login, String senha, int idPropriedade,
			boolean ativo, String email, String nome, int idPerfil) {
		try {
			CallableStatement infected = null;
			infected = super.getConnection().prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?,?)}");
			infected.setInt(1, 2);
			infected.setInt(2, idUsuario);
			infected.setString(3, login);
			infected.setString(4, senha);
			infected.setInt(5, idPropriedade);
			infected.setBoolean(6, ativo);
			infected.setString(7, email);
			infected.setString(8, nome);
			infected.setInt(9, idPerfil);
			infected.registerOutParameter(10, java.sql.Types.VARCHAR);
			ResultSet rs = infected.executeQuery();

			while (rs.next()) {
				
				System.out.println("ResultSet: " + rs.getString("nomes"));
				}
			
			String x = infected.getString(10);
			System.out.println("retorno: " + x);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro a criar um novo login: " + e.getMessage());
		}
		return usuarioLoginBean;
	}	
	//-- idoperacao = 2 -> retornar todos usuario
	public UsuarioLoginBean execUsuarioRetornarTodos(int idOperacao, int idUsuario, String login, String senha, int idPropriedade,
			boolean ativo, String email, String nome, int idPerfil) {
		try {
			CallableStatement infected = null;
			infected = super.getConnection().prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?,?)}");
			infected.setInt(1, 2);
			infected.setInt(2, idUsuario);
			infected.setString(3, login);
			infected.setString(4, senha);
			infected.setInt(5, idPropriedade);
			infected.setBoolean(6, ativo);
			infected.setString(7, email);
			infected.setString(8, nome);
			infected.setInt(9, idPerfil);
			infected.registerOutParameter(10, java.sql.Types.VARCHAR);
			ResultSet rs = infected.executeQuery();

			while (rs.next()) {
				
				System.out.println("ResultSet: " + rs.getString("nomes"));
				}
			
			String x = infected.getString(10);
			System.out.println("retorno: " + x);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro a criar um novo login: " + e.getMessage());
		}
		return usuarioLoginBean;
	}
	//-- idoperacao = 3 -> retornar usuario especifico
	public UsuarioLoginBean execUsuarioRetornarEspecifico(int idOperacao, int idUsuario, String login, String senha, int idPropriedade,
			boolean ativo, String email, String nome, int idPerfil) {
		try {
			CallableStatement infected = null;
			infected = super.getConnection().prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?,?)}");
			infected.setInt(1, idOperacao);
			infected.setInt(2, idUsuario);
			infected.setString(3, login);
			infected.setString(4, senha);
			infected.setInt(5, idPropriedade);
			infected.setBoolean(6, ativo);
			infected.setString(7, email);
			infected.setString(8, nome);
			infected.setInt(9, idPerfil);
			infected.registerOutParameter(10, java.sql.Types.VARCHAR);
			ResultSet rs = infected.executeQuery();

			while (rs.next()) {
				
				System.out.println("ResultSet: " + rs.getString("Login"));
				System.out.println("ResultSet: " + rs.getString("SenhaDescrip"));
				System.out.println("ResultSet: " + rs.getString("IdPropriedade"));
				System.out.println("ResultSet: " + rs.getString("Ativo"));
				System.out.println("ResultSet: " + rs.getString("Email"));
				System.out.println("ResultSet: " + rs.getString("Nome"));
				System.out.println("ResultSet: " + rs.getString("IDPerfil"));
				}
			
			
			System.out.println("retorno: " + infected.getString("retorn"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro a criar um novo login: " + e.getMessage());
		}
		return usuarioLoginBean;
	}
	//-- idoperacao = 4 -> atualizar usuario
	public UsuarioLoginBean execUsuarioAtualizar(int idOperacao, int idUsuario, String login, String senha, int idPropriedade,
			boolean ativo, String email, String nome, int idPerfil) {
		try {
			CallableStatement infected = null;
			infected = super.getConnection().prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?,?)}");
			infected.setInt(1, idOperacao);
			infected.setInt(2, idUsuario);
			infected.setString(3, login);
			infected.setString(4, senha);
			infected.setInt(5, idPropriedade);
			infected.setBoolean(6, ativo);
			infected.setString(7, email);
			infected.setString(8, nome);
			infected.setInt(9, idPerfil);
			infected.registerOutParameter(10, java.sql.Types.VARCHAR);
			ResultSet rs = infected.executeQuery();

			while (rs.next()) {
				
				System.out.println("ResultSet: " + rs.getString("Login"));
				System.out.println("ResultSet: " + rs.getString("SenhaDescrip"));
				System.out.println("ResultSet: " + rs.getString("IdPropriedade"));
				System.out.println("ResultSet: " + rs.getString("Ativo"));
				System.out.println("ResultSet: " + rs.getString("Email"));
				System.out.println("ResultSet: " + rs.getString("Nome"));
				System.out.println("ResultSet: " + rs.getString("IDPerfil"));
				}
			
			
			System.out.println("retorno: " + infected.getString("retorn"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro a criar um novo login: " + e.getMessage());
		}
		return usuarioLoginBean;
	}
	//-- idoperacao = 5 -> remover usuario
	public UsuarioLoginBean execUsuarioRemover(int idOperacao, int idUsuario, String login, String senha, int idPropriedade,
			boolean ativo, String email, String nome, int idPerfil) {
		try {
			CallableStatement infected = null;
			infected = super.getConnection().prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?,?)}");
			infected.setInt(1, idOperacao);
			infected.setInt(2, idUsuario);
			infected.setString(3, login);
			infected.setString(4, senha);
			infected.setInt(5, idPropriedade);
			infected.setBoolean(6, ativo);
			infected.setString(7, email);
			infected.setString(8, nome);
			infected.setInt(9, idPerfil);
			infected.registerOutParameter(10, java.sql.Types.VARCHAR);
			ResultSet rs = infected.executeQuery();

			while (rs.next()) {
				
				System.out.println("ResultSet: " + rs.getString("Login"));
				System.out.println("ResultSet: " + rs.getString("SenhaDescrip"));
				System.out.println("ResultSet: " + rs.getString("IdPropriedade"));
				System.out.println("ResultSet: " + rs.getString("Ativo"));
				System.out.println("ResultSet: " + rs.getString("Email"));
				System.out.println("ResultSet: " + rs.getString("Nome"));
				System.out.println("ResultSet: " + rs.getString("IDPerfil"));
				}
			
			
			System.out.println("retorno: " + infected.getString("retorn"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro a criar um novo login: " + e.getMessage());
		}
		return usuarioLoginBean;
	}
	//-- idoperacao = 6 -> remover todos usuários
	public UsuarioLoginBean execUsuarioRemoverTodos(int idOperacao, int idUsuario, String login, String senha, int idPropriedade,
			boolean ativo, String email, String nome, int idPerfil) {
		try {
			CallableStatement infected = null;
			infected = super.getConnection().prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?,?)}");
			infected.setInt(1, idOperacao);
			infected.setInt(2, idUsuario);
			infected.setString(3, login);
			infected.setString(4, senha);
			infected.setInt(5, idPropriedade);
			infected.setBoolean(6, ativo);
			infected.setString(7, email);
			infected.setString(8, nome);
			infected.setInt(9, idPerfil);
			infected.registerOutParameter(10, java.sql.Types.VARCHAR);
			ResultSet rs = infected.executeQuery();

			while (rs.next()) {
				
				System.out.println("ResultSet: " + rs.getString("Login"));
				System.out.println("ResultSet: " + rs.getString("SenhaDescrip"));
				System.out.println("ResultSet: " + rs.getString("IdPropriedade"));
				System.out.println("ResultSet: " + rs.getString("Ativo"));
				System.out.println("ResultSet: " + rs.getString("Email"));
				System.out.println("ResultSet: " + rs.getString("Nome"));
				System.out.println("ResultSet: " + rs.getString("IDPerfil"));
				}
			
			
			System.out.println("retorno: " + infected.getString("retorn"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro a criar um novo login: " + e.getMessage());
		}
		return usuarioLoginBean;
	}
	//-- idoperacao = 7 -> bloquear usuário
	public UsuarioLoginBean execUsuarioBloquear(int idOperacao, int idUsuario, String login, String senha, int idPropriedade,
			boolean ativo, String email, String nome, int idPerfil) {
		try {
			CallableStatement infected = null;
			infected = super.getConnection().prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?,?)}");
			infected.setInt(1, idOperacao);
			infected.setInt(2, idUsuario);
			infected.setString(3, login);
			infected.setString(4, senha);
			infected.setInt(5, idPropriedade);
			infected.setBoolean(6, ativo);
			infected.setString(7, email);
			infected.setString(8, nome);
			infected.setInt(9, idPerfil);
			infected.registerOutParameter(10, java.sql.Types.VARCHAR);
			ResultSet rs = infected.executeQuery();

			while (rs.next()) {
				
				System.out.println("ResultSet: " + rs.getString("Login"));
				System.out.println("ResultSet: " + rs.getString("SenhaDescrip"));
				System.out.println("ResultSet: " + rs.getString("IdPropriedade"));
				System.out.println("ResultSet: " + rs.getString("Ativo"));
				System.out.println("ResultSet: " + rs.getString("Email"));
				System.out.println("ResultSet: " + rs.getString("Nome"));
				System.out.println("ResultSet: " + rs.getString("IDPerfil"));
				}
			
			
			System.out.println("retorno: " + infected.getString("retorn"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro a criar um novo login: " + e.getMessage());
		}
		return usuarioLoginBean;
	}
	
	
	
	
}
