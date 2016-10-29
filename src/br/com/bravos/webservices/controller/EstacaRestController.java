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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.bravos.webservices.dao.EstacaDAO;
<<<<<<< HEAD
import br.com.bravos.webservices.enums.EnumErroEstaca;
import br.com.bravos.webservices.enums.EnumErroUsuario;
=======
import br.com.bravos.webservices.enums.EnumErroArea;
import br.com.bravos.webservices.enums.EnumErroEstaca;
>>>>>>> 32994819c99a6dc40ccc58877545f7d2cd73eb9f
import br.com.bravos.webservices.model.EstacaBean;
import br.com.bravos.webservices.model.UsuarioBean;



/**
 * @author JamessonSena
 *
 */
@RestController
<<<<<<< HEAD
public class EstacaRestController implements TratamentoRetorno {
	private EstacaBean estacaBean;
	List<EstacaBean> estacaList;
=======
public class EstacaRestController implements _TratamentoRetorno {
	private EstacaBean estacaBean;
	private List<EstacaBean> estacaList;
	private String retorno;

	
	public EstacaRestController()  {
	}
	
>>>>>>> 32994819c99a6dc40ccc58877545f7d2cd73eb9f
	/**
	 * @method POST
	 * @param JSON
	 * @return EstacaBean
	 */
	@RequestMapping(value = "/cadastrarEstaca", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public EstacaBean cadastrarEstaca(@RequestBody String jsonCadastro){
		estacaBean = new EstacaBean();
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
<<<<<<< HEAD
			estacaBean.setReason(new EstacaDAO().execEstacaCadastrar(idUsuario, estacaBean.getIdEstaca(), estacaBean.getIdArea(), estacaBean.getNome(),  estacaBean.getLatitude(), estacaBean.getLongitude()));
			tratamentoRetorno(estacaBean.getReason());
=======
			retorno = new EstacaDAO().execEstacaCadastrar(idUsuario, estacaBean.getIdEstaca(), estacaBean.getIdArea(), estacaBean.getNome(),  estacaBean.getLatitude(), estacaBean.getLongitude());
			estacaBean.setReason(retorno);
			if (estacaBean.getReason().equals("1")) {
				estacaBean.setSuccess(true);
				estacaBean.setDetail("sucesso");
			}
>>>>>>> 32994819c99a6dc40ccc58877545f7d2cd73eb9f
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
	@RequestMapping(value = "/consultarEstacas/{idArea}/{idUsuario}", 
			        method = RequestMethod.GET,
			        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<EstacaBean> consultarEstacas(@PathVariable("idArea")int idArea, @PathVariable("idUsuario")int idUsuario) {
		try {
			estacaList = new EstacaDAO().execEstacaRetornarTodos(idArea, idUsuario);
		} catch (SQLException e) {
			e.printStackTrace();
<<<<<<< HEAD
			estacaList = new ArrayList<EstacaBean>(); 
			estacaList.add(new EstacaBean(false, EnumErroUsuario._6_SQLException.toString(), "-6"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			estacaList = new ArrayList<EstacaBean>(); 
			estacaList.add(new EstacaBean(false, EnumErroUsuario._7_ClassNotFoundException.toString(), "-7"));
=======
			estacaList = new ArrayList<EstacaBean>();
			estacaList.add(new EstacaBean(true, EnumErroArea._6_SQLException.toString(), "-6"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			estacaList = new ArrayList<EstacaBean>();
			estacaList.add(new EstacaBean(true, EnumErroArea._7_ClassNotFoundException.toString(), "-7"));
>>>>>>> 32994819c99a6dc40ccc58877545f7d2cd73eb9f
		}
		return estacaList;
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
<<<<<<< HEAD
			estacaBean.setReason("-6");	
			estacaBean.setDetail(EnumErroEstaca._6_SQLException.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			estacaBean.setReason("-7");	
			estacaBean.setDetail(EnumErroEstaca._7_ClassNotFoundException.toString());
=======
			estacaBean.set_BeanAbstract(false, EnumErroEstaca._6_SQLException.toString().toString(), "-6");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			estacaBean.set_BeanAbstract(false, EnumErroEstaca._7_ClassNotFoundException.toString(), "-7");
>>>>>>> 32994819c99a6dc40ccc58877545f7d2cd73eb9f
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
<<<<<<< HEAD
			estacaBean.setReason(new EstacaDAO().execEstacaRemover(idEstaca, idArea));
		} catch (SQLException e) {
=======
			retorno = new EstacaDAO().execEstacaRemover(idEstaca, idArea);
		} catch (SQLException e) {		
			estacaBean.set_BeanAbstract(false, EnumErroArea._6_SQLException.toString(), "-6");
>>>>>>> 32994819c99a6dc40ccc58877545f7d2cd73eb9f
			e.printStackTrace();
			estacaBean.setDetail(EnumErroEstaca._6_SQLException.toString());
			estacaBean.setReason("-6");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
<<<<<<< HEAD
			estacaBean.setDetail(EnumErroEstaca._7_ClassNotFoundException.toString());
			estacaBean.setReason("-7");
		} catch (JSONException e) {
			e.printStackTrace();
			estacaBean.setDetail(EnumErroEstaca._5_JSONException.toString());
			estacaBean.setReason("-5");
=======
			estacaBean.set_BeanAbstract(false, EnumErroArea._7_ClassNotFoundException.toString(), "-7");
		} catch (JSONException e) {
			e.printStackTrace();
			estacaBean.set_BeanAbstract(false, EnumErroArea._5_JSONException.toString(), "-5");
>>>>>>> 32994819c99a6dc40ccc58877545f7d2cd73eb9f
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
<<<<<<< HEAD
		try {			
=======
		try {
			
>>>>>>> 32994819c99a6dc40ccc58877545f7d2cd73eb9f
			JSONObject jsonObject = new JSONObject(jsonDeletarEstacas);
			System.out.println(jsonObject.toString());
			int idUsuario = jsonObject.getInt("idUsuario");
			int idArea = jsonObject.getInt("idArea");
			int idEstaca = jsonObject.getInt("idEstaca");
<<<<<<< HEAD
			estacaBean.setReason(new EstacaDAO().execEstacaRemoverTodos(idUsuario, idArea, idEstaca));
			tratamentoRetorno(estacaBean.getReason());
		} catch (SQLException e) {
			e.printStackTrace();
			estacaBean.setReason("-6");
			estacaBean.setDetail(EnumErroEstaca._6_SQLException.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			estacaBean.setReason("-7");
			estacaBean.setDetail(EnumErroEstaca._7_ClassNotFoundException.toString());
		} catch (JSONException e) {
			e.printStackTrace();
			estacaBean.setReason("-5");
			estacaBean.setDetail(EnumErroEstaca._5_JSONException.toString());
=======
			retorno = new EstacaDAO().execEstacaRemoverTodos(idUsuario, idArea, idEstaca);
			tratamentoRetorno(retorno);
		} catch (SQLException e) {
			e.printStackTrace();
			estacaBean.set_BeanAbstract(false, EnumErroArea._6_SQLException.toString(), "-6");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();	
			estacaBean.set_BeanAbstract(false, EnumErroArea._7_ClassNotFoundException.toString(), "-7");
		} catch (JSONException e) {
			e.printStackTrace();
			estacaBean.set_BeanAbstract(false, EnumErroArea._5_JSONException.toString(), "-5");
>>>>>>> 32994819c99a6dc40ccc58877545f7d2cd73eb9f
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
<<<<<<< HEAD
			estacaBean.setReason(new EstacaDAO().execEstacaAtualzar(idUsuario, estacaBean.getIdEstaca(), estacaBean.getIdArea(), estacaBean.getNome(),  estacaBean.getLatitude(), estacaBean.getLongitude()));
		
=======
			String codigo = new EstacaDAO().execEstacaAtualzar(idUsuario, estacaBean.getIdEstaca(), estacaBean.getIdArea(), estacaBean.getNome(),  estacaBean.getLatitude(), estacaBean.getLongitude());
			tratamentoRetorno(codigo);
>>>>>>> 32994819c99a6dc40ccc58877545f7d2cd73eb9f
		} catch (JSONException e) {
			e.printStackTrace();
			estacaBean.set_BeanAbstract(false, EnumErroArea._5_JSONException.toString(), "-5");
		} catch (SQLException e) {
			e.printStackTrace();
			estacaBean.set_BeanAbstract(false, EnumErroArea._6_SQLException.toString(), "-6");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			estacaBean.set_BeanAbstract(false, EnumErroArea._7_ClassNotFoundException.toString(), "-7");
		}
		return estacaBean;

	}
	@Override
	public void tratamentoRetorno(String erro) {
		switch (erro) {
		case "-1":
<<<<<<< HEAD
			estacaBean.setSuccess(false);
			estacaBean.setDetail(EnumErroUsuario._1.toString());
			break;
		case "-2":
			estacaBean.setSuccess(false);
			estacaBean.setDetail(EnumErroUsuario._2.toString());
			break;
		case "-3":
			estacaBean.setSuccess(false);
			estacaBean.setDetail(EnumErroUsuario._3.toString());
			break;
		case "-4":
			estacaBean.setSuccess(false);
			estacaBean.setDetail(EnumErroUsuario._4.toString());
=======
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
>>>>>>> 32994819c99a6dc40ccc58877545f7d2cd73eb9f
			break;
		default:
			break;
		}		
	}
}
