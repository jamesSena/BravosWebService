package br.com.bravos.webservices.controller;

public interface TratamentoRetorno {
	
	//Traducao de todos os erros retornado pelo banco de dados para a consultas de usuario
	 void tratamentoRetorno(String erro);
}
