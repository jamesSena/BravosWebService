package br.com.bravos.webservices.controller;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.bravos.webservices.dao.UsuarioDAO;
import br.com.bravos.webservices.enums.EnumErroUsuario;
import br.com.bravos.webservices.filtro.Token;
import br.com.bravos.webservices.model.UsuarioBean;

/**
 * @author JamessonSena
 *
 */
@CrossOrigin
@RestController
public class UsuarioRestController implements _TratamentoRetorno{
	
	private UsuarioBean usuario;
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
	 */

	@RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes= MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<UsuarioBean> cadastrarUsuario(@RequestBody String jsonCadastro){
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
			usuario.setReason(new UsuarioDAO().execUsuarioCadastrar(1, usuario.getLogin(), usuario.getSenha(),usuario.getIdPropriedade(), usuario.isAtivo(), usuario.getNome(),usuario.getIdPerfil()));
			tratamentoRetorno(usuario.getReason());
		} catch (JSONException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(false, EnumErroUsuario._5_JSONException.toString(), "-5");
		} catch (SQLException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(false, EnumErroUsuario._6_SQLException.toString(), "-6");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();	
			usuario = new UsuarioBean(false, EnumErroUsuario._7_ClassNotFoundException.toString(), "-7");

		} 
		return ResponseEntity.ok(usuario);

	}

	/**
	 * @return JSON: lista de UsuarioBean
	 */
	
	@RequestMapping(value = "/consultarUsuarios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<UsuarioBean> consultarUsuarios() {
		try {
			usuarioList = new UsuarioDAO().execUsuarioRetornarTodos();
			usuarioList.get(0).setToken(new Token().gerarToken(usuario.getIdUsuario()));
			tratamentoRetorno(usuarioList.get(0).getReason());
		} catch (SQLException e) {
			e.printStackTrace();
			usuarioList = new ArrayList<UsuarioBean>(); 
			usuarioList.add(new UsuarioBean(false, EnumErroUsuario._6_SQLException.toString(), "-6"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			usuarioList = new ArrayList<UsuarioBean>(); 
			usuarioList.add(new UsuarioBean(false, EnumErroUsuario._7_ClassNotFoundException.toString(), "-7" ));
		}
		return usuarioList;
	}
	/**
	 * @param email
	 * @param senha
	 * @return JSON: UsuarioBean
	 */
	@RequestMapping(value = "/consultarUsuario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UsuarioBean consultarUsuario(@RequestBody String jsonConsultarUsuario) {

		try {
			System.out.println("JSON: "+jsonConsultarUsuario);
			JSONObject jsonObject = new JSONObject(jsonConsultarUsuario);
			System.out.println("JSON TO STRING: "+jsonObject.toString());
			String login = jsonObject.getString("login");
			String senha = jsonObject.getString("senha");
			usuario = new UsuarioDAO().execUsuarioRetornarEspecifico(login, senha);
			
			if (usuario!=null) { // ver se passou um usuario
				usuario.setToken(new Token().gerarToken(usuario.getIdUsuario()));
			}
			tratamentoRetorno(usuario.getReason());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(false, EnumErroUsuario._7_ClassNotFoundException.toString(), "-7");
		} catch (SQLException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(false, EnumErroUsuario._6_SQLException.toString(), "-6");
		} catch (JSONException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(false, EnumErroUsuario._5_JSONException.toString(), "-5");
		}
		return usuario;
	}

	/**
	 * @param JSON:
	 *            idUsuario, ativo, idPerfil, idPropriedade, login, nome,
	 *            senha
	 * @return UsuarioBean
	 */
	@RequestMapping(value = "/atualizarUsuario", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UsuarioBean atualizarUsuario(@RequestBody String jsonAtualizar, @RequestHeader String Authorization) {
		System.out.println(Authorization);
		usuario = new UsuarioBean();
		int idUsuario = 0;
		try {
			JSONObject jsonObject = new JSONObject(jsonAtualizar);
			System.out.println(jsonObject.toString());
			idUsuario = jsonObject.getInt("idUsuario");
			usuario.setAtivo(jsonObject.getBoolean("ativo"));
			usuario.setIdPerfil(jsonObject.getInt("idPerfil"));
			usuario.setIdPropriedade(jsonObject.getInt("idPropriedade"));
			usuario.setLogin(jsonObject.getString("login"));
			usuario.setNome(jsonObject.getString("nome"));
			usuario.setSenha(jsonObject.getString("senha"));
			retorno = new UsuarioDAO().execUsuarioAtualizar(idUsuario, usuario.getLogin(), usuario.getSenha(),
					usuario.getIdPropriedade(), usuario.isAtivo(), usuario.getNome(),usuario.getIdPerfil());
			tratamentoRetorno(retorno);
			usuario.setToken(new Token().gerarToken(usuario.getIdUsuario()));
		} catch (JSONException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(true, EnumErroUsuario._5_JSONException.toString(), "-5");
		} catch (SQLException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(true, EnumErroUsuario._6_SQLException.toString(), "-6");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			usuario = new UsuarioBean(true, EnumErroUsuario._7_ClassNotFoundException.toString(), "-7");

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
			retorno = new UsuarioDAO().execUsuarioRemover(usuario.getIdUsuario(), usuario.getLogin(), usuario.getSenha());
			tratamentoRetorno(retorno);
			usuario.setToken(new Token().gerarToken(usuario.getIdUsuario()));

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
		usuario = new UsuarioBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonExcluirTodos);
			usuario.setIdUsuario(jsonObject.getInt("idUsuario"));
			retorno = new UsuarioDAO().execUsuarioRemoverTodos(usuario.getIdUsuario());
			tratamentoRetorno(retorno);
			usuario.setToken(new Token().gerarToken(usuario.getIdUsuario()));

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
		usuario = new UsuarioBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonBloquear);
			System.out.println(jsonObject.toString());
			usuario.setIdUsuario(jsonObject.getInt("idUsuario"));
			usuario.setLogin(jsonObject.getString("login"));
			usuario.setSenha(jsonObject.getString("senha"));
			retorno = new UsuarioDAO().execUsuarioBloquear(usuario.getIdUsuario(), usuario.getLogin(), false);
			tratamentoRetorno(retorno);
			usuario.setToken(new Token().gerarToken(usuario.getIdUsuario()));
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
			tratamentoRetorno(retorno);
			usuario.setToken(new Token().gerarToken(usuario.getIdUsuario()));

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
	@Override
	public void tratamentoRetorno(String erro) {
		switch (erro) {
		case "-1":
			usuario.set_BeanAbstract(false, EnumErroUsuario._1.toString(), "-1");
			break;
		case "-2":
			usuario.set_BeanAbstract(false, EnumErroUsuario._2.toString(), "-2");
			break;
		case "-3":
			usuario.set_BeanAbstract(false, EnumErroUsuario._3.toString(), "-3");
			break;
		case "-4":
			usuario.set_BeanAbstract(false, EnumErroUsuario._4.toString(), "-4");
			break;
		default:
			break;
		}		
	}
}
