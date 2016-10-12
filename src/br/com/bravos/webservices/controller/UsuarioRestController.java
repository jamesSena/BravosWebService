package br.com.bravos.webservices.controller;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
}
