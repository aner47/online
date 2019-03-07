package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a5businessbureau.PetrolGasStation;

@Component
public class SWJJiaYouZhanWriterImpl extends BaseImpl<PetrolGasStation> {

	@Override
	public String getJpql() {
		return "select loading from PetrolGasStation loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
