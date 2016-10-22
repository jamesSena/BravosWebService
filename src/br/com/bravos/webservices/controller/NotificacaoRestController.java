package br.com.bravos.webservices.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.bravos.webservices.dao.NotificacaoDAO;
import br.com.bravos.webservices.enums.EnumErroNotificacao;
import br.com.bravos.webservices.model.NotificacaoBean;
import br.com.bravos.webservices.model._BeanAbstract;

/**
 * @author JamessonSena
 *
 */
@RestController
public class NotificacaoRestController {

	private NotificacaoBean notificacaoBean;
	private NotificacaoDAO notificacaoDAO;
	private List<NotificacaoBean> notificacaoList;
	private _BeanAbstract retornoGenerico;
	
	public NotificacaoRestController() {}
	
	/**
	 * @param jsonCadastro -> idUsuario, idPropriedade, idArea, idNotificacao, idSensor, idStatus, dataInicio, dataFim
	 * @return NotificacaoBean
	 */
	@RequestMapping(value = "/cadastrarNotificacao", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public NotificacaoBean cadastrarNotificacao(@RequestBody String jsonCadastro) {
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
			notificacaoBean.setDetail(EnumErroNotificacao._5_JSONException.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			notificacaoBean.setSuccess(false);
			notificacaoBean.setReason("-6");
			notificacaoBean.setDetail(EnumErroNotificacao._6_SQLException.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			notificacaoBean.setSuccess(false);
			notificacaoBean.setReason("-7");
			notificacaoBean.setDetail(EnumErroNotificacao._7_ClassNotFoundException.toString());
		} catch (ParseException e) {
			e.printStackTrace();
			notificacaoBean.setSuccess(false);
			notificacaoBean.setDetail(EnumErroNotificacao._8_ParseException.toString());
			notificacaoBean.setReason("-8");
		}
		return notificacaoBean;

	}
	/**
	 * @param jsonCadastro -> idUsuario, idPropriedade, idArea, idNotificacao, idSensor, idStatus, dataInicio, dataFim
	 * @return NotificacaoBean
	 */
	@RequestMapping(value = "/consultarNotificacoes/{idUsuario}/{idPropriedade}", 
			        method = RequestMethod.GET,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<NotificacaoBean> consultarNotificacoes(@PathVariable("idUsuario")int idUsuario, @PathVariable("idPropriedade")int idPropriedade) {
		try {
			notificacaoDAO = new NotificacaoDAO();
			notificacaoList = notificacaoDAO.execNotificacaoRetornarTodas(idUsuario, idPropriedade);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			notificacaoBean.setDetail(EnumErroNotificacao._7_ClassNotFoundException.toString());
			notificacaoBean.setReason("-7");
			notificacaoBean.setSuccess(false);
			List<NotificacaoBean> notificacaoList =  new ArrayList<NotificacaoBean>();
			notificacaoList.add(notificacaoBean);
		} catch (SQLException e) {
			e.printStackTrace();
			retornoGenerico.setDetail(EnumErroNotificacao._6_SQLException.toString());
			retornoGenerico.setReason("-6");
			retornoGenerico.setSuccess(false);
			List<NotificacaoBean> notificacaoList =  new ArrayList<NotificacaoBean>();
			notificacaoList.add(notificacaoBean);
		}
		return notificacaoList;
	}	

	/**
	 * @param jsonConsultarNotificao -> idUsuario, idPropriedade, dataInicio, dataFim
	 * @return Lista de NotificacaoBean
	 */
	@RequestMapping(value = "/consultarNotificao", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<NotificacaoBean> consultarNotificao(@RequestBody String jsonConsultarNotificao) {
		try {
			JSONObject jsonObject = new JSONObject(jsonConsultarNotificao);
			System.out.println(jsonObject.toString());
			int idUsuario = jsonObject.getInt("idUsuario");
			int idPropriedade = jsonObject.getInt("idPropriedade");
			String strDataInicio = jsonObject.getString("dataInicio");
			String strDataFim = jsonObject.getString("");
			
			// Json ok
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);		
			Date dataInicio = sdf.parse(strDataInicio);
			Date dataFim = sdf.parse(strDataFim);

			notificacaoList = new NotificacaoDAO().execNotificacaoRetornar(idUsuario, idPropriedade, dataInicio, dataFim);
		
		} catch (JSONException e) {
			
			e.printStackTrace();
			notificacaoBean.setDetail(EnumErroNotificacao._5_JSONException.toString());
			notificacaoBean.setReason("-5");
			notificacaoBean.setSuccess(false);
			List<NotificacaoBean> notificacaoList =  new ArrayList<NotificacaoBean>();
			notificacaoList.add(notificacaoBean);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			notificacaoBean.setDetail(EnumErroNotificacao._7_ClassNotFoundException.toString());
			notificacaoBean.setReason("-7");
			notificacaoBean.setSuccess(false);	
			List<NotificacaoBean> notificacaoList =  new ArrayList<NotificacaoBean>();
			notificacaoList.add(notificacaoBean);

		} catch (SQLException e) {
			e.printStackTrace();
			notificacaoBean.setDetail(EnumErroNotificacao._6_SQLException.toString());
			notificacaoBean.setReason("-6");
			notificacaoBean.setSuccess(false);
			List<NotificacaoBean> notificacaoList =  new ArrayList<NotificacaoBean>();
			notificacaoList.add(notificacaoBean);
		}catch (ParseException e) {
			e.printStackTrace();
			notificacaoBean.setDetail(EnumErroNotificacao._8_ParseException.toString());
			notificacaoBean.setReason("-8");
			notificacaoBean.setSuccess(false);	
			List<NotificacaoBean> notificacaoList =  new ArrayList<NotificacaoBean>();
			notificacaoList.add(notificacaoBean);
	}
		return notificacaoList;
	}
	
	/**
	 * @param jsonDeletarNotificacao ->  idUsuario, idPropriedade, idNotificacao
	 * @return _BeanAbstract -> success, detail, reason
	 */
	@RequestMapping(value = "/deletarNotificacaoEspecifica", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public _BeanAbstract deletarNotificacaoEspecifica(@RequestBody String jsonDeletarNotificacao){
			try {
				JSONObject jsonObject = new JSONObject(jsonDeletarNotificacao);
				System.out.println(jsonObject.toString());
				int idUsuario = jsonObject.getInt("idUsuario");
				int idPropriedade = jsonObject.getInt("idPropriedade");
				int idNotificacao = jsonObject.getInt("idNotificacao");
				String retorno = new NotificacaoDAO().execDeletarNotificacao(idUsuario, idPropriedade, idNotificacao);
				retornoGenerico = new _BeanAbstract();
				retornoGenerico.setReason(retorno);
			} catch (JSONException e) {
				e.printStackTrace();
				retornoGenerico.setDetail(EnumErroNotificacao._5_JSONException.toString());
				retornoGenerico.setReason("-5");
				retornoGenerico.setSuccess(false);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				retornoGenerico.setDetail(EnumErroNotificacao._7_ClassNotFoundException.toString());
				retornoGenerico.setReason("-7");
				retornoGenerico.setSuccess(false);
			} catch (SQLException e) {
				e.printStackTrace();
				retornoGenerico.setDetail(EnumErroNotificacao._6_SQLException.toString());
				retornoGenerico.setReason("-6");
				retornoGenerico.setSuccess(false);
			}
		return retornoGenerico;
	}
	
	/**
	 * @param jsonDeletarNotificacao ->  idUsuario, idPropriedade
	 * @return _BeanAbstract -> success, detail, reason
	 */
	@RequestMapping(value = "/deletarNotificacao", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public _BeanAbstract deletarNotificacao(@RequestBody String jsonDeletarNotificacao){
			try {
				JSONObject jsonObject = new JSONObject(jsonDeletarNotificacao);
				System.out.println(jsonObject.toString());
				int idUsuario = jsonObject.getInt("idUsuario");
				int idPropriedade = jsonObject.getInt("idPropriedade");
				String retorno = new NotificacaoDAO().execDeletarNotificacao(idUsuario, idPropriedade);
				retornoGenerico = new _BeanAbstract();
				retornoGenerico.setReason(retorno);
			} catch (JSONException e) {
				e.printStackTrace();
				retornoGenerico.setDetail(EnumErroNotificacao._5_JSONException.toString());
				retornoGenerico.setReason("-5");
				retornoGenerico.setSuccess(false);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				retornoGenerico.setDetail(EnumErroNotificacao._7_ClassNotFoundException.toString());
				retornoGenerico.setReason("-7");
				retornoGenerico.setSuccess(false);
			} catch (SQLException e) {
				e.printStackTrace();
				retornoGenerico.setDetail(EnumErroNotificacao._6_SQLException.toString());
				retornoGenerico.setReason("-6");
				retornoGenerico.setSuccess(false);
			}
		return retornoGenerico;
	}

	
}
