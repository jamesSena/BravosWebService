package br.com.bravos.webservices.controller;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonDeserializer;

import br.com.bravos.webservices.dao.ConexaoDAO;
import br.com.bravos.webservices.dao.UsuarioDAO;
import br.com.bravos.webservices.model.UsuarioBean;
import br.com.bravos.webservices.utils.Json;

/**
 * @author JamessonSena
 *
 */
@RestController
public class UsuarioRestController {
	
	private UsuarioBean usuario;
	private UsuarioDAO usuarioDAO;
	private String retorno;
	/**
	 * Construtor default
	 */
	public UsuarioRestController() {
		super();
	}

	/**
	 * @param JSON: ativo, email, idPerfil, idPropriedade, login, nome, senha
	 * @return JSON: usuario
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(
			value="/cadastrarUsuario",
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String cadastrarUsuario(@RequestBody String jsonCadastro) throws JsonProcessingException{
		usuario = new UsuarioBean();
		try {		
			JSONObject jsonObject = new JSONObject(jsonCadastro);
			System.out.println(jsonObject.toString());
			usuario.setAtivo(jsonObject.getBoolean("ativo"));
			usuario.setEmail(jsonObject.getString("email"));
			usuario.setIdPerfil(jsonObject.getInt("idPerfil"));
			usuario.setIdPropriedade(jsonObject.getInt("idPropriedade"));
			usuario.setLogin(jsonObject.getString("login"));
			usuario.setNome(jsonObject.getString("nome"));
			usuario.setSenha(jsonObject.getString("senha"));
			usuarioDAO = new UsuarioDAO();
			usuario.setReason(usuarioDAO.execUsuarioCadastrar(1, usuario.getLogin(),  usuario.getSenha(), usuario.getIdPropriedade(), usuario.isAtivo(), usuario.getEmail(), usuario.getNome(), usuario.getIdPerfil()));
			if(usuario.getReason().equals("1")){
				usuario.setSuccess(true);
				usuario.setDetail("sucesso");
			}
			retorno = new Json().convertObjectToJson(usuario);
		} catch (JSONException e) {
			e.printStackTrace();
			usuario.setSuccess(false);
			usuario.setReason("-4");
			usuario.setDetail("Formato JSON invalido ou campo faltando, por favor verificar");
			retorno = new Json().convertObjectToJson(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
			usuario.setSuccess(false);
			usuario.setReason("-5");
			usuario.setDetail("Inconsistência no SQL");
			retorno = new Json().convertObjectToJson(usuario);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			usuario.setSuccess(false);
			usuario.setReason("-5");
			usuario.setDetail("Erro ao localizar o Driver de conexão");
			retorno = new Json().convertObjectToJson(usuario);			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			usuario.setSuccess(false);
			usuario.setReason("-6");
			usuario.setDetail("Erro ao realizar Parse de retorno");
			
		}
		return retorno;

		
	}
}
//	}
//	
//	/**
//	 * @return JSON: lista de usuario
//	 */
//	@RequestMapping(
//			value="/consultarUsuarios",
//			method=RequestMethod.GET,
//			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public List<UsuarioBean> consultarUsuarios(){
//		UsuarioDAO usuarioDAO = new UsuarioDAO(new ConexaoDAO().dbConnect());
//		List<UsuarioBean> usuario = usuarioDAO.execUsuarioRetornarTodos();
//		return usuario;
//	}
//
//	/**
//	 * @param login
//	 * @param senha
//	 * @return JSON: usuario
//	 */
//	@RequestMapping(
//			value="/consultarUsuario/{login}/{senha}",
//			method=RequestMethod.GET,
//			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public UsuarioBean consultarUsuario(@PathVariable("login") String login, @PathVariable("senha") String senha){
//		UsuarioDAO usuarioDAO = new UsuarioDAO(new ConexaoDAO().dbConnect());
//		return usuarioDAO.execUsuarioRetornarEspecifico(login, senha);
//	}
//
//	/**
//	 * @param JSON: idUsuario, ativo, email, idPerfil, idPropriedade, login, nome, senha
//	 * @return codigo de sucesso/erro
//	 */
//	@RequestMapping(
//			value="/atualizarUsuario",
//			method=RequestMethod.PUT,
//			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public String atualizarUsuario(@RequestBody String jsonAtualizar){
//		System.out.println(jsonAtualizar.toString());
//		usuario = new UsuarioBean();
//		int idUsuario = 0;
//		try {		
//			JSONObject jsonObject = new JSONObject(jsonAtualizar);
//			System.out.println(jsonObject.toString());
//		    idUsuario = jsonObject.getInt("idUsuario");
//			usuario.setAtivo(jsonObject.getBoolean("ativo"));
//			usuario.setEmail(jsonObject.getString("email"));
//			usuario.setIdPerfil(jsonObject.getInt("idPerfil"));
//			usuario.setIdPropriedade(jsonObject.getInt("idPropriedade"));
//			usuario.setLogin(jsonObject.getString("login"));
//			usuario.setNome(jsonObject.getString("nome"));
//			usuario.setSenha(jsonObject.getString("senha"));
//			return new UsuarioDAO().execUsuarioAtualizar(idUsuario, usuario.getLogin(),  usuario.getSenha(), usuario.getIdPropriedade(), usuario.isAtivo(), usuario.getEmail(), usuario.getNome(), usuario.getIdPerfil());
//
//		} catch (JSONException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	/**
//	 * @param JSON: idUsuario, login, senha
//	 * @return  codigo de sucesso/erro
//	 */
//	@RequestMapping(
//			value="/excluirUsuario",
//			method=RequestMethod.DELETE,
//			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public String excluirUsuario(@RequestBody String jsonExcluir){
//		System.out.println(jsonExcluir.toString());
//		usuario = new UsuarioBean();
//		try {		
//			JSONObject jsonObject = new JSONObject(jsonExcluir);
//			System.out.println(jsonObject.toString());
//			usuario.setIdUsuario(jsonObject.getInt("idUsuario"));
//			usuario.setLogin(jsonObject.getString("login"));
//			usuario.setSenha(jsonObject.getString("senha"));
//
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		
//		UsuarioDAO usuarioDAO = new UsuarioDAO(new ConexaoDAO().dbConnect());		
//		return usuarioDAO.execUsuarioRemover(usuario.getIdUsuario(), usuario.getLogin(), usuario.getSenha());
//	}
//	
//	/**
//	 * @param JSON: idUsuario
//	 * @return codigo de sucesso/erro
//	 */
//	@RequestMapping(
//			value="/excluirTodosUsuario",
//			method=RequestMethod.DELETE,
//			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public String excluirTodosUsuario(@RequestBody String jsonExcluirTodos){
//		System.out.println(jsonExcluirTodos.toString());
//		usuario = new UsuarioBean();
//		try {		
//			JSONObject jsonObject = new JSONObject(jsonExcluirTodos);
//			System.out.println(jsonObject.toString());
//			usuario.setIdUsuario(jsonObject.getInt("idUsuario"));
//
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		
//		UsuarioDAO usuarioDAO = new UsuarioDAO(new ConexaoDAO().dbConnect());		
//		return usuarioDAO.execUsuarioRemoverTodos(usuario.getIdUsuario());
//	}
//	
//	/**
//	 * @param JSON: idUsuario, login, senha
//	 * @return codigo de sucesso/erro
//	 */
//	@RequestMapping(
//			value="/bloquearUsuario",
//			method=RequestMethod.POST,
//			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	
//	public String bloquearUsuario(@RequestBody String jsonBloquear){
//		System.out.println(jsonBloquear.toString());
//		usuario = new UsuarioBean();
//		try {		
//			JSONObject jsonObject = new JSONObject(jsonBloquear);
//			System.out.println(jsonObject.toString());
//			usuario.setIdUsuario(jsonObject.getInt("idUsuario"));
//			usuario.setLogin(jsonObject.getString("login"));
//			usuario.setSenha(jsonObject.getString("senha"));
//
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		
//		UsuarioDAO usuarioDAO = new UsuarioDAO(new ConexaoDAO().dbConnect());		
//		return usuarioDAO.execUsuarioBloquear(usuario.getIdUsuario(), usuario.getLogin(), false);
//	}
//
//	/**
//	 * @param JSON: idUsuario, login, senha
//	 * @return  codigo de sucesso/erro
//	 */
//	@RequestMapping(
//			value="/desbloquearUsuario",
//			method=RequestMethod.POST,
//			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public String desbloquearUsuario(@RequestBody String jsonDesbloquar){
//		System.out.println(jsonDesbloquar.toString());
//		usuario = new UsuarioBean();
//		try {		
//			JSONObject jsonObject = new JSONObject(jsonDesbloquar);
//			System.out.println(jsonObject.toString());
//			usuario.setIdUsuario(jsonObject.getInt("idUsuario"));
//			usuario.setLogin(jsonObject.getString("login"));
//			usuario.setSenha(jsonObject.getString("senha"));
//
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		
//		UsuarioDAO usuarioDAO = new UsuarioDAO(new ConexaoDAO().dbConnect());		
//		return usuarioDAO.execUsuarioBloquear(usuario.getIdUsuario(), usuario.getLogin(), true);
//	}
//}
