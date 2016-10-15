package br.com.bravos.webservices.controller;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.bravos.webservices.dao.NotificacaoDAO;
import br.com.bravos.webservices.dao.PropriedadeDAO;
import br.com.bravos.webservices.model.NotificacaoBean;
import br.com.bravos.webservices.model.PropriedadeBean;

@RestController
public class NotificacaoRestController {

	private NotificacaoBean notificacaoBean;
	private NotificacaoDAO notificacaoDAO;
	
	public NotificacaoRestController() {
	}
	@RequestMapping(value = "/cadastrarNotificacao", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PropriedadeBean cadastrarNotificacao(@RequestBody String jsonCadastro) throws JsonProcessingException {
		notificacaoBean = new NotificacaoBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonCadastro);
			System.out.println(jsonObject.toString());
			notificacaoBean.setIdUsuario(jsonObject.getInt("idUsuario"));
			notificacaoBean.setIdPropriedade(jsonObject.getInt("idPropriedade"));
			notificacaoBean.setNomePropriedade(jsonObject.getString("nomePropriedade"));
			notificacaoBean.setResponsavel(jsonObject.getString("responsavel"));
			notificacaoBean.setLatitude(jsonObject.getString("latitude"));
			notificacaoBean.setLongitude(jsonObject.getString("longitude"));
			// Json ok
			notificacaoDAO = new NotificacaoDAO();
			String codigo = notificacaoDAO.execNotificacaoCadastrar(notificacaoBean.getIdUsuario(), idSensor, idArea, idPropriedade, idStatus, dataInicio, dataFim, idNotificacao)
			if (notificacaoBean.getReason().equals("1")) {
				notificacaoBean.setSuccess(true);
				notificacaoBean.setDetail("sucesso");
			}
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
		}
		return notificacaoBean;

	}

}
