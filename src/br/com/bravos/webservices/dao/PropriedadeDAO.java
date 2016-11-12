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

import br.com.bravos.webservices.model.PropriedadeBean;
import br.com.bravos.webservices.model.UsuarioBean;

/**
 * @author JamessonSena
 *
 */
public class PropriedadeDAO  extends ConexaoDAO {
	private CallableStatement callableStatement = null;
	private Connection connection = null;
	private String retorno = "-1";
	private PropriedadeBean propriedadeBean;
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * 
	 */
	public PropriedadeDAO() throws ClassNotFoundException, SQLException {
		connection = super.getConnection();
	}
	/**
	 * #Construtor que recebe a instancia da conexao
	 * 
	 * @param connection
	 */
	public PropriedadeDAO(Connection connection) {
		super.setConnection(connection);
	}


	// -- idoperacao = 1 -> Cadastrar propriedade
		public String execPropriedadeCadastrar(int idUsuario, String nomepropriedade, String responsavel, String emailResponsavel,
			String latitude, String longitude) throws SQLException {
			retorno = "-1";
			try {
				callableStatement = connection.prepareCall("{ CALL spPropriedade (?,?,?,?,?,?,?,?,?)}");
				callableStatement.setInt(1, 1);
				callableStatement.setInt(2, idUsuario);
				callableStatement.setInt(3, 0);
				callableStatement.setString(4, nomepropriedade);
				callableStatement.setString(5, responsavel);
				callableStatement.setString(6, emailResponsavel);
				callableStatement.setString(7, latitude);
				callableStatement.setString(8, longitude);
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
		
//		-- idoperacao = 2 -> retornar propriedade
		public PropriedadeBean execPropriedadeRetornar( int idPropriedade, int idUsuario) throws SQLException {
				retorno = "-1";
				try {
					callableStatement = connection.prepareCall("{ CALL spPropriedade (?,?,?,?,?,?,?,?,?)}");
					callableStatement.setInt(1, 2);
					callableStatement.setInt(2, idUsuario);
					callableStatement.setInt(3, idPropriedade);
					callableStatement.setString(4, "");
					callableStatement.setString(5, "");
					callableStatement.setString(6, "");
					callableStatement.setString(7, "");
					callableStatement.setString(8, "");
					callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
					ResultSet rs = callableStatement.executeQuery();
					while (rs.next()) {
						propriedadeBean = new PropriedadeBean();
						propriedadeBean.setIdPropriedade(rs.getInt("iDPropriedade"));
						propriedadeBean.setNomePropriedade(rs.getString("NomeProriedade"));
						propriedadeBean.setResponsavel(rs.getString("Responsavel"));
						propriedadeBean.setEmailResponsavel(rs.getString("EmailReponsavel"));
						propriedadeBean.setLatitude(rs.getString("LocalizacaoLatitude"));
						propriedadeBean.setLongitude(rs.getString("LocalizacaoLongitude"));
						propriedadeBean.setDataCadastro(rs.getString("DataCadastro"));

					}
					propriedadeBean.setReason(callableStatement.getString(9));
					System.out.println(propriedadeBean.toString());
				} catch (SQLException e) {
					e.printStackTrace();
					throw e;
				} finally {
					super.dbClose(connection, callableStatement);
				}
				return propriedadeBean;
			}

//		-- idoperacao = 3 -> deletar propriedade
		public PropriedadeBean execPropriedadeDeletar( int idUsuario, int idPropriedade) throws SQLException {
			retorno = "-1";
			try {
				callableStatement = connection.prepareCall("{ CALL spPropriedade (?,?,?,?,?,?,?,?,?)}");
				callableStatement.setInt(1, 3);
				callableStatement.setInt(2, idUsuario);
				callableStatement.setInt(3, idPropriedade);
				callableStatement.setString(4, "");
				callableStatement.setString(5, "");
				callableStatement.setString(6, "");
				callableStatement.setString(7, "");
				callableStatement.setString(8, "");
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
			return propriedadeBean;
		}

//		-- idoperacao = 4 -> atualzar propriedade
		public String execPropriedadeAtualizar(int idUsuario, int idPropriedade, String nomepropriedade, String responsavel, String emailResponsavel,
				String latitude, String longitude) throws SQLException {
				retorno = "-1";
				try {
					callableStatement = connection.prepareCall("{ CALL spPropriedade (?,?,?,?,?,?,?,?,?)}");
					callableStatement.setInt(1, 4);
					callableStatement.setInt(2, idUsuario);
					callableStatement.setInt(3, idPropriedade);
					callableStatement.setString(4, nomepropriedade);
					callableStatement.setString(5, responsavel);
					callableStatement.setString(6, emailResponsavel);
					callableStatement.setString(7, latitude);
					callableStatement.setString(8, longitude);
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

//		-- idoperacao = 5 -> retornar todas propriedades
		public List<PropriedadeBean> execPropriedadesRetornar() throws SQLException {
				List<PropriedadeBean> propriedadeList = new ArrayList<>();
				try {
					callableStatement = connection.prepareCall("{ CALL spPropriedade (?,?,?,?,?,?,?,?,?)}");
					callableStatement.setInt(1, 5);
					callableStatement.setInt(2, 0);
					callableStatement.setInt(3, 0);
					callableStatement.setString(4, "");
					callableStatement.setString(5, "");
					callableStatement.setString(6, "");
					callableStatement.setString(7, "");
					callableStatement.setString(8, "");
					callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
					ResultSet rs = callableStatement.executeQuery();
					while (rs.next()) {
						propriedadeBean = new PropriedadeBean();
						propriedadeBean.setIdPropriedade(rs.getInt("iDPropriedade"));
						propriedadeBean.setNomePropriedade(rs.getString("NomeProriedade"));
						propriedadeBean.setResponsavel(rs.getString("Responsavel"));
						propriedadeBean.setEmailResponsavel(rs.getString("EmailReponsavel"));
						propriedadeBean.setLatitude(rs.getString("LocalizacaoLatitude"));
						propriedadeBean.setLongitude(rs.getString("LocalizacaoLongitude"));
						propriedadeBean.setDataCadastro(rs.getString("DataCadastro"));
						propriedadeList.add(propriedadeBean);

					}
					propriedadeBean.setReason(callableStatement.getString(9));
					System.out.println(propriedadeBean.toString());
				} catch (SQLException e) {
					e.printStackTrace();
					throw e;
				} finally {
					super.dbClose(connection, callableStatement);
				}
				return propriedadeList;
			}
}
