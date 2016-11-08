/**
 * 
 */
package br.com.bravos.webservices.controller;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.bravos.webservices.dao.PropriedadeDAO;
import br.com.bravos.webservices.enums.EnumErroPropriedade;
import br.com.bravos.webservices.enums.EnumErroUsuario;
import br.com.bravos.webservices.model.PropriedadeBean;

/**
 * @author JamessonSena
 *
 */
@CrossOrigin
@RestController
public class PropriedadeRestController implements _TratamentoRetorno {
	
	private PropriedadeBean proprieadeBean;
	private PropriedadeDAO propriedadeDAO;
	/**
	 * 
	 */
	public PropriedadeRestController() {}

	
	/**
	 * @param jsonCadastro
	 * @return PropriedadeBean
	 */
	@RequestMapping(value = "/cadastrarPropriedade", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PropriedadeBean cadastrarPropriedade(@RequestBody String jsonCadastro){
		proprieadeBean = new PropriedadeBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonCadastro);
			System.out.println(jsonObject.toString());
			proprieadeBean.setIdUsuario(jsonObject.getInt("idUsuario"));
			proprieadeBean.setNomePropriedade(jsonObject.getString("nomePropriedade"));
			proprieadeBean.setResponsavel(jsonObject.getString("responsavel"));
			proprieadeBean.setLatitude(jsonObject.getString("latitude"));
			proprieadeBean.setLongitude(jsonObject.getString("longitude"));
			// Json ok
			proprieadeBean.setReason(new PropriedadeDAO().execPropriedadeCadastrar(proprieadeBean.getIdUsuario(), proprieadeBean.getNomePropriedade(), proprieadeBean.getResponsavel(), proprieadeBean.getEmailResponsavel(), proprieadeBean.getLatitude(), proprieadeBean.getLongitude()));
			tratamentoRetorno(proprieadeBean.getReason());
		} catch (JSONException e) {
			e.printStackTrace();
			proprieadeBean.setSuccess(false);
			proprieadeBean.setReason("-5");
			proprieadeBean.setDetail(EnumErroPropriedade._5_JSONException.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			proprieadeBean.setSuccess(false);
			proprieadeBean.setReason("-6");
			proprieadeBean.setDetail(EnumErroPropriedade._6_SQLException.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			proprieadeBean.setSuccess(false);
			proprieadeBean.setReason("-7");
			proprieadeBean.setDetail(EnumErroPropriedade._7_ClassNotFoundException.toString());
		}
		return proprieadeBean;

	}

	@RequestMapping(value = "/consultarPropriedade/{idUsuario}/{idPropriedade}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PropriedadeBean consultarPropriedade(@PathVariable("idPropriedade") int idPropriedade, @PathVariable("idUsuario") int idUsuario) throws JsonProcessingException {
		proprieadeBean = new PropriedadeBean();
		try {
			proprieadeBean = new PropriedadeDAO().execPropriedadeRetornar(idPropriedade, idUsuario);
			tratamentoRetorno(proprieadeBean.getReason());
		} catch (SQLException e) {
			e.printStackTrace();
			proprieadeBean.setSuccess(false);
			proprieadeBean.setReason("-6");
			proprieadeBean.setDetail(EnumErroPropriedade._6_SQLException.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			proprieadeBean.setSuccess(false);
			proprieadeBean.setReason("-7");
			proprieadeBean.setDetail(EnumErroPropriedade._7_ClassNotFoundException.toString());
		}
		return proprieadeBean;

	}

	@RequestMapping(value = "/deletarPropriedade", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PropriedadeBean deletarPropriedade(@RequestBody String jsonDeletarSensor) throws JsonProcessingException {
		proprieadeBean = new PropriedadeBean();
		try {

			JSONObject jsonObject = new JSONObject(jsonDeletarSensor);
			System.out.println(jsonObject.toString());
			int idUsuario = jsonObject.getInt("idUsuario");
			int idPropriedade = jsonObject.getInt("idPropriedade");

			proprieadeBean = new PropriedadeDAO().execPropriedadeDeletar(idUsuario, idPropriedade);
			tratamentoRetorno(proprieadeBean.getReason());
		} catch (SQLException e) {
			e.printStackTrace();
			proprieadeBean.setSuccess(false);
			proprieadeBean.setReason("-6");
			proprieadeBean.setDetail(EnumErroPropriedade._7_ClassNotFoundException.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			proprieadeBean.setSuccess(false);
			proprieadeBean.setReason("-7");
			proprieadeBean.setDetail("Erro ao localizar o Driver de conexão");
		} catch (JSONException e) {
			e.printStackTrace();
			proprieadeBean.setSuccess(false);
			proprieadeBean.setReason("-5");
			proprieadeBean.setDetail("Formato JSON invalido ou campo faltando, por favor verificar");
		}
		return proprieadeBean;

	}

	@RequestMapping(value = "/atualizarPropriedade", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PropriedadeBean atualizarPropriedade(@RequestBody String jsonAtualizar) throws JsonProcessingException {
		proprieadeBean = new PropriedadeBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonAtualizar);
			System.out.println(jsonObject.toString());
			proprieadeBean.setIdUsuario(jsonObject.getInt("idUsuario"));
			proprieadeBean.setIdPropriedade(jsonObject.getInt("idPropriedade"));
			proprieadeBean.setNomePropriedade(jsonObject.getString("nomePropriedade"));
			proprieadeBean.setResponsavel(jsonObject.getString("responsavel"));
			proprieadeBean.setLatitude(jsonObject.getString("latitude"));
			proprieadeBean.setLongitude(jsonObject.getString("longitude"));
			// Json ok
			propriedadeDAO = new PropriedadeDAO();
			String codigo = propriedadeDAO.execPropriedadeAtualizar(proprieadeBean.getIdUsuario(), proprieadeBean.getIdPropriedade(), proprieadeBean.getNomePropriedade(), proprieadeBean.getResponsavel(), proprieadeBean.getEmailResponsavel(), proprieadeBean.getLatitude(), proprieadeBean.getLongitude());
			proprieadeBean.setReason(codigo);
			tratamentoRetorno(proprieadeBean.getReason());
		} catch (JSONException e) {
			e.printStackTrace();
			proprieadeBean.setSuccess(false);
			proprieadeBean.setReason("-5");
			proprieadeBean.setDetail("Formato JSON invalido ou campo faltando, por favor verificar");
		} catch (SQLException e) {
			e.printStackTrace();
			proprieadeBean.setSuccess(false);
			proprieadeBean.setReason("-6");
			proprieadeBean.setDetail("Inconsistência no SQL: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			proprieadeBean.setSuccess(false);
			proprieadeBean.setReason("-7");
			proprieadeBean.setDetail("Erro ao localizar o Driver de conexão");
		}
		return proprieadeBean;

	}


	@Override
	public void tratamentoRetorno(String erro) {
		switch (erro) {
		case "-1":
			proprieadeBean.set_BeanAbstract(false, EnumErroUsuario._1.toString(), "-1");
			break;
		case "-2":			
			proprieadeBean.set_BeanAbstract(false, EnumErroUsuario._2.toString(), "-2");
			break;
		case "-3":
			proprieadeBean.set_BeanAbstract(false, EnumErroUsuario._3.toString(), "-3");
			break;
		case "-4":
			proprieadeBean.set_BeanAbstract(false, EnumErroPropriedade._4.toString(), "-4");
			break;
		default:
			break;
		}			
	}

}
