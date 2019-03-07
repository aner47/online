package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.general.TransportCar;

@Component
public class TransportCarWriterImpl extends BaseImpl<TransportCar> {

	@Override
	public String getJpql() {
		return "select loading from TransportCar loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
