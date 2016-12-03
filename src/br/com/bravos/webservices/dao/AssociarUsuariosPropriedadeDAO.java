package br.com.bravos.webservices.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bravos.webservices.model.UsuarioBean;


public class AssociarUsuariosPropriedadeDAO extends ConexaoDAO   {
	/**
	 * 
	 */
	private UsuarioBean usuarioBean;
	private CallableStatement callableStatement = null;
	private Connection connection = null;
	
	public AssociarUsuariosPropriedadeDAO() throws ClassNotFoundException, SQLException {
		connection = super.getConnection();
		}
	

	/**
	 * #Construtor que recebe a instancia da conexao
	 * 
	 * @param connection
	 */
	public AssociarUsuariosPropriedadeDAO(Connection connection) {
		super.setConnection(connection);
	}
/*
 * 	@idoperacao int,
	@login varchar(100) = null,
	@idPropriedade int = null,
	@idPropriedadeCandidata int = null,
	@aceito bit = null,
 * 
 * */
	//-- idoperacao = 1 -> Usuário Solicitação Propriedade 
	public String execUsuarioCadastrar(String login, int idPropriedadeCandidata) throws SQLException {
		String retorno = "-1";
		try {
			callableStatement = connection.prepareCall("{ CALL spAssociarUsuariosPropriedade (?,?,?,?,?,?)}");
			callableStatement.setInt(1, 1);
			callableStatement.setString(2, login);
			callableStatement.setInt(3, idPropriedadeCandidata);
			callableStatement.setInt(4, idPropriedadeCandidata);
			callableStatement.setBoolean(5, false);
			callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
			callableStatement.execute();
			retorno = callableStatement.getString(6);
			System.out.println("retorno: zzz" + retorno);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return retorno;
	}

	
	
	//-- idoperacao = 2 -> retornar todos usuario que querem se associar
		public List<UsuarioBean> execUsuarioRetornar(int idPropriedadeCandidata) throws SQLException {
			String retorno = "-1";
			List<UsuarioBean> usuarios = new ArrayList<UsuarioBean>();
			try {
				callableStatement = connection.prepareCall("{ CALL spAssociarUsuariosPropriedade (?,?,?,?,?,?)}");
				callableStatement.setInt(1, 2);
				callableStatement.setString(2, "");
				callableStatement.setInt(3, idPropriedadeCandidata);
				callableStatement.setInt(4, idPropriedadeCandidata);
				callableStatement.setBoolean(5, false);
				callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
				ResultSet rs = callableStatement.executeQuery();
				//Se não retornar o id da notificação é porque não tem notificação e a aplicação não vai executar as linhas abaaixo
				if(callableStatement.getMoreResults()){

					rs =	callableStatement.getResultSet();
				}
				while (rs.next()) {
					usuarioBean = new UsuarioBean();
					usuarioBean.setIdPropriedade(rs.getInt("IDPropriedadeCandidata"));
					usuarioBean.setLogin(rs.getString("Login"));
					usuarioBean.setSenha("*********");
					usuarios.add(usuarioBean);
				}
				if(usuarios != null && !usuarios.isEmpty())
				usuarios.get(0).setReason(callableStatement.getString(6));
				System.out.println("retorno: " + callableStatement.getString("retorn"));
				} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				super.dbClose(connection, callableStatement);
			}
			return usuarios;
		}

}
