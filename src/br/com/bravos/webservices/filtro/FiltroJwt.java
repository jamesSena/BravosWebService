package br.com.bravos.webservices.filtro;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.auth0.jwt.JWTVerifier;

import br.com.bravos.webservices.controller.UsuarioRestController;


/**
 * @author JamessonSena
 *
 */
@WebFilter("/*")
public class FiltroJwt implements Filter{

	@Override
	public void destroy() {
		
	}
	
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		
		//Castign realizado pois é necessário utilizar métodos específicos do HttpServletRequest e Response
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		if(request.getRequestURI().contains("consultarUsuario")){
			filterChain.doFilter(request, response);
			return;
		}
		String token = request.getHeader("Authorization"); // Token se encontra no elemento Authorization do cabeçalho.
		try{
			JWTVerifier verifier = new JWTVerifier(UsuarioRestController.SECRET);
			Map<String, Object> claims = verifier.verify(token);
			System.out.println(claims);
			filterChain.doFilter(servletRequest, servletResponse);
		}catch (Exception e) {
			if(token == null){
				response.sendError(HttpStatus.UNAUTHORIZED.value());
			}else{
				response.sendError(HttpStatus.FORBIDDEN.value());
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
