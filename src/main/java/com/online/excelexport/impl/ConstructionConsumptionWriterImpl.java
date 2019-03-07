package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.constructionsite.ConstructionConsumption;

@Component
public class ConstructionConsumptionWriterImpl extends BaseImpl<ConstructionConsumption> {

	@Override
	public String getJpql() {
		return "select loading from ConstructionConsumption loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
