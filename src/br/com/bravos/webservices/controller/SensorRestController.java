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

import br.com.bravos.webservices.dao.SensorDAO;
import br.com.bravos.webservices.model.SensorBean;
/**
 * @author JamessonSena
 *
 */
@RestController
public class SensorRestController {
	
	
	private SensorBean sensor;
	private SensorDAO sensorDAO;
	List<SensorBean> sensorList;
	private String retorno;
	public SensorRestController() {
	}
	
	
	@RequestMapping(value = "/cadastrarSensor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public SensorBean cadastrarSensor(@RequestBody String jsonCadastro) throws JsonProcessingException {
		sensor = new SensorBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonCadastro);
			System.out.println(jsonObject.toString());
			sensor.setIdUsuario(jsonObject.getInt("idUsuario"));
			sensor.setIdSensor(jsonObject.getInt("idSensor"));
			sensor.setIdCodArea(jsonObject.getInt("idArea"));
			sensor.setNome(jsonObject.getString("nome"));
			sensor.setLatitude(jsonObject.getString("latitude"));
			sensor.setLongitude(jsonObject.getString("longitude"));
			// Json ok
			sensorDAO = new SensorDAO();
			String codigo = sensorDAO.execSensorCadastrar(sensor.getIdUsuario(), sensor.getIdSensor(), sensor.getIdCodArea(), sensor.getNome(),  sensor.getLatitude(), sensor.getLongitude());
			sensor.setReason(codigo);
			if (sensor.getReason().equals("1")) {
				sensor.setSuccess(true);
				sensor.setDetail("sucesso");
			}
		} catch (JSONException e) {
			e.printStackTrace();
			sensor.setSuccess(false);
			sensor.setReason("-5");
			sensor.setDetail("Formato JSON invalido ou campo faltando, por favor verificar");
		} catch (SQLException e) {
			e.printStackTrace();
			sensor.setSuccess(false);
			sensor.setReason("-6");
			sensor.setDetail("Inconsistência no SQL: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			sensor.setSuccess(false);
			sensor.setReason("-7");
			sensor.setDetail("Erro ao localizar o Driver de conexão");
		}
		return sensor;

	}

	/**
	 * @return JSON: lista de sensores
	 */
	@RequestMapping(value = "/consultarSensores/{idArea}", 
			        method = RequestMethod.GET,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<SensorBean> consultarSensores(@PathVariable("idArea")int idArea) {
		try {
			sensorDAO = new SensorDAO();
			sensorList = sensorDAO.execSensorRetornarTodos(idArea);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return sensorList;
	}	
	/**
	 * @return JSON: sensores
	 */
	@RequestMapping(value = "/consultarSensores/{idArea}/{idSensor}", 
			        method = RequestMethod.GET,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public SensorBean consultarSensor(@PathVariable("idArea")int idArea, @PathVariable("idSensor")int idSensor) {
		try {
			sensorDAO = new SensorDAO();
			sensor = sensorDAO.execSensorRetornarEspecifico(idArea, idSensor);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return sensor;
	}
	/**
	 * @return JSON: codigo
	 */
	@RequestMapping(value = "/deletarSensor", 
			        method = RequestMethod.DELETE,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String deletarSensor(@RequestBody String jsonDeletarSensor) {
		try {
			
			JSONObject jsonObject = new JSONObject(jsonDeletarSensor);
			System.out.println(jsonObject.toString());
			int idArea = jsonObject.getInt("idArea");
			int idSensor = jsonObject.getInt("idSensor");
			sensorDAO = new SensorDAO();
			retorno = sensorDAO.execSensorRemover(idArea, idSensor);
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
	@RequestMapping(value = "/deletarTodosSensores", 
			        method = RequestMethod.DELETE,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String deletarTodosSensores(@RequestBody String jsonDeletarSensor) {
		try {
			
			JSONObject jsonObject = new JSONObject(jsonDeletarSensor);
			System.out.println(jsonObject.toString());
			int idArea = jsonObject.getInt("idArea");
			int idSensor = jsonObject.getInt("idSensor");
			sensorDAO = new SensorDAO();
			retorno = sensorDAO.execSensorRemoverTodos(idArea, idSensor);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	@RequestMapping(value = "/atualizarSensor", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public SensorBean atualizarSensor(@RequestBody String jsonCadastro) throws JsonProcessingException {
		sensor = new SensorBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonCadastro);
			System.out.println(jsonObject.toString());
			sensor.setIdUsuario(jsonObject.getInt("idUsuario"));
			sensor.setIdSensor(jsonObject.getInt("idSensor"));
			sensor.setIdCodArea(jsonObject.getInt("idArea"));
			sensor.setNome(jsonObject.getString("nome"));
			sensor.setLatitude(jsonObject.getString("latitude"));
			sensor.setLongitude(jsonObject.getString("longitude"));
			// Json ok
			sensorDAO = new SensorDAO();
			String codigo = sensorDAO.execSensorAtualzar(sensor.getIdUsuario(), sensor.getIdSensor(), sensor.getIdCodArea(), sensor.getNome(),  sensor.getLatitude(), sensor.getLongitude());
			sensor.setReason(codigo);
			if (sensor.getReason().equals("1")) {
				sensor.setSuccess(true);
				sensor.setDetail("sucesso");
			}
		} catch (JSONException e) {
			e.printStackTrace();
			sensor.setSuccess(false);
			sensor.setReason("-5");
			sensor.setDetail("Formato JSON invalido ou campo faltando, por favor verificar");
		} catch (SQLException e) {
			e.printStackTrace();
			sensor.setSuccess(false);
			sensor.setReason("-6");
			sensor.setDetail("Inconsistência no SQL: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			sensor.setSuccess(false);
			sensor.setReason("-7");
			sensor.setDetail("Erro ao localizar o Driver de conexão");
		}
		return sensor;

	}
}
