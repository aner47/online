package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.petrol.PetrolStorageType;

@Component
public class PetrolStorageTypeWriterImpl extends BaseImpl<PetrolStorageType> {

	@Override
	public String getJpql() {
		return "select loading from PetrolStorageType loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
