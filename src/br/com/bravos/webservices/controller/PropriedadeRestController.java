/**
 * 
 */
package br.com.bravos.webservices.controller;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.bravos.webservices.dao.PropriedadeDAO;
import br.com.bravos.webservices.enums.EnumErroPropriedade;
import br.com.bravos.webservices.model.PropriedadeBean;

/**
 * @author JamessonSena
 *
 */
@RestController
public class PropriedadeRestController implements _TratamentoRetorno {
	
	private PropriedadeBean proprieade;
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
		proprieade = new PropriedadeBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonCadastro);
			System.out.println(jsonObject.toString());
			proprieade.setIdUsuario(jsonObject.getInt("idUsuario"));
			proprieade.setNomePropriedade(jsonObject.getString("nomePropriedade"));
			proprieade.setResponsavel(jsonObject.getString("responsavel"));
			proprieade.setLatitude(jsonObject.getString("latitude"));
			proprieade.setLongitude(jsonObject.getString("longitude"));
			// Json ok
			proprieade.setReason(new PropriedadeDAO().execPropriedadeCadastrar(proprieade.getIdUsuario(), proprieade.getNomePropriedade(), proprieade.getResponsavel(), proprieade.getEmailResponsavel(), proprieade.getLatitude(), proprieade.getLongitude()));
			tratamentoRetorno(proprieade.getReason());
		} catch (JSONException e) {
			e.printStackTrace();
			proprieade.setSuccess(false);
			proprieade.setReason("-5");
			proprieade.setDetail(EnumErroPropriedade._5_JSONException.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			proprieade.setSuccess(false);
			proprieade.setReason("-6");
			proprieade.setDetail(EnumErroPropriedade._6_SQLException.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			proprieade.setSuccess(false);
			proprieade.setReason("-7");
			proprieade.setDetail(EnumErroPropriedade._7_ClassNotFoundException.toString());
		}
		return proprieade;

	}

	@RequestMapping(value = "/consultarPropriedade/{idUsuario}/{idPropriedade}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PropriedadeBean consultarPropriedade(@PathVariable("idPropriedade") int idPropriedade, @PathVariable("idUsuario") int idUsuario) throws JsonProcessingException {
		proprieade = new PropriedadeBean();
		try {
			proprieade = new PropriedadeDAO().execPropriedadeRetornar(idPropriedade, idUsuario);
	
		} catch (SQLException e) {
			e.printStackTrace();
			proprieade.setSuccess(false);
			proprieade.setReason("-6");
			proprieade.setDetail(EnumErroPropriedade._6_SQLException.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			proprieade.setSuccess(false);
			proprieade.setReason("-7");
			proprieade.setDetail(EnumErroPropriedade._7_ClassNotFoundException.toString());
		}
		return proprieade;

	}

	@RequestMapping(value = "/deletarPropriedade", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PropriedadeBean deletarPropriedade(@RequestBody String jsonDeletarSensor) throws JsonProcessingException {
		proprieade = new PropriedadeBean();
		try {

			JSONObject jsonObject = new JSONObject(jsonDeletarSensor);
			System.out.println(jsonObject.toString());
			int idUsuario = jsonObject.getInt("idUsuario");
			int idPropriedade = jsonObject.getInt("idPropriedade");

			proprieade = new PropriedadeDAO().execPropriedadeDeletar(idUsuario, idPropriedade);
	
		} catch (SQLException e) {
			e.printStackTrace();
			proprieade.setSuccess(false);
			proprieade.setReason("-6");
			proprieade.setDetail(EnumErroPropriedade._7_ClassNotFoundException.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			proprieade.setSuccess(false);
			proprieade.setReason("-7");
			proprieade.setDetail("Erro ao localizar o Driver de conexão");
		} catch (JSONException e) {
			e.printStackTrace();
			proprieade.setSuccess(false);
			proprieade.setReason("-5");
			proprieade.setDetail("Formato JSON invalido ou campo faltando, por favor verificar");
		}
		return proprieade;

	}

	@RequestMapping(value = "/atualizarPropriedade", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PropriedadeBean atualizarPropriedade(@RequestBody String jsonAtualizar) throws JsonProcessingException {
		proprieade = new PropriedadeBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonAtualizar);
			System.out.println(jsonObject.toString());
			proprieade.setIdUsuario(jsonObject.getInt("idUsuario"));
			proprieade.setIdPropriedade(jsonObject.getInt("idPropriedade"));
			proprieade.setNomePropriedade(jsonObject.getString("nomePropriedade"));
			proprieade.setResponsavel(jsonObject.getString("responsavel"));
			proprieade.setLatitude(jsonObject.getString("latitude"));
			proprieade.setLongitude(jsonObject.getString("longitude"));
			// Json ok
			propriedadeDAO = new PropriedadeDAO();
			String codigo = propriedadeDAO.execPropriedadeAtualizar(proprieade.getIdUsuario(), proprieade.getIdPropriedade(), proprieade.getNomePropriedade(), proprieade.getResponsavel(), proprieade.getEmailResponsavel(), proprieade.getLatitude(), proprieade.getLongitude());
			proprieade.setReason(codigo);
			if (proprieade.getReason().equals("1")) {
				proprieade.setSuccess(true);
				proprieade.setDetail("sucesso");
			}
		} catch (JSONException e) {
			e.printStackTrace();
			proprieade.setSuccess(false);
			proprieade.setReason("-5");
			proprieade.setDetail("Formato JSON invalido ou campo faltando, por favor verificar");
		} catch (SQLException e) {
			e.printStackTrace();
			proprieade.setSuccess(false);
			proprieade.setReason("-6");
			proprieade.setDetail("Inconsistência no SQL: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			proprieade.setSuccess(false);
			proprieade.setReason("-7");
			proprieade.setDetail("Erro ao localizar o Driver de conexão");
		}
		return proprieade;

	}


	@Override
	public void tratamentoRetorno(String erro) {
		// TODO Auto-generated method stub
		
	}

}
