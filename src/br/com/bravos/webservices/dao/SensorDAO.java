/**
 * 
 */
package br.com.bravos.webservices.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bravos.webservices.model.SensorBean;

/**
 * @author JamessonSena
 *
 */
public class SensorDAO extends ConexaoDAO {
	private CallableStatement callableStatement = null;
	private Connection connection = null;
	private SensorBean sensorBean;
	private String retorno = "-1";

	/**
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * 
	 */
	public SensorDAO() throws ClassNotFoundException, SQLException {
		connection = super.getConnection();
	}

	/**
	 * #Construtor que recebe a instancia da conexao
	 * 
	 * @param connection
	 */
	public SensorDAO(Connection connection) {
		super.setConnection(connection);
	}

	// -- idoperacao = 1 -> Cadastrar Sensor
	public String execSensorCadastrar(int idUsuario, int idSensor, int idArea, String nome, String latitude,
			String longitude) throws SQLException {
		retorno = "-1";
		try {
			callableStatement = connection.prepareCall("{ CALL spSensor (?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 1);
			callableStatement.setInt(2, idUsuario);
			callableStatement.setInt(3, idSensor);
			callableStatement.setInt(4, idArea);
			callableStatement.setString(5, nome);
			callableStatement.setString(6, latitude);
			callableStatement.setString(7, longitude);
			callableStatement.registerOutParameter(8, java.sql.Types.VARCHAR);
			callableStatement.execute();
			retorno = callableStatement.getString(8);
			System.out.println("retorno: " + retorno);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return retorno;
	}

	// -- idoperacao = 2 -> retornar todas os Sensores de uma area
	public List<SensorBean> execSensorRetornarTodos(int idArea) throws SQLException {
		List<SensorBean> sensor = new ArrayList<>();
		String retorno = "-1";
		try {
			callableStatement = connection.prepareCall("{ CALL spSensor (?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 2);
			callableStatement.setInt(2, 1);
			callableStatement.setInt(3, 1);
			callableStatement.setInt(4, idArea);
			callableStatement.setString(5, "");
			callableStatement.setString(6, "");
			callableStatement.setString(7, "");
			callableStatement.registerOutParameter(8, java.sql.Types.VARCHAR);
			ResultSet rs = callableStatement.executeQuery();

			while (rs.next()) {
				sensorBean = new SensorBean();
				sensorBean.setIdCodArea(rs.getInt("idArea"));
				sensorBean.setiDPropriedade(rs.getInt("IDPropriedade"));
				sensorBean.setNome(rs.getString("NomeArea"));
				sensorBean.setData(rs.getString("Data"));
				sensor.add(sensorBean);
			}
			retorno = callableStatement.getString(8);
			System.out.println("retorno: " + retorno);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return sensor;
	}

	// -- idoperacao = 3 -> retornar Sensor especifico de uma area
	public SensorBean execSensorRetornarEspecifico(int idArea, int idSensor) throws SQLException {
		try {
			callableStatement = connection.prepareCall("{ CALL spSensor (?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 3);
			callableStatement.setInt(2, 1);
			callableStatement.setInt(3, idSensor);
			callableStatement.setInt(4, idArea);
			callableStatement.setString(5, "");
			callableStatement.setString(6, "");
			callableStatement.setString(7, "");
			callableStatement.registerOutParameter(8, java.sql.Types.VARCHAR);
			ResultSet rs = callableStatement.executeQuery();

			while (rs.next()) {
				sensorBean = new SensorBean();
				sensorBean.setIdCodArea(rs.getInt("idArea"));
				sensorBean.setiDPropriedade(rs.getInt("IDPropriedade"));
				sensorBean.setNome(rs.getString("NomeArea"));
				sensorBean.setData(rs.getString("Data"));
			}
			retorno = callableStatement.getString(8);
			System.out.println("retorno: " + retorno);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return sensorBean;
	}


	//-- idoperacao = 4 -> deletar Sensor especifico
	public String execSensorRemover(int idArea, int idSensor) throws SQLException {
		String retorno = "-1";
		try {
			callableStatement = connection.prepareCall("{ CALL spSensor (?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 4);
			callableStatement.setInt(2, 1);
			callableStatement.setInt(3, idSensor);
			callableStatement.setInt(4, idArea);
			callableStatement.setString(5, "");
			callableStatement.setString(6, "");
			callableStatement.setString(7, "");
			callableStatement.registerOutParameter(8, java.sql.Types.VARCHAR);
			callableStatement.execute();
			retorno = callableStatement.getString(8);
			System.out.println("retorno: " + retorno);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return retorno;
	}

	//-- idoperacao = 5 -> deletar todos os Sensores de uma area
	public String execSensorRemoverTodos(int idArea, int idSensor) throws SQLException {
		String retorno = "-1";
		try {
			callableStatement = connection.prepareCall("{ CALL spSensor (?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 5);
			callableStatement.setInt(2, 1);
			callableStatement.setInt(3, idSensor);
			callableStatement.setInt(4, idArea);
			callableStatement.setString(5, "");
			callableStatement.setString(6, "");
			callableStatement.setString(7, "");
			callableStatement.registerOutParameter(8, java.sql.Types.VARCHAR);
			callableStatement.execute();
			retorno = callableStatement.getString(8);
			System.out.println("retorno: " + retorno);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return retorno;
	}

	//-- idoperacao = 6 -> atualzar sensor}
	public String execSensorAtualzar(int idUsuario, int idSensor, int idArea, String nome, String latitude,
			String longitude) throws SQLException {
		retorno = "-1";
		try {
			callableStatement = connection.prepareCall("{ CALL spSensor (?,?,?,?,?,?,?,?)}");
			callableStatement.setInt(1, 6);
			callableStatement.setInt(2, idUsuario);
			callableStatement.setInt(3, idSensor);
			callableStatement.setInt(4, idArea);
			callableStatement.setString(5, nome);
			callableStatement.setString(6, latitude);
			callableStatement.setString(7, longitude);
			callableStatement.registerOutParameter(8, java.sql.Types.VARCHAR);
			callableStatement.execute();
			retorno = callableStatement.getString(8);
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