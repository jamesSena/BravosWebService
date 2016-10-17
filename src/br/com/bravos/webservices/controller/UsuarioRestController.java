package br.com.bravos.webservices.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import br.com.bravos.webservices.dao.UsuarioDAO;
import br.com.bravos.webservices.enums.EnumErroUsuario;
import br.com.bravos.webservices.model.UsuarioBean;

/**
 * @author JamessonSena
 *
 */
@RestController
public class UsuarioRestController {

	private UsuarioBean usuario;
	private UsuarioDAO usuarioDAO;
	private String retorno;
	private List<UsuarioBean> usuarioList;

	/**
	 * Construtor default
	 */
	public UsuarioRestController() {
		super();
	}

	/**
	 * @param JSON:
	 *            ativo, email, idPerfil, idPropriedade, login, nome, senha
	 * @return JSON: UsuarioBean
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UsuarioBean cadastrarUsuario(@RequestBody String jsonCadastro) throws JsonProcessingException {
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
			// Json ok
			usuario.setReason(new UsuarioDAO().execUsuarioCadastrar(1, usuario.getLogin(), usuario.getSenha(),usuario.getIdPropriedade(), usuario.isAtivo(), usuario.getEmail(), usuario.getNome(),
							   								usuario.getIdPerfil()));
			traducaoRetornoErro(usuario.getReason());
		} catch (JSONException e) {
			e.printStackTrace();
			usuario.setSuccess(false);
			usuario.setReason("-5");
			usuario.setDetail(EnumErroUsuario._5_JSONException.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			usuario.setSuccess(false);
			usuario.setReason("-6");
			usuario.setDetail(EnumErroUsuario._6_SQLException.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			usuario.setSuccess(false);
			usuario.setReason("-7");
			usuario.setDetail(EnumErroUsuario._7_ClassNotFoundException.toString());
		} 
		return usuario;

	}

	/**
	 * @return JSON: lista de UsuarioBean
	 */
	@RequestMapping(value = "/consultarUsuarios", 
			        method = RequestMethod.GET,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<UsuarioBean> consultarUsuarios() {
		try {
			usuarioList = new UsuarioDAO().execUsuarioRetornarTodos();
		} catch (SQLException e) {
			e.printStackTrace();
			usuarioList = new ArrayList<UsuarioBean>(); 
			usuarioList.add(new UsuarioBean(false, "-6", EnumErroUsuario._6_SQLException.toString()));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			usuarioList = new ArrayList<UsuarioBean>(); 
			usuarioList.add(new UsuarioBean(false, "-7", EnumErroUsuario._7_ClassNotFoundException.toString()));
		}
		return usuarioList;
	}

	/**
	 * @param login
	 * @param senha
	 * @return JSON: UsuarioBean
	 */
	@RequestMapping(value = "/consultarUsuario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UsuarioBean consultarUsuario(@RequestBody String jsonConsultarUsuario) {
		try {
			JSONObject jsonObject = new JSONObject(jsonConsultarUsuario);
			System.out.println(jsonObject.toString());
			String login = jsonObject.getString("login");
			String senha = jsonObject.getString("senha");
			usuarioDAO = new UsuarioDAO();
			usuario = usuarioDAO.execUsuarioRetornarEspecifico(login, senha);
			traducaoRetornoErro(usuario.getReason());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(false, "-7", EnumErroUsuario._7_ClassNotFoundException.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(false, "-6", EnumErroUsuario._6_SQLException.toString());
		} catch (JSONException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(false, "-5", EnumErroUsuario._5_JSONException.toString());

		}
		return usuario;
	}

	/**
	 * @param JSON:
	 *            idUsuario, ativo, email, idPerfil, idPropriedade, login, nome,
	 *            senha
	 * @return UsuarioBean
	 */
	@RequestMapping(value = "/atualizarUsuario", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UsuarioBean atualizarUsuario(@RequestBody String jsonAtualizar) {
		System.out.println(jsonAtualizar.toString());
		usuario = new UsuarioBean();
		int idUsuario = 0;
		try {
			JSONObject jsonObject = new JSONObject(jsonAtualizar);
			System.out.println(jsonObject.toString());
			idUsuario = jsonObject.getInt("idUsuario");
			usuario.setAtivo(jsonObject.getBoolean("ativo"));
			usuario.setEmail(jsonObject.getString("email"));
			usuario.setIdPerfil(jsonObject.getInt("idPerfil"));
			usuario.setIdPropriedade(jsonObject.getInt("idPropriedade"));
			usuario.setLogin(jsonObject.getString("login"));
			usuario.setNome(jsonObject.getString("nome"));
			usuario.setSenha(jsonObject.getString("senha"));
			retorno = new UsuarioDAO().execUsuarioAtualizar(idUsuario, usuario.getLogin(), usuario.getSenha(),
					usuario.getIdPropriedade(), usuario.isAtivo(), usuario.getEmail(), usuario.getNome(),
					usuario.getIdPerfil());

			traducaoRetornoErro(retorno);
		} catch (JSONException e) {
			e.printStackTrace();
			usuario.setSuccess(false);
			usuario.setReason("-5");
			usuario.setDetail(EnumErroUsuario._5_JSONException.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			usuario.setSuccess(false);
			usuario.setReason("-6");
			usuario.setDetail(EnumErroUsuario._6_SQLException.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			usuario.setSuccess(false);
			usuario.setReason("-7");
			usuario.setDetail(EnumErroUsuario._7_ClassNotFoundException.toString());
		} 
		return usuario;

	}

	/**
	 * @param JSON:
	 *            idUsuario, login, senha
	 * @return codigo de sucesso/erro
	 */
	@RequestMapping(value = "/excluirUsuario", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UsuarioBean excluirUsuario(@RequestBody String jsonExcluir) {
		System.out.println(jsonExcluir.toString());
		usuario = new UsuarioBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonExcluir);
			System.out.println(jsonObject.toString());
			usuario.setIdUsuario(jsonObject.getInt("idUsuario"));
			usuario.setLogin(jsonObject.getString("login"));
			usuario.setSenha(jsonObject.getString("senha"));
			usuarioDAO = new UsuarioDAO();
			retorno = usuarioDAO.execUsuarioRemover(usuario.getIdUsuario(), usuario.getLogin(), usuario.getSenha());
			traducaoRetornoErro(retorno);

		} catch (JSONException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(true, EnumErroUsuario._5_JSONException.toString(), "-5");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(true, EnumErroUsuario._7_ClassNotFoundException.toString(), "-7");
		} catch (SQLException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(true, EnumErroUsuario._6_SQLException.toString(), "-6");
		}
		return usuario;
	}

	/**
	 * @param JSON:
	 *            idUsuario
	 * @return UsuarioBean
	 */
	@RequestMapping(value = "/excluirTodosUsuario", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UsuarioBean excluirTodosUsuario(@RequestBody String jsonExcluirTodos) {
		System.out.println(jsonExcluirTodos.toString());
		usuario = new UsuarioBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonExcluirTodos);
			System.out.println(jsonObject.toString());
			usuario.setIdUsuario(jsonObject.getInt("idUsuario"));
			retorno = new UsuarioDAO().execUsuarioRemoverTodos(usuario.getIdUsuario());
			traducaoRetornoErro(retorno);

		} catch (JSONException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(true, EnumErroUsuario._5_JSONException.toString(), "-5");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(true, EnumErroUsuario._7_ClassNotFoundException.toString(), "-7");
		} catch (SQLException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(true, EnumErroUsuario._6_SQLException.toString(), "-6");
		}
		return usuario;
	}

	/**
	 * @param JSON:
	 *            idUsuario, login, senha
	 * @return codigo de sucesso/erro
	 */
	@RequestMapping(value = "/bloquearUsuario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

	public UsuarioBean bloquearUsuario(@RequestBody String jsonBloquear) {
		System.out.println(jsonBloquear.toString());
		usuario = new UsuarioBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonBloquear);
			System.out.println(jsonObject.toString());
			usuario.setIdUsuario(jsonObject.getInt("idUsuario"));
			usuario.setLogin(jsonObject.getString("login"));
			usuario.setSenha(jsonObject.getString("senha"));
			retorno = new UsuarioDAO().execUsuarioBloquear(usuario.getIdUsuario(), usuario.getLogin(), false);
			traducaoRetornoErro(retorno);
		} catch (JSONException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(true, EnumErroUsuario._5_JSONException.toString(), "-5");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(true, EnumErroUsuario._7_ClassNotFoundException.toString(), "-7");
		} catch (SQLException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(true, EnumErroUsuario._6_SQLException.toString(), "-6");
		}
		return usuario;
	}

	/**
	 * @param JSON:
	 *            idUsuario, login, senha
	 * @return UsuarioBean
	 */
	@RequestMapping(value = "/desbloquearUsuario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UsuarioBean desbloquearUsuario(@RequestBody String jsonDesbloquar) {
		System.out.println(jsonDesbloquar.toString());
		usuario = new UsuarioBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonDesbloquar);
			System.out.println(jsonObject.toString());
			usuario.setIdUsuario(jsonObject.getInt("idUsuario"));
			usuario.setLogin(jsonObject.getString("login"));
			usuario.setSenha(jsonObject.getString("senha"));
			retorno = new UsuarioDAO().execUsuarioBloquear(usuario.getIdUsuario(), usuario.getLogin(), true);
			traducaoRetornoErro(retorno);
		} catch (JSONException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(true, EnumErroUsuario._5_JSONException.toString(), "-5");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(true, EnumErroUsuario._7_ClassNotFoundException.toString(), "-7");
		} catch (SQLException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(true, EnumErroUsuario._6_SQLException.toString(), "-6");
		}
		return usuario;
	}

	//Traducao de todos os erros retornado pelo banco de dados para a consultas de usuario
	public void traducaoRetornoErro(String erro){
		switch (usuario.getReason()) {
		case "-1":
			usuario.setSuccess(false);
			usuario.setDetail(EnumErroUsuario._1.toString());
			break;
		case "-2":
			usuario.setSuccess(false);
			usuario.setDetail(EnumErroUsuario._2.toString());
			break;
		case "-3":
			usuario.setSuccess(false);
			usuario.setDetail(EnumErroUsuario._3.toString());
			break;
		case "-4":
			usuario.setSuccess(false);
			usuario.setDetail(EnumErroUsuario._4.toString());
			break;
		default:
			break;
		}
	}
}
