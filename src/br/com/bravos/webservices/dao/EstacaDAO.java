package br.com.bravos.webservices.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bravos.webservices.model.EstacaBean;

/**
 * @author JamessonSena
 *
 */
public class EstacaDAO extends ConexaoDAO{
	private CallableStatement callableStatement = null;
	private Connection connection = null;
	private EstacaBean estacaBean;
	private String retorno = "-1";
	
	
	/**
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 */
	public EstacaDAO() throws ClassNotFoundException, SQLException {
		connection = super.getConnection();
	}

	/**
	 * #Construtor que recebe a instancia da conexao  
	 * 
	 * @param connection
	 */
	public EstacaDAO(Connection connection) {
		super.setConnection(connection);
	}

//	-- idoperacao = 1 -> Cadastrar Estaca
	public String execEstacaCadastrar(int idUsuario, int idEstaca, int idArea, String nome, String latitude,
			String longitude) throws SQLException {
		retorno = "-1";
		try {
			callableStatement = connection.prepareCall("{ CALL spEstaca (?,?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 1);
			callableStatement.setInt(2, idUsuario);
			callableStatement.setInt(3, idEstaca);
			callableStatement.setInt(4, idArea);
			callableStatement.setString(5, nome);
			callableStatement.setString(6, latitude);
			callableStatement.setString(7, longitude);
			callableStatement.setInt(8, 0);
			callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
			callableStatement.execute();
			retorno = callableStatement.getString(9);
			System.out.println("teste" + retorno);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return retorno;
	}

//	-- idoperacao = 2 -> retornar todas as estacas de uma area 
	public List<EstacaBean> execEstacaRetornarTodos(int idArea, int idUsuario) throws SQLException {
		List<EstacaBean> estacas = new ArrayList<>();
		String retorno = "-1";
		System.out.println("Piquete: " + idArea);
		System.out.println("propriedade: " + idUsuario);

		try {
			callableStatement = connection.prepareCall("{ CALL spEstaca (?,?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 2);
			callableStatement.setInt(2, 1);
			callableStatement.setInt(3, 1);
			callableStatement.setInt(4, idArea);
			callableStatement.setString(5, "");
			callableStatement.setString(6, "");
			callableStatement.setString(7, "");
			callableStatement.setInt(8, idUsuario);
			callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
			ResultSet rs = callableStatement.executeQuery();
			if(callableStatement.getMoreResults()){

				 rs =	callableStatement.getResultSet();
			}
			while (rs.next()) {
				EstacaBean estacaBean = new EstacaBean();
				estacaBean.setIdArea(rs.getInt("iDArea"));
				estacaBean.setIdEstaca(rs.getInt("IDEstaca"));
				estacaBean.setNome(rs.getString("Nome"));
				estacaBean.setLatitude(rs.getString("Latitude"));
				estacaBean.setLongitude(rs.getString("Longitude"));
				
				estacaBean.setLat(rs.getString("Latitude"));
				estacaBean.setLng(rs.getString("Latitude"));
				estacaBean.setDataCadastro(new java.util.Date(rs.getDate("DataCadastro").getTime()));
				estacas.add(estacaBean);
			}
			retorno = callableStatement.getString(9);
			System.out.println("retorno: ---" + retorno);

			if(estacas.isEmpty()){
				EstacaBean estaca = new EstacaBean();
				estaca.set_BeanAbstract(true, "vazio", "-45");
				estacas.add(estaca);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return estacas;
	}
	
//	-- idoperacao = 3 -> retornar estaca especifica de uma area
	public EstacaBean execEstacaRetornarEspecifico(int idEstaca, int idArea) throws SQLException {
		try {
			callableStatement = connection.prepareCall("{ CALL spEstaca (?,?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 3);
			callableStatement.setInt(2, 1);
			callableStatement.setInt(3, idEstaca);
			callableStatement.setInt(4, idArea);
			callableStatement.setString(5, "");
			callableStatement.setString(6, "");
			callableStatement.setString(7, "");
			callableStatement.setInt(8, 1);
			callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
			ResultSet rs = callableStatement.executeQuery();

			while (rs.next()) {
				estacaBean = new EstacaBean();
				estacaBean.setIdArea(rs.getInt("idArea"));
				estacaBean.setIdEstaca(rs.getInt("IDEstaca"));
				estacaBean.setNome(rs.getString("Nome"));
				estacaBean.setDataCadastro(new java.util.Date(rs.getDate("DataCadastro").getTime()));
				}
			retorno = callableStatement.getString(9);
			System.out.println("retorno: " + retorno);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return estacaBean;
	}

//	-- idoperacao = 4 -> deletar estaca especifica
	public String execEstacaRemover(int idEstaca, int idArea) throws SQLException {
		String retorno = "-1";
		try {
			callableStatement = connection.prepareCall("{ CALL spEstaca (?,?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 4);
			callableStatement.setInt(2, 1);
			callableStatement.setInt(3, idEstaca);
			callableStatement.setInt(4, idArea);
			callableStatement.setString(5, "");
			callableStatement.setString(6, "");
			callableStatement.setString(7, "");
			callableStatement.setInt(8, 1);
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

//	-- idoperacao = 5 -> deletar todas as estacas de uma area
	public String execEstacaRemoverTodos(int idUsuario, int idArea, int idEstaca) throws SQLException {
		String retorno = "-1";
		try {
			callableStatement = connection.prepareCall("{ CALL spEstaca (?,?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 5);
			callableStatement.setInt(2, idUsuario);
			callableStatement.setInt(3, idEstaca);
			callableStatement.setInt(4, idArea);
			callableStatement.setString(5, "");
			callableStatement.setString(6, "");
			callableStatement.setString(7, "");
			callableStatement.setInt(8, 1);
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

	//	-- idoperacao = 6 -> atualzar estaca
	public String execEstacaAtualzar(int idUsuario, int idEstaca, int idArea, String nome, String latitude,
			String longitude) throws SQLException {
		retorno = "-1";
		try {
			callableStatement = connection.prepareCall("{ CALL spEstaca (?,?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 6);
			callableStatement.setInt(2, idUsuario);
			callableStatement.setInt(3, idEstaca);
			callableStatement.setInt(4, idArea);
			callableStatement.setString(5, nome);
			callableStatement.setString(6, latitude);
			callableStatement.setString(7, longitude);
			callableStatement.setInt(8, 1);
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

//	-- idoperacao = 7 -> retornar todas as estacas de uma propriedade 
	public List<EstacaBean> execEstacaRetornarTodosDaPropriedade(int idPropriedade, int idUsuario) throws SQLException {
		List<EstacaBean> estacas = new ArrayList<>();
		String retorno = "-1";
		try {
			callableStatement = connection.prepareCall("{ CALL spEstaca (?,?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 7);
			callableStatement.setInt(2, idUsuario);
			callableStatement.setInt(3, 1);
			callableStatement.setInt(4, 0);
			callableStatement.setString(5, "");
			callableStatement.setString(6, "");
			callableStatement.setString(7, "");
			callableStatement.setInt(8, idPropriedade);
			callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
			ResultSet rs = callableStatement.executeQuery();

			while (rs.next()) {
				estacaBean = new EstacaBean();
				estacaBean.setIdArea(rs.getInt("iDArea"));
				estacaBean.setIdEstaca(rs.getInt("IDEstaca"));
				estacaBean.setNome(rs.getString("Nome"));
				estacaBean.setLongitude(rs.getString("Longitude"));
				estacaBean.setLatitude(rs.getString("Latitude"));
				estacaBean.setDataCadastro(new java.util.Date(rs.getDate("DataCadastro").getTime()));
				estacas.add(estacaBean);
			}
			
			retorno = callableStatement.getString(9);
			System.out.println("retorno: " + retorno);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return estacas;
	}

}
