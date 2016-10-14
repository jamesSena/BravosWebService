/**
 * 
 */
package br.com.bravos.webservices.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author JamessonSena
 *
 */
public class Json {

	public String convertObjectToJson(Object obj) throws JsonProcessingException{
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
