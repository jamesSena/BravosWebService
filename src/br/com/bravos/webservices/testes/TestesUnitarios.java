package br.com.bravos.webservices.testes;

import br.com.bravos.webservices.dao.ConexaoDAO;
import br.com.bravos.webservices.dao.UsuarioDAO;
import br.com.bravos.webservices.dao.PerfilUsuarioDAO;

/**
 * @author JamessonSena
 *
 */
public class TestesUnitarios {
	
	static int idOperacao = 3,idUsuario = 1,idPropriedade = 3,idPerfil = 1;
	static String email = "a@aaa", nome = "jamesson Sales de Sena", login = "jamesson9", senha = "admin";
	static boolean ativo = false;
	

	public static void main(String[] args) {
		procedureLogincTest();
	}
	
	
	//Teste de conexão com o sqlServer 2014
	public static void conexaoTest(){new ConexaoDAO().dbConnect();}

	//Teste execUsuarioCadastro
	private static void procedureLoginTest(){
		UsuarioDAO loginDAO = new UsuarioDAO(new ConexaoDAO().dbConnect());
		loginDAO.execUsuarioRetornarEspecifico(idOperacao, idUsuario, login, senha, idPropriedade, ativo, email, nome, idPerfil);
	}
	
	//Teste execUsuarioConsultar
	private static void procedureLogincTest(){
		UsuarioDAO loginDAO = new UsuarioDAO(new ConexaoDAO().dbConnect());
		loginDAO.execUsuarioCadastrar(idOperacao, idUsuario, login, senha, idPropriedade, ativo, email, nome, idPerfil);
	}
	//Teste de conexão com a procedure
	private static void procedurePerfilUsuarioTest(){
		PerfilUsuarioDAO perfilUsuarioDAO = new PerfilUsuarioDAO(new ConexaoDAO().dbConnect());
		perfilUsuarioDAO.execPerfilUsuario();
	}

}
