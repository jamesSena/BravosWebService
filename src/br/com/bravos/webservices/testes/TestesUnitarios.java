package br.com.bravos.webservices.testes;

import java.sql.SQLException;

import br.com.bravos.webservices.dao.AreaDAO;
import br.com.bravos.webservices.dao.ConexaoDAO;
import br.com.bravos.webservices.dao.PropriedadeDAO;
import br.com.bravos.webservices.dao.UsuarioDAO;
import br.com.bravos.webservices.dao.SensorDAO;

/**
 * @author JamessonSena
 *
 */
public class TestesUnitarios {
	
	static int idOperacao = 1,idUsuario = 9,idPropriedade =  2,idPerfil = 1, idArea = 1, idSensor = 1;
	static String email = "a@aaa", nome = "jamesson Sales de Sena", login = "jamesson", senha = "jamesson",
			latitude ="435435435", longitude = "435435435", nomepropriedade = "Fazenda Jamesson", responsavel = "Gerente Jamesson Sena", emailResponsavel = "jsena@hotmail.com",
					nomeArea = "Area-B";
	static boolean ativo = false;
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		areaRetornarPorPropriedade();
	}
	//Teste areaAtualizar
	private static void areaRetornarPorPropriedade() throws ClassNotFoundException, SQLException{
		new AreaDAO().execRetornarAreasPropriedade(idUsuario, idPropriedade);
	}
	//Teste areaAtualizar
	private static void areaAtualizar() throws ClassNotFoundException, SQLException{
		new AreaDAO().execAtualizarAreaPropriedadeEspecifica(idUsuario, idPropriedade, nomeArea, idArea);
	}
	//Teste areaCadastrar
	private static void areaCadastrar() throws ClassNotFoundException, SQLException{
		new AreaDAO().execAreaCadastrar(idUsuario, idPropriedade, nomeArea);
	}
	
	
	
	
	private static void deletarSensorTest() throws ClassNotFoundException, SQLException{
		new SensorDAO().execSensorRemover(idArea, idSensor);
	}
	//Teste execSensorCadastrar
	private static void sensorbuscarTodosTest() throws ClassNotFoundException, SQLException{
		new SensorDAO().execSensorRetornarTodos(idArea);
	}
	//Teste execSensorCadastrar
	private static void sensorCadastrarTest() throws ClassNotFoundException, SQLException{
		new SensorDAO().execSensorCadastrar(idUsuario, idSensor, idArea, nome, latitude, longitude);
	}
	
	
	//Teste Excluir Propriedade
	private static void propriedadeExcluirTest() throws ClassNotFoundException, SQLException {
		new PropriedadeDAO().execPropriedadeDeletar(idUsuario, idPropriedade);
	}
	//Teste Buscar Propriedade
	private static void propriedadeRetornarTest() throws ClassNotFoundException, SQLException {
		new PropriedadeDAO().execPropriedadeRetornar(idPropriedade, idUsuario);
	}
	//Teste Atualizar Propriedade
	private static void propriedadeAtualizarTest() throws ClassNotFoundException, SQLException {
		new PropriedadeDAO().execPropriedadeAtualizar(idUsuario, idPropriedade, nomepropriedade, responsavel, emailResponsavel, latitude, longitude);
	}
	//Teste Cadastrar Propriedade
	private static void propriedadeCadastrarTest() throws ClassNotFoundException, SQLException {
		new PropriedadeDAO().execPropriedadeCadastrar(idUsuario, nomepropriedade, responsavel, emailResponsavel, latitude, longitude);
	}
	//Teste execUsuarioCadastrar
	private static void usuarioCadastrarTest() throws ClassNotFoundException, SQLException{
		UsuarioDAO loginDAO = new UsuarioDAO();
		loginDAO.execUsuarioCadastrar(idUsuario, login, senha, idPropriedade, ativo, email, nome, idPerfil);
	}
	//Teste usuarioRetornarTodos
	private static void usuarioRetornarTodosTest() throws ClassNotFoundException, SQLException{
		UsuarioDAO loginDAO = new UsuarioDAO();
		loginDAO.execUsuarioRetornarTodos();
	}
	//Teste execUsuarioRetornarEspecifico
	private static void usuarioRetornarEspecificoTest() throws ClassNotFoundException, SQLException{
		UsuarioDAO loginDAO = new UsuarioDAO();
		loginDAO.execUsuarioRetornarEspecifico(login, senha);
	}
	//Teste execUsuarioAtualizar
	private static void usuarioAtualizarTest() throws ClassNotFoundException, SQLException{
		UsuarioDAO loginDAO = new UsuarioDAO();
		loginDAO.execUsuarioAtualizar(idUsuario, login, senha, idPropriedade, ativo, email, nome, idPerfil);
	}
	//Teste execUsuarioExcluir
	private static void usuarioExcluirTest() throws ClassNotFoundException, SQLException{
		UsuarioDAO loginDAO = new UsuarioDAO();
		loginDAO.execUsuarioRemover(idUsuario, login, senha);
	}
	//Teste execUsuarioExcluirTodos
	private static void usuarioExcluirTodosTest() throws ClassNotFoundException, SQLException{
		UsuarioDAO loginDAO = new UsuarioDAO();
		loginDAO.execUsuarioRemoverTodos(idUsuario);
	}
	//Teste execUsuarioBloquear
	private static void usuarioBloquearTest() throws ClassNotFoundException, SQLException{
		UsuarioDAO loginDAO = new UsuarioDAO();
		loginDAO.execUsuarioBloquear(idUsuario, login, ativo);
	}


}
