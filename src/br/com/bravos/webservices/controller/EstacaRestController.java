/**
 * 
 */
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

import br.com.bravos.webservices.dao.EstacaDAO;
import br.com.bravos.webservices.model.EstacaBean;



/**
 * @author JamessonSena
 *
 */
@RestController
public class EstacaRestController {
	private EstacaBean estacaBean;
	private EstacaDAO estacaDAO;
	List<EstacaBean> estacaList;
	private String retorno;
	/**
	 * 
	 */
	public EstacaRestController() {
	}
	@RequestMapping(value = "/cadastrarEstaca", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public EstacaBean cadastrarEstaca(@RequestBody String jsonCadastro) throws JsonProcessingException {
		estacaBean = new EstacaBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonCadastro);
			System.out.println(jsonObject.toString());
			int idUsuario = jsonObject.getInt("idUsuario");
			estacaBean.setIdEstaca(jsonObject.getInt("idEstaca"));
			estacaBean.setIdArea(jsonObject.getInt("idArea"));
			estacaBean.setNome(jsonObject.getString("nome"));
			estacaBean.setLatitude(jsonObject.getString("latitude"));
			estacaBean.setLongitude(jsonObject.getString("longitude"));
			// Json ok
			estacaDAO = new EstacaDAO();
			retorno = estacaDAO.execEstacaCadastrar(idUsuario, estacaBean.getIdEstaca(), estacaBean.getIdArea(), estacaBean.getNome(),  estacaBean.getLatitude(), estacaBean.getLongitude());
			estacaBean.setReason(retorno);
			if (estacaBean.getReason().equals("1")) {
				estacaBean.setSuccess(true);
				estacaBean.setDetail("sucesso");
			}
		} catch (JSONException e) {
			e.printStackTrace();
			estacaBean.setSuccess(false);
			estacaBean.setReason("-5");
			estacaBean.setDetail("Formato JSON invalido ou campo faltando, por favor verificar");
		} catch (SQLException e) {
			e.printStackTrace();
			estacaBean.setSuccess(false);
			estacaBean.setReason("-6");
			estacaBean.setDetail("Inconsistência no SQL: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			estacaBean.setSuccess(false);
			estacaBean.setReason("-7");
			estacaBean.setDetail("Erro ao localizar o Driver de conexão");
		}
		return estacaBean;

	}

	/**
	 * @return JSON: lista de estaca
	 */
	@RequestMapping(value = "/consultarEstacas/{idArea}/{idUsuario}", 
			        method = RequestMethod.GET,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<EstacaBean> consultarEstacas(@PathVariable("idArea")int idArea, @PathVariable("idUsuario")int idUsuario) {
		try {
			estacaList = new EstacaDAO().execEstacaRetornarTodos(idArea, idUsuario);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return estacaList;
	}	
	
	
	/**
	 * @return JSON: EstacaBean
	 */
	@RequestMapping(value = "/consultarEstaca/{idArea}/{idEstaca}", 
			        method = RequestMethod.GET,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public EstacaBean consultarSensor(@PathVariable("idEstaca")int idEstaca, @PathVariable("idArea")int idArea) {
		try {	
			estacaBean = new EstacaDAO().execEstacaRetornarEspecifico(idEstaca, idArea);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return estacaBean;
	}
	/**
	 * @return JSON: codigo
	 */
	@RequestMapping(value = "/deletarEstaca", 
			        method = RequestMethod.DELETE,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String deletarSensor(@RequestBody String jsonDeletarEstaca) {
		try {
			
			JSONObject jsonObject = new JSONObject(jsonDeletarEstaca);
			System.out.println(jsonObject.toString());
			int idEstaca = jsonObject.getInt("idEstaca");
			int idArea = jsonObject.getInt("idArea");
			retorno = new EstacaDAO().execEstacaRemover(idEstaca, idArea);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	/**
	 * @return JSON: codigo
	 */
	@RequestMapping(value = "/deletarTodasEstacas", 
			        method = RequestMethod.DELETE,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String deletarTodasEstacas(@RequestBody String jsonDeletarEstacas) {
		try {
			
			JSONObject jsonObject = new JSONObject(jsonDeletarEstacas);
			System.out.println(jsonObject.toString());
			int idUsuario = jsonObject.getInt("idUsuario");
			int idArea = jsonObject.getInt("idArea");
			int idEstaca = jsonObject.getInt("idEstaca");
			retorno = new EstacaDAO().execEstacaRemoverTodos(idUsuario, idArea, idEstaca);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	@RequestMapping(value = "/atualizarEstaca", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public EstacaBean atualizarEstaca(@RequestBody String jsonAtualizar) throws JsonProcessingException {
		estacaBean = new EstacaBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonAtualizar);
			System.out.println(jsonObject.toString());
			int idUsuario = jsonObject.getInt("idUsuario");
			estacaBean.setIdEstaca(jsonObject.getInt("idSensor"));
			estacaBean.setIdArea(jsonObject.getInt("idArea"));
			estacaBean.setNome(jsonObject.getString("nome"));
			estacaBean.setLatitude(jsonObject.getString("latitude"));
			estacaBean.setLongitude(jsonObject.getString("longitude"));
			// Json ok
			String codigo = new EstacaDAO().execEstacaAtualzar(idUsuario, estacaBean.getIdEstaca(), estacaBean.getIdArea(), estacaBean.getNome(),  estacaBean.getLatitude(), estacaBean.getLongitude());
			estacaBean.setReason(codigo);
			if (estacaBean.getReason().equals("1")) {
				estacaBean.setSuccess(true);
				estacaBean.setDetail("sucesso");
			}
		} catch (JSONException e) {
			e.printStackTrace();
			estacaBean.setSuccess(false);
			estacaBean.setReason("-5");
			estacaBean.setDetail("Formato JSON invalido ou campo faltando, por favor verificar");
		} catch (SQLException e) {
			e.printStackTrace();
			estacaBean.setSuccess(false);
			estacaBean.setReason("-6");
			estacaBean.setDetail("Inconsistência no SQL: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			estacaBean.setSuccess(false);
			estacaBean.setReason("-7");
			estacaBean.setDetail("Erro ao localizar o Driver de conexão");
		}
		return estacaBean;

	}
}
