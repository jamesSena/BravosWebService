package br.com.bravos.webservices.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.bravos.webservices.dao.AssociarUsuariosPropriedadeDAO;
import br.com.bravos.webservices.dao.UsuarioDAO;
import br.com.bravos.webservices.enums.EnumErroUsuario;
import br.com.bravos.webservices.model.UsuarioBean;

/**
 * @author JamessonSena
 *
 */
@CrossOrigin
@RestController
public class AssociarUsuariosPropriedadeController implements _TratamentoRetorno{
	private UsuarioBean usuario;
	private String retorno;
	private List<UsuarioBean> usuarioList;
	/**
	 * Construtor default
	 */
	public AssociarUsuariosPropriedadeController() {
		super();
	}

	
	@RequestMapping(value = "/AssociarUsuario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes= MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<UsuarioBean> cadastrarUsuario(@RequestBody String jsonCadastro){
		usuario = new UsuarioBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonCadastro);  
			System.out.println(jsonObject.toString());
			String login = jsonObject.getString("login");
			int idPropriedadeCandidata = jsonObject.getInt("idPropriedadeCandidata");
			
			// Json ok
			usuario.setReason(new AssociarUsuariosPropriedadeDAO().execUsuarioCadastrar(login, idPropriedadeCandidata));
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
//
	// idoperacao = 2 -> retornar todos usuario que querem se associar
	@RequestMapping(value = "/consultarUsuarioPendentes/{idPropriedadeCandidata}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<UsuarioBean> consultarUsuarioPendentes(@PathVariable("idPropriedadeCandidata") int idPropriedadeCandidata){
		usuarioList = new ArrayList<UsuarioBean>();
		try {
			usuarioList  = new AssociarUsuariosPropriedadeDAO().execUsuarioRetornar(idPropriedadeCandidata);
			tratamentoRetorno(usuarioList.get(0).getReason());
		} catch (SQLException e) {
			e.printStackTrace();
			usuarioList.add(new UsuarioBean(false, EnumErroUsuario._6_SQLException.toString(), "-6"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();	
			usuarioList.add(new UsuarioBean(false, EnumErroUsuario._7_ClassNotFoundException.toString(), "-7"));
		} 
		return usuarioList;

	}

	@Override
	public void tratamentoRetorno(String erro) {
		// TODO Auto-generated method stub
		
	}

}
