package br.com.bravos.webservices.controller;

/**
 * @author JamessonSena
 *
 */
public interface _TratamentoRetorno {
	//Traducao de todos os erros retornado pelo banco de dados para a consultas de usuario
	 /**
	 * @param codigo de erro
	 */
	void tratamentoRetorno(String erro);
}
