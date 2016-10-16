package br.com.bravos.webservices.dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.bravos.webservices.model.AreaBean;


/**
 *  @author WeltonBatista
 *
 */
public class AreaDAO extends ConexaoDAO {
	
	/**
	 * 
	 */
	private AreaBean areaBean;
	private CallableStatement callableStatement = null;
	private Connection connection = null;
	
	/**
	 * #Construtor default
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	

	/**
	 * #Construtor que recebe a instancia da conexao
	 * 
	 * @param connection
	 */
	public AreaDAO() throws ClassNotFoundException, SQLException {
		connection = super.getConnection();
	}
	
	/**
	 * #idoperacao = 1 -> Cadastrar Area
	 * 
	 *@param idoperacao
	 *@param idusuario 
	 *@param idpropriedade 
	 *@param nomearea
	 *@param idarea 
	 *@param retorn
	 *@throws SQLException
	 */
	public String execAreaCadastrar(int idUsuario, int idPropriedade, String nomeArea) throws SQLException {
		String retorno = "-1";
		try {
			callableStatement = connection.prepareCall("{ CALL spArea (?,?,?,?,?,?)}");
			callableStatement.setInt(1, 1);
			callableStatement.setInt(2, idUsuario);
			callableStatement.setInt(3, idPropriedade);
			callableStatement.setString(4, nomeArea);
			callableStatement.setInt(5, 0);
			callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
			callableStatement.execute();
			retorno = callableStatement.getString(6);
			System.out.println("retorno: " + retorno);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return retorno;
	}

	/**
	 * #idoperacao = 2 -> Retornar todas areas de uma propriedade
	 * 
	 *@param idoperacao
	 *@param idusuario 
	 *@param idpropriedade 
	 *@param nomearea
	 *@param idarea 
	 *@param retorn
	 *@throws SQLException
	 */
	public List<AreaBean> execRetornarAreasPropriedade(int idUsuario, int idPropriedade) throws SQLException {
		List<AreaBean> areas = new ArrayList<>();
		try {
			callableStatement = connection.prepareCall("{ CALL spArea (?,?,?,?,?,?)}");
			callableStatement.setInt(1, 2);
			callableStatement.setInt(2, idUsuario);
			callableStatement.setInt(3, idPropriedade);
			callableStatement.setString(4, "");
			callableStatement.setInt(5, 0);
			callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
			callableStatement.execute();
			ResultSet rs = callableStatement.executeQuery();
			while (rs.next()) {
				areaBean = new AreaBean();
				areaBean.setIdArea(rs.getInt("IDArea"));
				areaBean.setIdPropriedade(rs.getInt("IDPropriedade"));
				areaBean.setNomeArea(rs.getString("NomeArea"));
				areas.add(areaBean);
			}

			System.out.println("retorno: " + areas);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return areas;
	}
	
	/**
	 * #idoperacao = 3 -> Retornar  area de uma propriedade
	 * 
	 *@param idoperacao
	 *@param idusuario 
	 *@param idpropriedade 
	 *@param nomearea
	 *@param idarea 
	 *@param retorn
	 *@throws SQLException
	 */
	public AreaBean execRetornarAreaPropriedadeEspecifica(int idUsuario, int idPropriedade, String nomeArea, int idArea) throws SQLException {
		try {
			callableStatement = connection.prepareCall("{ CALL spArea (?,?,?,?,?,?)}");
			callableStatement.setInt(1, 3);
			callableStatement.setInt(2, idUsuario);
			callableStatement.setInt(3, idPropriedade);
			callableStatement.setString(4, nomeArea);
			callableStatement.setInt(5, idArea);
			callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
			callableStatement.execute();
			ResultSet rs = callableStatement.executeQuery();
			areaBean = new AreaBean();
			while (rs.next()) {
				
				areaBean.setIdArea(rs.getInt("IDArea"));
				areaBean.setIdPropriedade(rs.getInt("IDPropriedade"));
				areaBean.setNomeArea(rs.getString("NomeArea"));
				
			}

			String retorno = callableStatement.getString(6);
			System.out.println("retorno: " + retorno);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return areaBean;
	}	
	
	
	/**
	 * #idoperacao = 4 -> deletar  area de uma propriedade
	 * 
	 *@param idoperacao
	 *@param idusuario 
	 *@param idpropriedade 
	 *@param nomearea
	 *@param idarea 
	 *@param retorn
	 *@throws SQLException
	 */
	public String execDeletarAreaPropriedadeEspecifica(int idUsuario, int idPropriedade, String nomeArea, int idArea) throws SQLException{
		String retorno = "-1";
		try {
			callableStatement = connection.prepareCall("{ CALL spArea (?,?,?,?,?,?)}");
			callableStatement.setInt(1, 4);
			callableStatement.setInt(2, idUsuario);
			callableStatement.setInt(3, idPropriedade);
			callableStatement.setString(4, nomeArea);
			callableStatement.setInt(5, idArea);
			callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
			callableStatement.execute();
			retorno = callableStatement.getString(6);
			System.out.println("retorno: " + retorno);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			super.dbClose(connection, callableStatement);
		}
		return retorno;
	}	

	/**
	 * #idoperacao = 5 -> Atualizar  area de uma propriedade
	 * 
	 *@param idoperacao
	 *@param idusuario 
	 *@param idpropriedade 
	 *@param nomearea
	 *@param idarea 
	 *@param retorn
	 *@throws SQLException
	 */
	public String execAtualizarAreaPropriedadeEspecifica(int idUsuario, int idPropriedade, String nomeArea, int idArea) throws SQLException{
		String retorno = "-1";
		try {
			callableStatement = connection.prepareCall("{ CALL spArea (?,?,?,?,?,?)}");
			callableStatement.setInt(1, 5);
			callableStatement.setInt(2, idUsuario);
			callableStatement.setInt(3, idPropriedade);
			callableStatement.setString(4, nomeArea);
			callableStatement.setInt(5, idArea);
			callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
			callableStatement.execute();
			retorno = callableStatement.getString(6);
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
