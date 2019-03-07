package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a4fooddrugbureau.FoodDrugCatering;

@Component
public class SYJJCanYinWriterImpl extends BaseImpl<FoodDrugCatering> {

	@Override
	public String getJpql() {
		return "select loading from FoodDrugCatering loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
