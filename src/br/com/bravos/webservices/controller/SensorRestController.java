package br.com.bravos.webservices.controller;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.bravos.webservices.dao.SensorDAO;
import br.com.bravos.webservices.enums.EnumErroSensor;
import br.com.bravos.webservices.model.SensorBean;
/**
 * @author JamessonSena
 *
 */
@CrossOrigin
@RestController
public class SensorRestController implements _TratamentoRetorno{
	
	
	private SensorBean sensorBean;
	private SensorDAO sensorDAO;
	private List<SensorBean> sensorList;
	public SensorRestController() {
		sensorBean = new SensorBean();
	}
	
	
	@RequestMapping(value = "/cadastrarSensor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public SensorBean cadastrarSensor(@RequestBody String jsonCadastro){
		try {
			JSONObject jsonObject = new JSONObject(jsonCadastro);
			System.out.println(jsonObject.toString());
			sensorBean.setIdUsuario(jsonObject.getInt("idUsuario"));
			sensorBean.setIdSensor(jsonObject.getInt("idSensor"));
			sensorBean.setIdCodArea(jsonObject.getInt("idArea"));
			sensorBean.setNome(jsonObject.getString("nome"));
			sensorBean.setLatitude(jsonObject.getString("latitude"));
			sensorBean.setLongitude(jsonObject.getString("longitude"));
			// Json ok
			String codigo = new SensorDAO().execSensorCadastrar(sensorBean.getIdUsuario(), sensorBean.getIdSensor(), sensorBean.getIdCodArea(), sensorBean.getNome(),  sensorBean.getLatitude(), sensorBean.getLongitude());
			sensorBean.setReason(codigo);
			tratamentoRetorno(sensorBean.getReason());

		} catch (JSONException e) {
			e.printStackTrace();
			sensorBean.setSuccess(false);
			sensorBean.setReason("-5");
			sensorBean.setDetail("Formato JSON invalido ou campo faltando, por favor verificar");
		} catch (SQLException e) {
			e.printStackTrace();
			sensorBean.setSuccess(false);
			sensorBean.setReason("-6");
			sensorBean.setDetail("Inconsistência no SQL: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			sensorBean.setSuccess(false);
			sensorBean.setReason("-7");
			sensorBean.setDetail("Erro ao localizar o Driver de conexão");
		}
		return sensorBean;

	}

	/**
	 * @return JSON: lista de sensores
	 */
	@RequestMapping(value = "/consultarSensores/{idArea}", 
			        method = RequestMethod.GET,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<SensorBean> consultarSensores(@PathVariable("idArea")int idArea) {
		try {
			sensorList = new SensorDAO().execSensorRetornarTodos(idArea);
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
			sensorBean =  new SensorDAO().execSensorRetornarEspecifico(idArea, idSensor);
		} catch (SQLException e) {
			e.printStackTrace();
			sensorBean.set_BeanAbstract(false, EnumErroSensor._6_SQLException.toString(), "-6");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			sensorBean.set_BeanAbstract(false, EnumErroSensor._6_SQLException.toString(), "-6");
		}
		return sensorBean;
	}
	/**
	 * @return JSON: codigo
	 */
	@RequestMapping(value = "/deletarSensor", 
			        method = RequestMethod.DELETE,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public SensorBean deletarSensor(@RequestBody String jsonDeletarSensor) {
		try {			
			JSONObject jsonObject = new JSONObject(jsonDeletarSensor);
			System.out.println(jsonObject.toString());
			int idArea = jsonObject.getInt("idArea");
			int idSensor = jsonObject.getInt("idSensor");
			sensorBean.setReason(new SensorDAO().execSensorRemover(idArea, idSensor));
			tratamentoRetorno(sensorBean.getReason());
		} catch (SQLException e) {
			e.printStackTrace();
			sensorBean.set_BeanAbstract(false, EnumErroSensor._6_SQLException.toString(), "-6");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			sensorBean.set_BeanAbstract(false, EnumErroSensor._7_ClassNotFoundException.toString(), "-7");
		} catch (JSONException e) {
			e.printStackTrace();
			sensorBean.set_BeanAbstract(false, EnumErroSensor._5_JSONException.toString(), "-5");
		}
		return sensorBean;
	}
	
	/**
	 * @return JSON: codigo
	 */
	@RequestMapping(value = "/deletarTodosSensores", 
			        method = RequestMethod.DELETE,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public SensorBean deletarTodosSensores(@RequestBody String jsonDeletarSensor) {
		try {
			
			JSONObject jsonObject = new JSONObject(jsonDeletarSensor);
			System.out.println(jsonObject.toString());
			int idArea = jsonObject.getInt("idArea");
			int idSensor = jsonObject.getInt("idSensor");
			sensorBean.setReason(new SensorDAO().execSensorRemoverTodos(idArea, idSensor));
			tratamentoRetorno(sensorBean.getReason());
		} catch (SQLException e) {
			e.printStackTrace();
			sensorBean.set_BeanAbstract(false, EnumErroSensor._6_SQLException.toString(), "-6");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			sensorBean.set_BeanAbstract(false, EnumErroSensor._7_ClassNotFoundException.toString(), "-7");
		} catch (JSONException e) {
			e.printStackTrace();
			sensorBean.set_BeanAbstract(false, EnumErroSensor._5_JSONException.toString(), "-5");

		}
		return sensorBean;
	}
	
	@RequestMapping(value = "/atualizarSensor", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public SensorBean atualizarSensor(@RequestBody String jsonCadastro) {
		sensorBean = new SensorBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonCadastro);
			System.out.println(jsonObject.toString());
			sensorBean.setIdUsuario(jsonObject.getInt("idUsuario"));
			sensorBean.setIdSensor(jsonObject.getInt("idSensor"));
			sensorBean.setIdCodArea(jsonObject.getInt("idArea"));
			sensorBean.setNome(jsonObject.getString("nome"));
			sensorBean.setLatitude(jsonObject.getString("latitude"));
			sensorBean.setLongitude(jsonObject.getString("longitude"));
			// Json ok
			sensorDAO = new SensorDAO();
			String codigo = sensorDAO.execSensorAtualzar(sensorBean.getIdUsuario(), sensorBean.getIdSensor(), sensorBean.getIdCodArea(), sensorBean.getNome(),  sensorBean.getLatitude(), sensorBean.getLongitude());
			sensorBean.setReason(codigo);
			tratamentoRetorno(sensorBean.getReason());
		} catch (JSONException e) {
			e.printStackTrace();
			sensorBean.set_BeanAbstract(false, EnumErroSensor._5_JSONException.toString(), "-5");
		} catch (SQLException e) {
			e.printStackTrace();
			sensorBean.set_BeanAbstract(false, EnumErroSensor._6_SQLException.toString(), "-6");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			sensorBean.set_BeanAbstract(false, EnumErroSensor._7_ClassNotFoundException.toString(), "-7");
		}
		return sensorBean;

	}


	@Override
	public void tratamentoRetorno(String erro) {
		switch (erro) {
		case "-1":
			sensorBean.setSuccess(false);
			sensorBean.setDetail(EnumErroSensor._1.toString());
			break;
		case "-2":
			sensorBean.setSuccess(false);
			sensorBean.setDetail(EnumErroSensor._2.toString());
			break;
		case "-3":
			sensorBean.setSuccess(false);
			sensorBean.setDetail(EnumErroSensor._3.toString());
			break;
		case "-4":
			sensorBean.setSuccess(false);
			sensorBean.setDetail(EnumErroSensor._4.toString());
			break;
		default:
			break;
		}		
	}
}