package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.SingleBoilerFuel;

@Component
public class SingleBoilerFuelWriterImpl extends BaseImpl<SingleBoilerFuel> {

	@Override
	public String getJpql() {
		return "select loading from SingleBoilerFuel loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
