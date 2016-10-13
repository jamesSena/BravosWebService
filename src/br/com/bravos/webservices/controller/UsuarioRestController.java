package br.com.bravos.webservices.controller;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import br.com.bravos.webservices.dao.ConexaoDAO;
import br.com.bravos.webservices.dao.UsuarioDAO;
import br.com.bravos.webservices.model.UsuarioBean;

@RestController
public class UsuarioRestController {
	
	private UsuarioBean usuario;
	public UsuarioRestController() {
		super();
	}

	@RequestMapping(
			value="/cadastrarUsuario",
			method=RequestMethod.PUT,
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String cadastrarUsuario(@RequestBody String strCadastro){
		System.out.println(strCadastro.toString());
		usuario = new UsuarioBean();
		try {		
			JSONObject jsonObject = new JSONObject(strCadastro);
			System.out.println(jsonObject.toString());
			usuario.setAtivo(jsonObject.getBoolean("ativo"));
			usuario.setEmail(jsonObject.getString("email"));
			usuario.setIdPerfil(jsonObject.getInt("idPerfil"));
			usuario.setIdPropriedade(jsonObject.getInt("idPropriedade"));
			usuario.setLogin(jsonObject.getString("login"));
			usuario.setNome(jsonObject.getString("nome"));
			usuario.setSenha(jsonObject.getString("senha"));

		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		UsuarioDAO usuarioDAO = new UsuarioDAO(new ConexaoDAO().dbConnect());
		int idUsuario = usuarioDAO.execBuscaTotal()+1;
		
		
		return usuarioDAO.execUsuarioCadastrar(idUsuario, usuario.getLogin(),  usuario.getSenha(), usuario.getIdPropriedade(), usuario.isAtivo(), usuario.getEmail(), usuario.getNome(), usuario.getIdPerfil());
	}
	
	@RequestMapping(
			value="/consultarUsuarios",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<UsuarioBean> consultarUsuarios(){
		UsuarioDAO usuarioDAO = new UsuarioDAO(new ConexaoDAO().dbConnect());
		List<UsuarioBean> usuario = usuarioDAO.execUsuarioRetornarTodos();
		return usuario;
	}

	@RequestMapping(
			value="/consultarUsuario/{login}/{senha}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UsuarioBean consultarUsuario(@PathVariable("login") String login, @PathVariable("senha") String senha){
		UsuarioDAO usuarioDAO = new UsuarioDAO(new ConexaoDAO().dbConnect());
		return usuarioDAO.execUsuarioRetornarEspecifico(login, senha);
	}

	@RequestMapping(
			value="/atualizarUsuario",
			method=RequestMethod.PUT,
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String atualizarUsuario(@RequestBody String strCadastro){
		System.out.println(strCadastro.toString());
		usuario = new UsuarioBean();
		int idUsuario = 0;
		try {		
			JSONObject jsonObject = new JSONObject(strCadastro);
			System.out.println(jsonObject.toString());
		    idUsuario = jsonObject.getInt("idUsuario");
			usuario.setAtivo(jsonObject.getBoolean("ativo"));
			usuario.setEmail(jsonObject.getString("email"));
			usuario.setIdPerfil(jsonObject.getInt("idPerfil"));
			usuario.setIdPropriedade(jsonObject.getInt("idPropriedade"));
			usuario.setLogin(jsonObject.getString("login"));
			usuario.setNome(jsonObject.getString("nome"));
			usuario.setSenha(jsonObject.getString("senha"));

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return new UsuarioDAO(new ConexaoDAO().dbConnect()).execUsuarioAtualizar(idUsuario, usuario.getLogin(),  usuario.getSenha(), usuario.getIdPropriedade(), usuario.isAtivo(), usuario.getEmail(), usuario.getNome(), usuario.getIdPerfil());
	}

	@RequestMapping(
			value="/excluirUsuario",
			method=RequestMethod.DELETE,
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String excluirUsuario(@RequestBody String strCadastro){
		System.out.println(strCadastro.toString());
		usuario = new UsuarioBean();
		try {		
			JSONObject jsonObject = new JSONObject(strCadastro);
			System.out.println(jsonObject.toString());
			usuario.setIdUsuario(jsonObject.getInt("idUsuario"));
			usuario.setLogin(jsonObject.getString("login"));
			usuario.setSenha(jsonObject.getString("senha"));

		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		UsuarioDAO usuarioDAO = new UsuarioDAO(new ConexaoDAO().dbConnect());		
		return usuarioDAO.execUsuarioRemover(usuario.getIdUsuario(), usuario.getLogin(), usuario.getSenha());
	}

	
	@RequestMapping(
			value="/excluirTodosUsuario",
			method=RequestMethod.DELETE,
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String excluirTodosUsuario(@RequestBody String strCadastro){
		System.out.println(strCadastro.toString());
		usuario = new UsuarioBean();
		try {		
			JSONObject jsonObject = new JSONObject(strCadastro);
			System.out.println(jsonObject.toString());
			usuario.setIdUsuario(jsonObject.getInt("idUsuario"));

		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		UsuarioDAO usuarioDAO = new UsuarioDAO(new ConexaoDAO().dbConnect());		
		return usuarioDAO.execUsuarioRemoverTodos(usuario.getIdUsuario());
	}

}
