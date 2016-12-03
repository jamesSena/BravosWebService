package br.com.bravos.webservices.model;

import java.util.List;

public class SensorListBean {
	List<SensorBean> sensorList;

	public SensorListBean() {
	}

	public List<SensorBean> getSensorList() {
		return sensorList;
	}

	public void setSensorList(List<SensorBean> sensorList) {
		this.sensorList = sensorList;
	}

	
}
