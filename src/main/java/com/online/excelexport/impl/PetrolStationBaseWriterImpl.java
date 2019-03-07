package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.petrol.PetrolStationBase;

@Component
public class PetrolStationBaseWriterImpl extends BaseImpl<PetrolStationBase> {

	@Override
	public String getJpql() {
		return "select loading from PetrolStationBase loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
