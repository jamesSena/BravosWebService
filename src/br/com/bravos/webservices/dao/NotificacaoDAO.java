/**
 * 
 */
package br.com.bravos.webservices.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bravos.webservices.model.NotificacaoBean;

/**
 * @author JamessonSena
 *
 */
public class NotificacaoDAO extends ConexaoDAO {
	private CallableStatement callableStatement = null;
	private Connection connection = null;
	private String retorno = "-1";
	private NotificacaoBean notificacaoBean;
	private List<NotificacaoBean> NotificacaoList;
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * 
	 */
	public NotificacaoDAO() throws ClassNotFoundException, SQLException {
		connection = super.getConnection();
	}
	/**
	 * #Construtor que recebe a instancia da conexao
	 * 
	 * @param connection
	 */
	public NotificacaoDAO(Connection connection) {
		super.setConnection(connection);
	}
	
//	-- idoperacao = 1 -> Cadastrar Notificação
	public String execNotificacaoCadastrar(int idUsuario, int idSensor, int  idArea, int idPropriedade, int idStatus,
			Date dataInicio, Date dataFim, int idNotificacao) throws SQLException {
			retorno = "-1";
			try {
				
				callableStatement = connection.prepareCall("{ CALL spNotificacao (?,?,?,?,?,?,?,?,?,?)}");
				callableStatement.setInt(1, 1);
				callableStatement.setInt(2, idUsuario);
				callableStatement.setInt(3, idSensor);
				callableStatement.setInt(4, idArea);
				callableStatement.setInt(5, idPropriedade);
				callableStatement.setInt(6, idStatus);
				callableStatement.setDate(7, new java.sql.Date(dataInicio.getTime()));
				callableStatement.setDate(8, new java.sql.Date(dataFim.getTime()));
				callableStatement.setInt(9, idNotificacao);
				callableStatement.registerOutParameter(10, java.sql.Types.VARCHAR);
				callableStatement.execute();
				retorno = callableStatement.getString(10);
				System.out.println("retorno: " + retorno);
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				super.dbClose(connection, callableStatement);
			}
			return retorno;
		}

//	-- idoperacao = 2 -> retornar todas as notificações de uma propriedade
	public List<NotificacaoBean> execNotificacaoRetornarTodas(int idUsuario, int idPropriedade) throws SQLException {
			retorno = "-1";
			try {
				
				callableStatement = connection.prepareCall("{ CALL spNotificacao (?,?,?,?,?,?,?,?,?,?)}");
				callableStatement.setInt(1, 2);
				callableStatement.setInt(2, idUsuario);
				callableStatement.setInt(3, 0);
				callableStatement.setInt(4, 0);
				callableStatement.setInt(5, idPropriedade);
				callableStatement.setInt(6, 0);
				callableStatement.setDate(7, null);
				callableStatement.setDate(8, null);
				callableStatement.setInt(9, 0);
				callableStatement.registerOutParameter(10, java.sql.Types.VARCHAR);
				ResultSet resultSet = callableStatement.executeQuery();
				String tokenList = "";
				while (resultSet.next()) {
					notificacaoBean = new NotificacaoBean(tokenList);
					tokenList = notificacaoBean.getToken();
					notificacaoBean.setIdNotificacao(resultSet.getInt("IDNotificacao"));
					notificacaoBean.setIdSensor(resultSet.getInt("IDSensor"));
					notificacaoBean.setIdPropriedade(resultSet.getInt("IDPropriedade"));
					notificacaoBean.setIdArea(resultSet.getInt("IDArea"));
					notificacaoBean.setDataInicio(new java.util.Date(resultSet.getDate("Data").getTime()));
					notificacaoBean.setDataFim(new java.util.Date(resultSet.getDate("Data").getTime()));
					notificacaoBean.setIdStatus(resultSet.getInt("Status"));
					NotificacaoList.add(notificacaoBean);
				}
				
				retorno = callableStatement.getString(10);
				System.out.println("retorno: " + retorno);
				
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				super.dbClose(connection, callableStatement);
			}
			return NotificacaoList;
		}

//	-- idoperacao = 3 -> retornar notificação entre datas de uma propriedade
	public List<NotificacaoBean> execNotificacaoRetornar(int idUsuario, int idPropriedade, Date dataInicio, Date dataFim ) throws SQLException {
		retorno = "-1";
		NotificacaoList = new ArrayList<NotificacaoBean>();
		try {
			
			callableStatement = connection.prepareCall("{ CALL spNotificacao (?,?,?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 3);
			callableStatement.setInt(2, idUsuario);
			callableStatement.setInt(3, 0);
			callableStatement.setInt(4, 0);
			callableStatement.setInt(5, idPropriedade);
			callableStatement.setInt(6, 0);
			callableStatement.setDate(7, new java.sql.Date(dataInicio.getTime()));
			callableStatement.setDate(8, new java.sql.Date(dataFim.getTime()));
			callableStatement.setInt(9, 0);
			callableStatement.registerOutParameter(10, java.sql.Types.VARCHAR);
			ResultSet resultSet = callableStatement.executeQuery();
			//Se não retornar o id da notificação é porque não tem notificação e a aplicação não vai executar as linhas abaaixo
			if(callableStatement.getMoreResults()){

				 resultSet =	callableStatement.getResultSet();
			}
			int i =0;
			while (resultSet.next()) {
		
				notificacaoBean = new NotificacaoBean();

				
				notificacaoBean.setIdNotificacao(resultSet.getInt("IDNotificacao"));
				notificacaoBean.setIdSensor(resultSet.getInt("IDSensor"));
				notificacaoBean.setIdPropriedade(resultSet.getInt("IDPropriedade"));
				notificacaoBean.setIdArea(resultSet.getInt("IDArea"));
				System.out.println(notificacaoBean.toString());
				notificacaoBean.setDataFim(new java.util.Date(resultSet.getDate("Data").getTime()));
				notificacaoBean.setDataInicio(new java.util.Date(resultSet.getDate("Data").getTime()));
				notificacaoBean.setIdStatus(resultSet.getInt("Status"));
				NotificacaoList.add(notificacaoBean);
				System.out.println("retorno: teste de loop " + i++);

				}
			
			retorno = callableStatement.getString(10);
			System.out.println("retorno: teste " + retorno);
			
	
			System.out.println(NotificacaoList.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();

		}finally {
			super.dbClose(connection, callableStatement);
		}
		return NotificacaoList;
	}

//	-- idoperacao = 4 -> deletar notificação especifica de uma propriedade
	public String execDeletarNotificacao(int idUsuario, int idPropriedade, int idNotificacao ) throws SQLException {
		retorno = "-1";
		try {
			
			callableStatement = connection.prepareCall("{ CALL spNotificacao (?,?,?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 4);
			callableStatement.setInt(2, idUsuario);
			callableStatement.setInt(3, 0);
			callableStatement.setInt(4, 0);
			callableStatement.setInt(5, idPropriedade);
			callableStatement.setInt(6, 0);
			callableStatement.setDate(7, null);
			callableStatement.setDate(8, null);
			callableStatement.setInt(9, idNotificacao);
			callableStatement.registerOutParameter(10, java.sql.Types.VARCHAR);
			callableStatement.execute();			
			retorno = callableStatement.getString(10);
			System.out.println("retorno: " + retorno);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return retorno;
	}
	
//	-- idoperacao = 5 -> deletar todas notificações de uma propriedade
	public String execDeletarNotificacao(int idUsuario, int idPropriedade) throws SQLException {
		retorno = "-1";
		try {
			
			callableStatement = connection.prepareCall("{ CALL spNotificacao (?,?,?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 5);
			callableStatement.setInt(2, idUsuario);
			callableStatement.setInt(3, 0);
			callableStatement.setInt(4, 0);
			callableStatement.setInt(5, idPropriedade);
			callableStatement.setInt(6, 0);
			callableStatement.setDate(7, null);
			callableStatement.setDate(8, null);
			callableStatement.setInt(9, 0);
			callableStatement.registerOutParameter(10, java.sql.Types.VARCHAR);
			callableStatement.execute();			
			retorno = callableStatement.getString(10);
			System.out.println("retorno: " + retorno);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return retorno;
	}
	
	
}
