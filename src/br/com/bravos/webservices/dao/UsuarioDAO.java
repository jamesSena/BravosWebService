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
	private CallableStatement callableStatement = null;
	private Connection connection = null;

	/**
	 * #Construtor default
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UsuarioDAO() throws ClassNotFoundException, SQLException {
		connection = super.getConnection();
	}

	/**
	 * #Construtor que recebe a instancia da conexao
	 * 
	 * @param connection
	 */
	public UsuarioDAO(Connection connection) {
		super.setConnection(connection);
	}

	/**
	 * #idoperacao = 1 -> Cadastrar Usuario
	 * 
	 * @param idOperacao
	 * @param idUsuario
	 * @param login
	 * @param senha
	 * @param idPropriedade
	 * @param ativo
	 * @param nome
	 * @param idPerfil
	 * @return codigo de sucesso/erro
	 * @throws SQLException
	 */
	public String execUsuarioCadastrar(int idUsuario, String login, String senha, int idPropriedade, boolean ativo,
			 String nome, int idPerfil) throws SQLException {
		String retorno = "-1";
		try {
			callableStatement = connection.prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 1);
			callableStatement.setInt(2, idUsuario);
			callableStatement.setString(3, login);
			callableStatement.setString(4, senha);
			callableStatement.setInt(5, idPropriedade);
			callableStatement.setBoolean(6, ativo);
			callableStatement.setString(7, nome);
			callableStatement.setInt(8, idPerfil);
			callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
			callableStatement.execute();
			retorno = callableStatement.getString(9);
			System.out.println("retorno: " + retorno);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return retorno;
	}

	// -- idoperacao = 2 -> retornar todos usuario
	/**
	 * @return uma lista de objetos [UsuarioBean]
	 * @throws SQLException
	 */
	public List<UsuarioBean> execUsuarioRetornarTodos() throws SQLException {
		List<UsuarioBean> usuario = new ArrayList<>();
		try {
			callableStatement = connection.prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 2);
			callableStatement.setInt(2, 0);
			callableStatement.setString(3, "");
			callableStatement.setString(4, "");
			callableStatement.setInt(5, 0);
			callableStatement.setBoolean(6, true);
			callableStatement.setString(7, "");
			callableStatement.setInt(8, 0);
			callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
			ResultSet rs = callableStatement.executeQuery();

			while (rs.next()) {
				usuarioBean = new UsuarioBean();
				usuarioBean.setAtivo(rs.getBoolean("Ativo"));
				usuarioBean.setIdPropriedade(rs.getInt("IdPropriedade"));
				usuarioBean.setIdPerfil(rs.getInt("IDPerfil"));
				usuarioBean.setNome(rs.getString("Nome"));
				usuarioBean.setLogin(rs.getString("Login"));
				usuarioBean.setSenha("*********");
				usuario.add(usuarioBean);
			}

			String retorno = callableStatement.getString(9);
			System.out.println("retorno: " + retorno);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return usuario;
	}

	/**
	 * @return o total de usuarios cadastrado
	 * @throws SQLException
	 */
	public int execBuscaTotal() throws SQLException {
		int todoUsuario = 0;
		try {
			callableStatement = connection.prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 2);
			callableStatement.setInt(2, 0);
			callableStatement.setString(3, "");
			callableStatement.setString(4, "");
			callableStatement.setInt(5, 0);
			callableStatement.setBoolean(6, true);
			callableStatement.setString(7, "");
			callableStatement.setInt(8, 0);
			callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
			ResultSet rs = callableStatement.executeQuery();

			while (rs.next()) {
				todoUsuario++;
			}

			String x = callableStatement.getString(9);
			System.out.println("retorno: " + x);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return todoUsuario;
	}

	// -- idoperacao = 3 -> retornar usuario especifico
	/**
	 * @param login
	 * @param senha
	 * @return Json UsuarioBean
	 * @throws SQLException
	 */
	public UsuarioBean execUsuarioRetornarEspecifico(String login, String senha) throws SQLException {
		try {
			callableStatement = connection.prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 3);
			callableStatement.setInt(2, 0);
			callableStatement.setString(3, login);
			callableStatement.setString(4, senha);
			callableStatement.setInt(5, 0);
			callableStatement.setBoolean(6, true);
			callableStatement.setString(7, "");
			callableStatement.setInt(8, 0);
			callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
			ResultSet rs = callableStatement.executeQuery();
			usuarioBean = new UsuarioBean();

			while (rs.next()) {
				usuarioBean.setIdUsuario(rs.getInt("idusuario"));
				usuarioBean.setAtivo(Boolean.parseBoolean(rs.getString("Ativo")));
				usuarioBean.setIdPropriedade(rs.getInt("IdPropriedade"));
				usuarioBean.setIdPerfil(rs.getInt("IDPerfil"));
				usuarioBean.setNome(rs.getString("Nome"));
				usuarioBean.setLogin(rs.getString("Login"));
				usuarioBean.setSenha("*********");
			}
			usuarioBean.setReason(callableStatement.getString(9));
			System.out.println("retorno: " + callableStatement.getString("retorn"));
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return usuarioBean;
	}

	// -- idoperacao = 4 -> atualizar usuario
	public String execUsuarioAtualizar(int idUsuario, String login, String senha, int idPropriedade, boolean ativo,
			 String nome, int idPerfil) throws SQLException {
		String retorno = "-1";
		try {
			callableStatement = connection.prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 4);
			callableStatement.setInt(2, idUsuario);
			callableStatement.setString(3, login);
			callableStatement.setString(4, senha);
			callableStatement.setInt(5, idPropriedade);
			callableStatement.setBoolean(6, ativo);
			callableStatement.setString(7, nome);
			callableStatement.setInt(8, idPerfil);
			callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
			callableStatement.execute();
			retorno = callableStatement.getString(9);
			System.out.println("retorno: " + retorno);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return retorno;
	}

	// -- idoperacao = 5 -> remover usuario
	public String execUsuarioRemover(int idUsuario, String login, String senha) throws SQLException {
		String retorno = "-1";
		try {
			callableStatement = connection.prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 5);
			callableStatement.setInt(2, idUsuario);
			callableStatement.setString(3, login);
			callableStatement.setString(4, senha);
			callableStatement.setInt(5, 0);
			callableStatement.setBoolean(6, false);
			callableStatement.setString(7, "");
			callableStatement.setInt(8, 0);
			callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
			callableStatement.execute();
			retorno = callableStatement.getString("retorn");
			System.out.println("retorno: " + retorno);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			super.dbClose(connection, callableStatement);
		}
		return retorno;
	}

	// -- idoperacao = 6 -> remover todos usuários
	public String execUsuarioRemoverTodos(int idUsuario) throws SQLException {
		String retorno = "-1";
		try {
			callableStatement = connection.prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 6);
			callableStatement.setInt(2, idUsuario);
			callableStatement.setString(3, "");
			callableStatement.setString(4, "");
			callableStatement.setInt(5, 0);
			callableStatement.setBoolean(6, false);
			callableStatement.setString(7, "");
			callableStatement.setInt(8, 0);
			callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
			callableStatement.execute();
			retorno = callableStatement.getString("retorn");
			System.out.println("retorno: " + retorno);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			super.dbClose(connection, callableStatement);
		}
		return retorno;
	}

	// -- idoperacao = 7 -> bloquear usuário
	public String execUsuarioBloquear(int idUsuario, String login, boolean ativo) throws SQLException {
		String retorno = "-1";
		try {
			callableStatement = connection.prepareCall("{ CALL spUsuarios (?,?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 7);
			callableStatement.setInt(2, idUsuario);
			callableStatement.setString(3, login);
			callableStatement.setString(4, "");
			callableStatement.setInt(5, 0);
			callableStatement.setBoolean(6, ativo);
			callableStatement.setString(7, "");
			callableStatement.setInt(8, 0);
			callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
			callableStatement.execute();
			retorno = callableStatement.getString("retorn");
			System.out.println("retorno: " + retorno);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			super.dbClose(connection, callableStatement);
		}
		return retorno;
	}

}
