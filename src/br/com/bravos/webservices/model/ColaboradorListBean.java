package br.com.bravos.webservices.model;

import java.util.List;

public class ColaboradorListBean {
	List<UsuarioBean> colaboradorList;

	public ColaboradorListBean() {
	}

	public List<UsuarioBean> getColaboradorList() {
		return colaboradorList;
	}

	public void setColaboradorList(List<UsuarioBean> colaboradorList) {
		this.colaboradorList = colaboradorList;
	}

}
