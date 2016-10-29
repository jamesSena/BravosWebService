/**
 * 
 */
package br.com.bravos.webservices.controller;

import java.sql.SQLException;
import java.util.ArrayList;
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
import br.com.bravos.webservices.enums.EnumErroEstaca;
import br.com.bravos.webservices.enums.EnumErroUsuario;
import br.com.bravos.webservices.model.EstacaBean;
import br.com.bravos.webservices.model.UsuarioBean;



/**
 * @author JamessonSena
 *
 */
@RestController
public class EstacaRestController implements TratamentoRetorno {
	private EstacaBean estacaBean;
	List<EstacaBean> estacaList;
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
			estacaBean.setReason(new EstacaDAO().execEstacaCadastrar(idUsuario, estacaBean.getIdEstaca(), estacaBean.getIdArea(), estacaBean.getNome(),  estacaBean.getLatitude(), estacaBean.getLongitude()));
			tratamentoRetorno(estacaBean.getReason());
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
			estacaList = new ArrayList<EstacaBean>(); 
			estacaList.add(new EstacaBean(false, EnumErroUsuario._6_SQLException.toString(), "-6"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			estacaList = new ArrayList<EstacaBean>(); 
			estacaList.add(new EstacaBean(false, EnumErroUsuario._7_ClassNotFoundException.toString(), "-7"));
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
		estacaBean = new EstacaBean();
		try {	
			estacaBean = new EstacaDAO().execEstacaRetornarEspecifico(idEstaca, idArea);
			tratamentoRetorno(estacaBean.getReason());
		} catch (SQLException e) {
			e.printStackTrace();
			estacaBean.setReason("-6");	
			estacaBean.setDetail(EnumErroEstaca._6_SQLException.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			estacaBean.setReason("-7");	
			estacaBean.setDetail(EnumErroEstaca._7_ClassNotFoundException.toString());
		}
		return estacaBean;
	}
	/**
	 * @return JSON: codigo
	 */
	@RequestMapping(value = "/deletarEstaca", 
			        method = RequestMethod.DELETE,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public EstacaBean deletarSensor(@RequestBody String jsonDeletarEstaca) {
		estacaBean = new EstacaBean();
		try {
			
			JSONObject jsonObject = new JSONObject(jsonDeletarEstaca);
			System.out.println(jsonObject.toString());
			int idEstaca = jsonObject.getInt("idEstaca");
			int idArea = jsonObject.getInt("idArea");
			estacaBean.setReason(new EstacaDAO().execEstacaRemover(idEstaca, idArea));
		} catch (SQLException e) {
			e.printStackTrace();
			estacaBean.setDetail(EnumErroEstaca._6_SQLException.toString());
			estacaBean.setReason("-6");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			estacaBean.setDetail(EnumErroEstaca._7_ClassNotFoundException.toString());
			estacaBean.setReason("-7");
		} catch (JSONException e) {
			e.printStackTrace();
			estacaBean.setDetail(EnumErroEstaca._5_JSONException.toString());
			estacaBean.setReason("-5");
		}
		return estacaBean;
	}
	/**
	 * @return JSON: codigo
	 */
	@RequestMapping(value = "/deletarTodasEstacas", 
			        method = RequestMethod.DELETE,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public EstacaBean deletarTodasEstacas(@RequestBody String jsonDeletarEstacas) {
		estacaBean = new EstacaBean();
		try {			
			JSONObject jsonObject = new JSONObject(jsonDeletarEstacas);
			System.out.println(jsonObject.toString());
			int idUsuario = jsonObject.getInt("idUsuario");
			int idArea = jsonObject.getInt("idArea");
			int idEstaca = jsonObject.getInt("idEstaca");
			estacaBean.setReason(new EstacaDAO().execEstacaRemoverTodos(idUsuario, idArea, idEstaca));
			tratamentoRetorno(estacaBean.getReason());
		} catch (SQLException e) {
			e.printStackTrace();
			estacaBean.setReason("-6");
			estacaBean.setDetail(EnumErroEstaca._6_SQLException.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			estacaBean.setReason("-7");
			estacaBean.setDetail(EnumErroEstaca._7_ClassNotFoundException.toString());
		} catch (JSONException e) {
			e.printStackTrace();
			estacaBean.setReason("-5");
			estacaBean.setDetail(EnumErroEstaca._5_JSONException.toString());
		}
		return estacaBean;
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
			estacaBean.setReason(new EstacaDAO().execEstacaAtualzar(idUsuario, estacaBean.getIdEstaca(), estacaBean.getIdArea(), estacaBean.getNome(),  estacaBean.getLatitude(), estacaBean.getLongitude()));
		
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
	@Override
	public void tratamentoRetorno(String erro) {
		switch (erro) {
		case "-1":
			estacaBean.setSuccess(false);
			estacaBean.setDetail(EnumErroUsuario._1.toString());
			break;
		case "-2":
			estacaBean.setSuccess(false);
			estacaBean.setDetail(EnumErroUsuario._2.toString());
			break;
		case "-3":
			estacaBean.setSuccess(false);
			estacaBean.setDetail(EnumErroUsuario._3.toString());
			break;
		case "-4":
			estacaBean.setSuccess(false);
			estacaBean.setDetail(EnumErroUsuario._4.toString());
			break;
		default:
			break;
		}		
	}
}
