package br.com.bravos.webservices.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bravos.webservices.model.UsuarioBean;

/**
 * @author JamessonSena
 *
 */

public class UsuarioDAO extends ConexaoDAO {
	private UsuarioBean usuarioBean;

	/**
	 * @param connection
	 */
	public UsuarioDAO(Connection connection) {
		super.setConnection(connection);
	}
	
	//-- idoperacao = 1 -> Cadastrar Usuario
	/**
	 * @param idOperacao
	 * @param idUsuario
	 * @param login
	 * @param senha
	 * @param idPropriedade
	 * @param ativo
	 * @param email
	 * @param nome
	 * @param idPerfil
	 * @return codigo de sucesso/erro
	 */
	public String execUsuarioCadastrar(int idUsuario, String login, String senha, int idPropriedade,
			boolean ativo, String email, String nome, int idPerfil) {
		String retorno="-1";
		try {
			CallableStatement infected = null;
			infected = super.getConnection().prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?,?)}");
			infected.setInt(1, 1);
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
			
			retorno = infected.getString(10);
			System.out.println("retorno: " + retorno);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro a criar um novo login: " + e.getMessage());
		}
		return retorno;
	}	

	//-- idoperacao = 2 -> retornar todos usuario
	/**
	 * @return o total de usuarios cadastrado na base de dados.
	 */
	public List<UsuarioBean> execUsuarioRetornarTodos() {
		List<UsuarioBean> usuario = new ArrayList<>();
		try {
			CallableStatement infected = null;
			infected = super.getConnection().prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?,?)}");
			infected.setInt(1, 2);
			infected.setInt(2, 0);
			infected.setString(3, "");
			infected.setString(4, "");
			infected.setInt(5, 0);
			infected.setBoolean(6, true);
			infected.setString(7, "");
			infected.setString(8, "");
			infected.setInt(9, 0);
			infected.registerOutParameter(10, java.sql.Types.VARCHAR);
			ResultSet rs = infected.executeQuery();
		
			while (rs.next()) {
				usuarioBean = new UsuarioBean();
				usuarioBean.setAtivo(rs.getBoolean("Ativo"));
				usuarioBean.setIdPropriedade(rs.getInt("IdPropriedade"));
				usuarioBean.setIdPerfil(rs.getInt("IDPerfil"));
				usuarioBean.setEmail(rs.getString("Email"));
				usuarioBean.setNome(rs.getString("Nome"));
				usuarioBean.setLogin(rs.getString("Login"));
				usuarioBean.setSenha("*********");

				usuario.add(usuarioBean);
			}
			
			String x = infected.getString(10);
			System.out.println("retorno: " + x);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro a criar um novo login: " + e.getMessage());
		}
		return usuario;
	}
	
	public int execBuscaTotal() {
		int todoUsuario = 0;
		List<UsuarioBean> usuario = null;
		try {
			CallableStatement infected = null;
			infected = super.getConnection().prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?,?)}");
			infected.setInt(1, 2);
			infected.setInt(2, 0);
			infected.setString(3, "");
			infected.setString(4, "");
			infected.setInt(5, 0);
			infected.setBoolean(6, true);
			infected.setString(7, "");
			infected.setString(8, "");
			infected.setInt(9, 0);
			infected.registerOutParameter(10, java.sql.Types.VARCHAR);
			ResultSet rs = infected.executeQuery();
		
			while (rs.next()) {
				todoUsuario++;
			}
			
			String x = infected.getString(10);
			System.out.println("retorno: " + x);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro a criar um novo login: " + e.getMessage());
		}
		return todoUsuario;
	}
	
	//-- idoperacao = 3 -> retornar usuario especifico
	public UsuarioBean execUsuarioRetornarEspecifico(int idOperacao, int idUsuario, String login, String senha, int idPropriedade,
			boolean ativo, String email, String nome, int idPerfil) {
		try {
			CallableStatement infected = null;
			infected = super.getConnection().prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?,?)}");
			infected.setInt(1, 3);
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
		return usuarioBean;
	}
	//-- idoperacao = 4 -> atualizar usuario
	public UsuarioBean execUsuarioAtualizar(int idOperacao, int idUsuario, String login, String senha, int idPropriedade,
			boolean ativo, String email, String nome, int idPerfil) {
		try {
			CallableStatement infected = null;
			infected = super.getConnection().prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?,?)}");
			infected.setInt(1, 4);
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
		return usuarioBean;
	}
	//-- idoperacao = 5 -> remover usuario
	public UsuarioBean execUsuarioRemover(int idOperacao, int idUsuario, String login, String senha, int idPropriedade,
			boolean ativo, String email, String nome, int idPerfil) {
		try {
			CallableStatement infected = null;
			infected = super.getConnection().prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?,?)}");
			infected.setInt(1, 5);
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
		return usuarioBean;
	}
	//-- idoperacao = 6 -> remover todos usuários
	public UsuarioBean execUsuarioRemoverTodos(int idOperacao, int idUsuario, String login, String senha, int idPropriedade,
			boolean ativo, String email, String nome, int idPerfil) {
		try {
			CallableStatement infected = null;
			infected = super.getConnection().prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?,?)}");
			infected.setInt(1, 6);
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
		return usuarioBean;
	}
	//-- idoperacao = 7 -> bloquear usuário
	public UsuarioBean execUsuarioBloquear(int idOperacao, int idUsuario, String login, String senha, int idPropriedade,
			boolean ativo, String email, String nome, int idPerfil) {
		try {
			CallableStatement infected = null;
			infected = super.getConnection().prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?,?)}");
			infected.setInt(1, 7);
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
		return usuarioBean;
	}
	
	
	
	
}
