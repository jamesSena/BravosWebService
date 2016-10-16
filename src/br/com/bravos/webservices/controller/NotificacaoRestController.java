package br.com.bravos.webservices.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.bravos.webservices.dao.NotificacaoDAO;
import br.com.bravos.webservices.model.NotificacaoBean;

@RestController
public class NotificacaoRestController {

	private NotificacaoBean notificacaoBean;
	private NotificacaoDAO notificacaoDAO;
	
	public NotificacaoRestController() {
	
	}
	
	
	@RequestMapping(value = "/cadastrarNotificacao", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public NotificacaoBean cadastrarNotificacao(@RequestBody String jsonCadastro) throws JsonProcessingException {
		notificacaoBean = new NotificacaoBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonCadastro);
			System.out.println(jsonObject.toString());
			notificacaoBean.setIdUsuario(jsonObject.getInt("idUsuario"));
			notificacaoBean.setIdPropriedade(jsonObject.getInt("idPropriedade"));
			notificacaoBean.setIdArea(jsonObject.getInt("idArea"));
			notificacaoBean.setIdNotificacao(jsonObject.getInt("idNotificacao"));
			notificacaoBean.setIdSensor(jsonObject.getInt("idSensor"));
			notificacaoBean.setIdStatus(jsonObject.getInt("idStatus"));
			String strDataInicio =jsonObject.getString("dataInicio");
			String strDataFim= jsonObject.getString("dataFim");
			// Json ok
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);		
			notificacaoBean.setDataFim(sdf.parse(strDataInicio));
			notificacaoBean.setDataInicio(sdf.parse(strDataFim));
			//Formatação Data ok
			notificacaoDAO = new NotificacaoDAO();
			notificacaoDAO.execNotificacaoCadastrar(notificacaoBean.getIdUsuario(), notificacaoBean.getIdSensor(), notificacaoBean.getIdArea(), notificacaoBean.getIdPropriedade(), notificacaoBean.getIdStatus(), notificacaoBean.getDataInicio(), notificacaoBean.getDataFim(), notificacaoBean.getIdNotificacao());
			
		} catch (JSONException e) {
			e.printStackTrace();
			notificacaoBean.setSuccess(false);
			notificacaoBean.setReason("-5");
			notificacaoBean.setDetail("Formato JSON invalido ou campo faltando, por favor verificar");
		} catch (SQLException e) {
			e.printStackTrace();
			notificacaoBean.setSuccess(false);
			notificacaoBean.setReason("-6");
			notificacaoBean.setDetail("Inconsistência no SQL: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			notificacaoBean.setSuccess(false);
			notificacaoBean.setReason("-7");
			notificacaoBean.setDetail("Erro ao localizar o Driver de conexão");
		} catch (ParseException e) {
			e.printStackTrace();
			notificacaoBean.setSuccess(false);
			notificacaoBean.setReason("-8");
			notificacaoBean.setDetail("Erro no Formato da Data. [padrão: dd/MM/yyyy] ");
		}
		return notificacaoBean;

	}

}
