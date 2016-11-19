package br.com.bravos.webservices.filtro;

import java.util.HashMap;

import com.auth0.jwt.JWTSigner;

public class Token {
	private String token;
	public static final String SECRET = "crifradoprograma"; // cifra para descriptografar.
	public static final String ISSUER = "Servidor Bravo"; // emissor do token.
	
	
	public Token() {
	}
	public String getToken(){
		return token;
	}
	public void setToken(String token){
		this.token = token;
	}
	public String gerarToken(){
		return gerarToken(0);
	}
	public String gerarToken(int id){
		// Data de emiss�o em segundos do Token
		long iat = System.currentTimeMillis() / 1000;
		// Data de Expira��o do token, � o tempo atual mais 1 minuto
		long exp = iat + 6000;
		
		//Criptografa passando a senha
		JWTSigner signer = new JWTSigner(SECRET);	
		
		//HashMap passando as informa��es necessaria para a cria��o do toker
		HashMap<String, Object> claims = new HashMap<>();
		claims.put("iat", iat);
		claims.put("exp", exp);
		claims.put("iss", ISSUER);
		claims.put("id_Usuario", id);

		// gerar Token8
		String jwt = signer.sign(claims); // M�todo crpitografa toda a HashMap na forma de um Token
		return jwt;
	}
}
