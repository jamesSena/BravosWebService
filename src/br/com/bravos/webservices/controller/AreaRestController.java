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
import br.com.bravos.webservices.dao.AreaDAO;
import br.com.bravos.webservices.enums.EnumErroArea;
import br.com.bravos.webservices.enums.EnumErroUsuario;
import br.com.bravos.webservices.model.AreaBean;
import br.com.bravos.webservices.model._BeanAbstract;

/**
 * @author WeltonBatista
 *
 */
@RestController
public class AreaRestController implements TratamentoRetorno {
	
	private AreaBean areaBean;
	private AreaDAO areaDAO;
	private String retorno;
	private List<AreaBean> areaList;
	
	/**
	 * Construtor default
	 */
	public AreaRestController() {
		super();
	}
	
	/**
	 * @param JSON:
	 *            idUsuario, idPropriedade, nomeArea
	 * @return JSON: areaBean
	 * @throws JsonProcessingException
	 */
	
	@RequestMapping(value = "/cadastrarArea", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public _BeanAbstract cadastrarArea(@RequestBody String jsonCadastro) throws JsonProcessingException {
		areaBean = new AreaBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonCadastro);
			System.out.println(jsonObject.toString());
			areaBean.setIdPropriedade(jsonObject.getInt("idPropriedade"));
			areaBean.setNomeArea(jsonObject.getString("nomeArea"));
			int idUsuario = jsonObject.getInt("idUsuario");
			// Json ok
			String codigo = new AreaDAO().execAreaCadastrar(idUsuario, areaBean.getIdPropriedade(), areaBean.getNomeArea());
			areaBean.setReason(codigo);
			tratamentoRetorno(areaBean.getReason());
			
		} catch (JSONException e) {
			e.printStackTrace();
			areaBean.set_BeanAbstract(false, EnumErroArea._5_JSONException.toString(), "-5");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			areaBean.set_BeanAbstract(false, EnumErroArea._7_ClassNotFoundException.toString(), "-7");

		} catch (SQLException e) {
			e.printStackTrace();
			areaBean.set_BeanAbstract(false, EnumErroArea._6_SQLException.toString(), "-5");
		}
		return areaBean;

	}
	
	
	/**
	 * @param idPropriedade
	 * @return JSON: List Area
	 */
	@RequestMapping(value = "/consultarAreas/{idPropriedade}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<AreaBean> consultarAreas(@PathVariable("idPropriedade") int idpropriedade) {
		try {
			areaList = new AreaDAO().execRetornarAreasPropriedade(1, idpropriedade);
			
		} catch (SQLException e) {
			e.printStackTrace();
			areaList = new ArrayList<AreaBean>();
			areaList.add(new AreaBean(true, EnumErroArea._6_SQLException.toString(), "-6"));
	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			areaList = new ArrayList<AreaBean>();
			areaList.add(new AreaBean(true, EnumErroArea._7_ClassNotFoundException.toString(), "-7"));
		}
		return areaList;
	}

	
	/**
	 * @param idPropriedade
	 * @param idArea
	 * @return JSON: Area
	 */
	@RequestMapping(value = "/consultarAreaEspecifica/{idPropriedade}/{idArea}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AreaBean consultarAreaEspecifica(@PathVariable("idPropriedade") int idpropriedade, @PathVariable("idArea") int idarea) {
		try {
			
			areaBean = new AreaDAO().execRetornarAreaPropriedadeEspecifica(1, idpropriedade, "", idarea);
			tratamentoRetorno(retorno);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			areaBean = new AreaBean(false, EnumErroArea._6_SQLException.toString(), "-5");

		} catch (SQLException e) {
			e.printStackTrace();
			areaBean = new AreaBean(false, EnumErroArea._6_SQLException.toString(), "-5");
		}
		return areaBean;
	}
	
	
	/**
	 * @param JSON:
	 * @param idpropriedade, idarea
	 * @return codigo de sucesso/erro
	 */
	@RequestMapping(value = "/excluirArea", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String excluirAreaEspecifica(@RequestBody String jsonExcluir) {
		System.out.println(jsonExcluir.toString());
		areaBean = new AreaBean();
		try {
			areaDAO = new AreaDAO();
			JSONObject jsonObject = new JSONObject(jsonExcluir);
			System.out.println(jsonObject.toString());
			areaBean.setIdArea(jsonObject.getInt("idArea"));
			areaBean.setIdPropriedade(jsonObject.getInt("idPropriedade"));
			retorno = areaDAO.execDeletarAreaPropriedadeEspecifica(1, areaBean.getIdPropriedade(), "", areaBean.getIdArea());
			tratamentoRetorno(retorno);

		} catch (JSONException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	
	/**
	 * @param JSON:
	 * @param idpropriedade
	 * @return codigo de sucesso/erro
	 */
	@RequestMapping(value = "/excluirTodasAreas", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String excluirTodasAreas(@RequestBody String jsonExcluir) {
		System.out.println(jsonExcluir.toString());
		areaBean = new AreaBean();
		try {
			areaDAO = new AreaDAO();
			JSONObject jsonObject = new JSONObject(jsonExcluir);
			System.out.println(jsonObject.toString());
			areaBean.setIdPropriedade(jsonObject.getInt("idPropriedade"));
			retorno = areaDAO.execDeletarAreaPropriedadeEspecifica(1, areaBean.getIdPropriedade(), "", 0);
			tratamentoRetorno(retorno);
		} catch (JSONException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	@Override
	public void tratamentoRetorno(String erro) {
		// TODO Auto-generated method stub
		switch (erro) {
		case "-1":
			areaBean.setSuccess(false);
			areaBean.setDetail(EnumErroUsuario._1.toString());
			break;
		case "-2":
			areaBean.setSuccess(false);
			areaBean.setDetail(EnumErroUsuario._2.toString());
			break;
		case "-3":
			areaBean.setSuccess(false);
			areaBean.setDetail(EnumErroUsuario._3.toString());
			break;
		default:
			break;
		}		
	}

	
}
