/**
 * 
 */
package br.com.bravos.webservices.controller;

import java.sql.SQLException;
import java.util.ArrayList;
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

import br.com.bravos.webservices.dao.AreaDAO;
import br.com.bravos.webservices.dao.EstacaDAO;
import br.com.bravos.webservices.dao.PropriedadeDAO;
import br.com.bravos.webservices.enums.EnumErroEstaca;
import br.com.bravos.webservices.model.AreaBean;
import br.com.bravos.webservices.model.EstacaBean;
import br.com.bravos.webservices.model.EstacaListBean;
import br.com.bravos.webservices.model.PropriedadeBean;
import br.com.bravos.webservices.model.SensorBean;



/**
 * @author JamessonSena
 *
 */
@CrossOrigin
@RestController
public class EstacaRestController implements _TratamentoRetorno {
	private EstacaBean estacaBean;
	private List<EstacaBean> estacaList;
	private String retorno;

	
	public EstacaRestController()  {
	}
	
	/**
	 * @method POST
	 * @param JSON
	 * @return EstacaBean
	 */
	@RequestMapping(value = "/cadastrarEstaca", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public EstacaBean cadastrarEstaca(@RequestBody String jsonCadastro){
		EstacaBean estacaBean = new EstacaBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonCadastro);
			System.out.println(jsonObject.toString());
			int idUsuario = jsonObject.getInt("idUsuario");
			estacaBean.setIdEstaca(jsonObject.getInt("idEstaca"));
			estacaBean.setIdArea(jsonObject.getInt("idArea"));
			estacaBean.setNome(jsonObject.getString("nome"));
			estacaBean.setLatitude(jsonObject.getString("latitude"));
			estacaBean.setLongitude(jsonObject.getString("longitude"));
			// Json ok

			estacaBean.setReason(new EstacaDAO().execEstacaCadastrar(idUsuario, estacaBean.getIdEstaca(), estacaBean.getIdArea(), estacaBean.getNome(),  estacaBean.getLatitude(), estacaBean.getLongitude()));
			tratamentoRetorno(estacaBean.getReason());

		//	retorno = new EstacaDAO().execEstacaCadastrar(idUsuario, estacaBean.getIdEstaca(), estacaBean.getIdArea(), estacaBean.getNome(),  estacaBean.getLatitude(), estacaBean.getLongitude());
		//	estacaBean.setReason(retorno);
		} catch (JSONException e) {
			e.printStackTrace();
			estacaBean.set_BeanAbstract(false, EnumErroEstaca._5_JSONException.toString(), "-5");
		} catch (SQLException e) {
			e.printStackTrace();			
			estacaBean.set_BeanAbstract(false, EnumErroEstaca._6_SQLException.toString(), "-6");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();		
			estacaBean.set_BeanAbstract(false, EnumErroEstaca._7_ClassNotFoundException.toString(), "-7");

		}
		return estacaBean;

	}


	/**
	 * @method GET
	 * @param idArea
	 * @param idUsuario
	 * @return JSON: lista de EstacaBean
	 */
	@RequestMapping(value = "/consultarEstacas/{idArea}/{idPropriedade}", 
			        method = RequestMethod.GET,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public EstacaListBean consultarEstacas(@PathVariable("idArea")int idArea, @PathVariable("idPropriedade")int idPropriedade) {
		EstacaListBean estacalistBean = new EstacaListBean();
		try {
			
			estacaList = new EstacaDAO().execEstacaRetornarTodos(idArea, idPropriedade);
			estacalistBean.setEstacaBean(estacaList);
		} catch (SQLException e) {
			e.printStackTrace();
			estacaList = new ArrayList<EstacaBean>(); 
			estacaList.add(new EstacaBean(false, EnumErroEstaca._6_SQLException.toString(), "-6"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			estacaList = new ArrayList<EstacaBean>(); 
			estacaList.add(new EstacaBean(false, EnumErroEstaca._7_ClassNotFoundException.toString(), "-7"));
		}
		return estacalistBean;
	}	
	
	

	/**
	 * @method GET
	 * @param idEstaca
	 * @param idArea
	 * @return EstacaBean
	 */
	@RequestMapping(value = "/consultarEstaca/{idArea}/{idEstaca}", 
			        method = RequestMethod.GET,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public EstacaBean consultarSensor(@PathVariable("idEstaca")int idEstaca, @PathVariable("idArea")int idArea) {
		estacaBean = new EstacaBean();
		try {	
			estacaBean = new EstacaDAO().execEstacaRetornarEspecifico(idEstaca, idArea);
			tratamentoRetorno(estacaBean.getReason());
		} catch (SQLException e) {
			e.printStackTrace();
			estacaBean.setReason("-6");	
			estacaBean.setDetail(EnumErroEstaca._6_SQLException.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			estacaBean.set_BeanAbstract(false, EnumErroEstaca._7_ClassNotFoundException.toString().toString(), "-7");
		} catch (Exception e) {
			e.printStackTrace();
			}
		return estacaBean;
	}

	/**
	 * @method DELETE
	 * @param JSON -> idEstaca, idArea
	 * @return EstacaBean
	 */
	@RequestMapping(value = "/deletarEstaca", 
			        method = RequestMethod.DELETE,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public EstacaBean deletarSensor(@RequestBody String jsonDeletarEstaca) {
		estacaBean = new EstacaBean();
		try {
			
			JSONObject jsonObject = new JSONObject(jsonDeletarEstaca);
			System.out.println(jsonObject.toString());
			int idEstaca = jsonObject.getInt("idEstaca");
			int idArea = jsonObject.getInt("idArea");
			estacaBean.setReason(new EstacaDAO().execEstacaRemover(idEstaca, idArea));
		} catch (SQLException e) {		
			estacaBean.set_BeanAbstract(false, EnumErroEstaca._6_SQLException.toString(), "-6");
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			estacaBean.set_BeanAbstract(false, EnumErroEstaca._7_ClassNotFoundException.toString(), "-7");
			e1.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
			estacaBean.set_BeanAbstract(false, EnumErroEstaca._5_JSONException.toString(), "-5");
		}
		return estacaBean;
	}

	/**
	 * @method DELETE
	 * @param JSON -> idUsuario, idArea, idEstaca
	 * @return EstacaBean
	 */
	@RequestMapping(value = "/deletarTodasEstacas", 
			        method = RequestMethod.DELETE,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public EstacaBean deletarTodasEstacas(@RequestBody String jsonDeletarEstacas) {
		estacaBean = new EstacaBean();

		try {			
			JSONObject jsonObject = new JSONObject(jsonDeletarEstacas);
			System.out.println(jsonObject.toString());
			int idUsuario = jsonObject.getInt("idUsuario");
			int idArea = jsonObject.getInt("idArea");
			int idEstaca = jsonObject.getInt("idEstaca");
			estacaBean.setReason(new EstacaDAO().execEstacaRemoverTodos(idUsuario, idArea, idEstaca));
			tratamentoRetorno(estacaBean.getReason());
		} catch (SQLException e) {
			e.printStackTrace();
			estacaBean.set_BeanAbstract(false, EnumErroEstaca._6_SQLException.toString(), "-6");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();	
			estacaBean.set_BeanAbstract(false, EnumErroEstaca._7_ClassNotFoundException.toString(), "-7");
		} catch (JSONException e) {
			e.printStackTrace();
			estacaBean.set_BeanAbstract(false, EnumErroEstaca._5_JSONException.toString(), "-5");
		}
		return estacaBean;
	}

	/**
	 * @method PUT
	 * @param JSON -> idUsuario, idSensor, idArea, nome, latitude, longitude
	 * @return EstacaBean
	 */
	@RequestMapping(value = "/atualizarEstaca", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public EstacaBean atualizarEstaca(@RequestBody String jsonAtualizar) {
		estacaBean = new EstacaBean();
		try {
			JSONObject jsonObject = new JSONObject(jsonAtualizar);
			System.out.println(jsonObject.toString());
			int idUsuario = jsonObject.getInt("idUsuario");
			estacaBean.setIdEstaca(jsonObject.getInt("idSensor"));
			estacaBean.setIdArea(jsonObject.getInt("idArea"));
			estacaBean.setNome(jsonObject.getString("nome"));
			estacaBean.setLatitude(jsonObject.getString("latitude"));
			estacaBean.setLongitude(jsonObject.getString("longitude"));
			// Json ok
			estacaBean.setReason(new EstacaDAO().execEstacaAtualzar(idUsuario, estacaBean.getIdEstaca(), estacaBean.getIdArea(), estacaBean.getNome(),  estacaBean.getLatitude(), estacaBean.getLongitude()));
			tratamentoRetorno(estacaBean.getReason());
		} catch (JSONException e) {
			e.printStackTrace();
			estacaBean.set_BeanAbstract(false, EnumErroEstaca._5_JSONException.toString(), "-5");
		} catch (SQLException e) {
			e.printStackTrace();
			estacaBean.set_BeanAbstract(false, EnumErroEstaca._6_SQLException.toString(), "-6");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			estacaBean.set_BeanAbstract(false, EnumErroEstaca._7_ClassNotFoundException.toString(), "-7");
		}
		return estacaBean;

	}
	
	/**
	 * @method GET
	 * @param idArea
	 * @param idUsuario
	 * @return JSON: lista de EstacaBean
	 */
	@RequestMapping(value = "/consultarEstacasDaPropriedade/{idUsuario}/{idPropriedade}", 
			        method = RequestMethod.GET,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public EstacaListBean consultarEstacasDaPropriedade(@PathVariable("idPropriedade")int idPropriedade, @PathVariable("idUsuario")int idUsuario) {
		EstacaListBean estacaListBean = new EstacaListBean();
		try {
			estacaListBean.setEstacaBean(new EstacaDAO().execEstacaRetornarTodosDaPropriedade(idPropriedade, idUsuario));
			
		} catch (SQLException e) {
			e.printStackTrace();
			estacaList.add(new EstacaBean(false, EnumErroEstaca._6_SQLException.toString(), "-6"));
			List<EstacaBean> estacaBeanList =  new ArrayList<EstacaBean>();
			estacaBeanList.add(estacaBean);
			estacaListBean.setEstacaBean(estacaBeanList);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			estacaBean = new EstacaBean(false, EnumErroEstaca._7_ClassNotFoundException.toString(), "-7");
			List<EstacaBean> estacaBeanList =  new ArrayList<EstacaBean>();
			estacaBeanList.add(estacaBean);
			estacaListBean.setEstacaBean(estacaBeanList);
		}
		return estacaListBean;
	}	
	
	
	
	/**
	 * @method GET
	 * @param idArea
	 * @param idUsuario
	 * @return JSON: lista de EstacaBean
	 */
	@RequestMapping(value = "/consultarEstacasDaPropriedade1/{idUsuario}/{idPropriedade}", 
			        method = RequestMethod.GET,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<AreaBean> consultarEstacasDaPropriedade1(@PathVariable("idPropriedade")int idPropriedade, @PathVariable("idUsuario")int idUsuario) {
		List<AreaBean> piquete = new ArrayList<AreaBean>() ;
		
		try {	
			//pegando os piquetes passando a propriedade

			
				 piquete = new AreaDAO().execRetornarAreasPropriedade(idUsuario, idPropriedade);
					for (AreaBean areaBean : piquete) {
						estacaList = new EstacaDAO().execEstacaRetornarTodos(areaBean.getIdArea(), idUsuario);
						areaBean.setEstacaBean(estacaList);
					}
			

			
			
		} catch (SQLException e) {
			e.printStackTrace();
			estacaList.add(new EstacaBean(false, EnumErroEstaca._6_SQLException.toString(), "-6"));
			List<EstacaBean> estacaBeanList =  new ArrayList<EstacaBean>();
			estacaBeanList.add(estacaBean);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			estacaBean = new EstacaBean(false, EnumErroEstaca._7_ClassNotFoundException.toString(), "-7");
			List<EstacaBean> estacaBeanList =  new ArrayList<EstacaBean>();
			estacaBeanList.add(estacaBean);
		
		}
		return piquete;
	}	
	
	
	
	
	public void tratamentoRetorno(String erro) {
		switch (erro) {
		case "-1":
			estacaBean.set_BeanAbstract(false, EnumErroEstaca._1.toString(), "-1");
			break;
		case "-2":
			estacaBean.set_BeanAbstract(false, EnumErroEstaca._2.toString(), "-2");
			break;
		case "-3":
			estacaBean.set_BeanAbstract(false, EnumErroEstaca._3.toString(), "-3");
			break;
		case "-4":
			estacaBean.set_BeanAbstract(false, EnumErroEstaca._4.toString(), "-4");
			break;
		default:
			break;
		}		
	}
}
