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
import br.com.bravos.webservices.dao.AreaDAO;
import br.com.bravos.webservices.enums.EnumErroArea;
import br.com.bravos.webservices.enums.EnumErroUsuario;
import br.com.bravos.webservices.model.AreaBean;
/**
 * @author WeltonBatista
 *
 */
@RestController
public class AreaRestController implements _TratamentoRetorno {
	
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
	 * @method POST
	 * @param JSON -> idUsuario, idPropriedade, nomeArea
	 * @return JSON: areaBean
	 */
	
	@RequestMapping(value = "/cadastrarArea", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AreaBean cadastrarArea(@RequestBody String jsonCadastro){
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
			areaBean.set_BeanAbstract(false, EnumErroArea._6_SQLException.toString(), "-6");
		}
		return areaBean;

	}
	
	
	/**
	 * @method GET
	 * @param idPropriedade
	 * @return JSON: Lista de AreaBean
	 */
	@RequestMapping(value = "/consultarAreas/{idPropriedade}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<AreaBean> consultarAreas(@PathVariable("idPropriedade") int idPropriedade) {
		try {
			areaList = new AreaDAO().execRetornarAreasPropriedade(1, idPropriedade);
			
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
	 * @method GET
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
			areaBean = new AreaBean(false, EnumErroArea._7_ClassNotFoundException.toString(), "-7");
		} catch (SQLException e) {
			e.printStackTrace();
			areaBean = new AreaBean(false, EnumErroArea._6_SQLException.toString(), "-5");
		}
		return areaBean;
	}
	
	
//	/**
//	 * @method DELETE
//	 * @param JSON -> idArea, idPropriedade
//	 * @return JSON: AreaBean
//	 */
//	@RequestMapping(value = "/excluirArea", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public AreaBean excluirAreaEspecifica(@RequestBody String jsonExcluir) {
//		System.out.println(jsonExcluir.toString());
//		areaBean = new AreaBean();
//		try {
//			JSONObject jsonObject = new JSONObject(jsonExcluir);
//			System.out.println(jsonObject.toString());
//			areaBean.setIdArea(jsonObject.getInt("idArea"));
//			areaBean.setIdPropriedade(jsonObject.getInt("idPropriedade"));
//			retorno =  new AreaDAO().execDeletarAreaPropriedadeEspecifica(1, areaBean.getIdPropriedade(), "", areaBean.getIdArea());
//			tratamentoRetorno(retorno);
//		} catch (JSONException e) {
//			e.printStackTrace();
//			areaBean.set_BeanAbstract(false, EnumErroArea._5_JSONException.toString(), "-5");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			areaBean.set_BeanAbstract(false, EnumErroArea._6_SQLException.toString(), "-6");
//		}catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			areaBean.set_BeanAbstract(false, EnumErroArea._7_ClassNotFoundException.toString(), "-7");
//		}
//		return areaBean;
//	}
//	
	
	/**
	 * @method DELETE
	 * @param JSON -> idUsuario, idArea
	 * @return JSON: AreaBean 
	 */
	@RequestMapping(value = "/excluirTodasAreas", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AreaBean excluirTodasAreas(@RequestBody String jsonExcluir) {
		System.out.println(jsonExcluir.toString());
		areaBean = new AreaBean();
		try {
			areaDAO = new AreaDAO();
			JSONObject jsonObject = new JSONObject(jsonExcluir);
			int idUsuario = jsonObject.getInt("idUsuario");
			int idArea = jsonObject.getInt("idArea");
			System.out.println(jsonObject.toString());
			retorno = areaDAO.execDeletarAreaPropriedadeEspecifica(idUsuario, idArea);
			tratamentoRetorno(retorno);
		} catch (JSONException e) {
			e.printStackTrace();
			areaBean.set_BeanAbstract(false, EnumErroArea._5_JSONException.toString(), "-5");
		} catch (SQLException e) {
			e.printStackTrace();
			areaBean.set_BeanAbstract(false, EnumErroArea._6_SQLException.toString(), "-6");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			areaBean.set_BeanAbstract(false, EnumErroArea._7_ClassNotFoundException.toString(), "-7");
		}
		return areaBean;
	}

	@Override
	public void tratamentoRetorno(String erro) {
		switch (erro) {
		case "-1":
			areaBean.set_BeanAbstract(false, EnumErroUsuario._1.toString(), "-1");
			break;
		case "-2":			
			areaBean.set_BeanAbstract(false, EnumErroUsuario._2.toString(), "-2");
			break;
		case "-3":
			areaBean.set_BeanAbstract(false, EnumErroUsuario._3.toString(), "-3");
			break;
		default:
			break;
		}		
	}

	
}
