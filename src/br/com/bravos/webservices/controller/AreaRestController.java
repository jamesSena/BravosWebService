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
import br.com.bravos.webservices.dao.AreaDAO;
import br.com.bravos.webservices.model.AreaBean;
import br.com.bravos.webservices.utils.Json;

/**
 * @author WeltonBatista
 *
 */
@RestController
public class AreaRestController {
	
	private AreaBean area;
	private AreaDAO areaDAO;
	private String retorno;
	List<AreaBean> areaList;
	
	/**
	 * Construtor default
	 */
	public AreaRestController() {
		super();
	}
	
	/**
	 * @param JSON:
	 *            idarea, idpropriedade, nomearea
	 * @return JSON: area
	 * @throws JsonProcessingException
	 */
	
	@RequestMapping(value = "/cadastrarArea", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String cadastrarArea(@RequestBody String jsonCadastro) throws JsonProcessingException {
		area = new AreaBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonCadastro);
			System.out.println(jsonObject.toString());
			area.setIdPropriedade(jsonObject.getInt("idPropriedade"));
			area.setNomeArea(jsonObject.getString("nomeArea"));
			
			// Json ok
			areaDAO = new AreaDAO();
			String codigo = areaDAO.execAreaCadastrar(1, area.getIdPropriedade(), area.getNomeArea());
			area.setReason(codigo);
			if (area.getReason().equals("1")) {
				area.setSuccess(true);
				area.setDetail("sucesso");
			}
			retorno = new Json().convertObjectToJson(area);
			
		} catch (JSONException e) {
			e.printStackTrace();
			area.setSuccess(false);
			area.setReason("-4");
			area.setDetail("Formato JSON invalido ou campo faltando, por favor verificar");
			retorno = new Json().convertObjectToJson(area);
		} catch (SQLException e) {
			e.printStackTrace();
			area.setSuccess(false);
			area.setReason("-5");
			area.setDetail("Inconsistência no SQL: " + e.getMessage());
			retorno = new Json().convertObjectToJson(area);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			area.setSuccess(false);
			area.setReason("-5");
			area.setDetail("Erro ao localizar o Driver de conexão");
			retorno = new Json().convertObjectToJson(area);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			area.setSuccess(false);
			area.setReason("-6");
			area.setDetail("Erro ao realizar Parse de retorno");

		} catch(Exception e){
			
			
		}
		return retorno;

	}
	
	
	/**
	 * @param idPropriedade
	 * @return JSON: List Area
	 */
	@RequestMapping(value = "/consultarAreas/{idPropriedade}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<AreaBean> consultarAreas(@PathVariable("idPropriedade") int idpropriedade) {
		try {
			areaDAO = new AreaDAO();
			areaList = areaDAO.execRetornarAreasPropriedade(1, idpropriedade);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			areaDAO = new AreaDAO();
			area = areaDAO.execRetornarAreaPropriedadeEspecifica(1, idpropriedade, "", idarea);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return area;
	}
	
	
	/**
	 * @param JSON:
	 * @param idpropriedade, idarea
	 * @return codigo de sucesso/erro
	 */
	@RequestMapping(value = "/excluirArea", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String excluirAreaEspecifica(@RequestBody String jsonExcluir) {
		System.out.println(jsonExcluir.toString());
		area = new AreaBean();
		try {
			areaDAO = new AreaDAO();
			JSONObject jsonObject = new JSONObject(jsonExcluir);
			System.out.println(jsonObject.toString());
			area.setIdArea(jsonObject.getInt("idArea"));
			area.setIdPropriedade(jsonObject.getInt("idPropriedade"));
			retorno = areaDAO.execDeletarAreaPropriedadeEspecifica(1, area.getIdPropriedade(), "", area.getIdArea());

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
		area = new AreaBean();
		try {
			areaDAO = new AreaDAO();
			JSONObject jsonObject = new JSONObject(jsonExcluir);
			System.out.println(jsonObject.toString());
			area.setIdPropriedade(jsonObject.getInt("idPropriedade"));
			retorno = areaDAO.execDeletarAreaPropriedadeEspecifica(1, area.getIdPropriedade(), "", 0);

		} catch (JSONException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	
}
