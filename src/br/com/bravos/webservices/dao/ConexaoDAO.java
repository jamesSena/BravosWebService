package br.com.bravos.webservices.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author JamessonSena
 *
 */
public class ConexaoDAO {

	private String stringConexao, usuario, senha;
	private Connection connection;

	/**
	 * @return connection
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	protected Connection getConnection() throws SQLException, ClassNotFoundException {
		if (connection == null || connection.isClosed())
			return dbConnect();
		return connection;

	}

	/**
	 * @param connection
	 */
	protected void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * #Construtor default com as informações básicas de conexao
	 */
	public ConexaoDAO() {
			stringConexao = "jdbc:sqlserver://192.168.2.255:1433;databaseName=BDBovControlProd";
			usuario = "sa";
			senha = "jamessonSena";
		//senha = "1014231563";
	}

	/**
	 * #Construtor passando as informações de conexao
	 * 
	 * @param stringConexao
	 * @param usuario
	 * @param senha
	 */
	public ConexaoDAO(String stringConexao, String usuario, String senha) {
		this.stringConexao = stringConexao;
		this.usuario = usuario;
		this.senha = senha;
	}

	/**
	 * #Conexão com o banco de dados sql server
	 * 
	 * @return Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected Connection dbConnect() throws ClassNotFoundException, SQLException {

		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(stringConexao, usuario, senha);
			System.out.println("Conexao realizada com sucesso");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

		return connection;
	}

	protected void dbClose(Connection connection, CallableStatement callableStatement)  {
	

			try {
				if (connection != null && !connection.isClosed() && callableStatement != null && !callableStatement.isClosed())
					callableStatement.close();
				if (connection != null && !connection.isClosed())
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	
	}
}
