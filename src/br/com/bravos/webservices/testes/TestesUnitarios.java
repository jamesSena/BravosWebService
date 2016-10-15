package br.com.bravos.webservices.testes;

import java.sql.SQLException;

import br.com.bravos.webservices.dao.ConexaoDAO;
import br.com.bravos.webservices.dao.UsuarioDAO;
import br.com.bravos.webservices.dao.PerfilUsuarioDAO;
import br.com.bravos.webservices.dao.SensorDAO;

/**
 * @author JamessonSena
 *
 */
public class TestesUnitarios {
	
	static int idOperacao = 1,idUsuario = 9,idPropriedade = 4,idPerfil = 1, idArea = 1, idSensor = 1;
	static String email = "a@aaa", nome = "jamesson Sales de Sena", login = "jamesson", senha = "jamesson",
			latitude ="435435435", longitude = "435435435" ;
	static boolean ativo = false;
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		deletarSensorTest();
	}
	//Teste execSensorCadastrars
	private static void deletarSensorTest() throws ClassNotFoundException, SQLException{
		SensorDAO loginDAO = new SensorDAO();
		loginDAO.execSensorRemover(idArea, idSensor);
	}
//	//Teste execSensorCadastrar
//	private static void sensorbuscarTodosTest() throws ClassNotFoundException, SQLException{
//		SensorDAO loginDAO = new SensorDAO();
//		loginDAO.execSensorRetornarTodos(idArea);
//	}
//	//Teste execSensorCadastrar
//	private static void sensorCadastrarTest() throws ClassNotFoundException, SQLException{
//		SensorDAO loginDAO = new SensorDAO();
//		loginDAO.execSensorCadastrar(idUsuario, idSensor, idArea, nome, latitude, longitude);
//	}
//	//Teste execUsuarioCadastrar
//	private static void usuarioCadastrarTest() throws ClassNotFoundException, SQLException{
//		UsuarioDAO loginDAO = new UsuarioDAO();
//		loginDAO.execUsuarioCadastrar(idUsuario, login, senha, idPropriedade, ativo, email, nome, idPerfil);
//	}
//	//Teste usuarioRetornarTodos
//	private static void usuarioRetornarTodosTest() throws ClassNotFoundException, SQLException{
//		UsuarioDAO loginDAO = new UsuarioDAO(new ConexaoDAO().dbConnect());
//		loginDAO.execUsuarioRetornarTodos();
//	}
//	//Teste execUsuarioRetornarEspecifico
//	private static void usuarioRetornarEspecificoTest() throws ClassNotFoundException, SQLException{
//		UsuarioDAO loginDAO = new UsuarioDAO(new ConexaoDAO().dbConnect());
//		loginDAO.execUsuarioRetornarEspecifico(login, senha);
//	}
//	//Teste execUsuarioAtualizar
//	private static void usuarioAtualizarTest() throws ClassNotFoundException, SQLException{
//		UsuarioDAO loginDAO = new UsuarioDAO(new ConexaoDAO().dbConnect());
//		loginDAO.execUsuarioAtualizar(idUsuario, login, senha, idPropriedade, ativo, email, nome, idPerfil);
//	}
//	//Teste execUsuarioExcluir
//	private static void usuarioExcluirTest() throws ClassNotFoundException, SQLException{
//		UsuarioDAO loginDAO = new UsuarioDAO(new ConexaoDAO().dbConnect());
//		loginDAO.execUsuarioRemover(idUsuario, login, senha);
//	}
//	//Teste execUsuarioExcluirTodos
//	private static void usuarioExcluirTodosTest() throws ClassNotFoundException, SQLException{
//		UsuarioDAO loginDAO = new UsuarioDAO(new ConexaoDAO().dbConnect());
//		loginDAO.execUsuarioRemoverTodos(idUsuario);
//	}
//	//Teste execUsuarioBloquear
//	private static void usuarioBloquearTest() throws ClassNotFoundException, SQLException{
//		UsuarioDAO loginDAO = new UsuarioDAO(new ConexaoDAO().dbConnect());
//		loginDAO.execUsuarioBloquear(idUsuario, login, ativo);
//	}


}
