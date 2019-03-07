package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.catering.CateringFuel;

@Component
public class CateringFuelWriterImpl extends BaseImpl<CateringFuel> {

	@Override
	public String getJpql() {
		return "select loading from CateringFuel loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
