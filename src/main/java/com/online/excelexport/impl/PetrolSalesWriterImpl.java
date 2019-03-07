package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.petrol.PetrolSales;

@Component
public class PetrolSalesWriterImpl extends BaseImpl<PetrolSales> {

	@Override
	public String getJpql() {
		return "select loading from PetrolSales loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
