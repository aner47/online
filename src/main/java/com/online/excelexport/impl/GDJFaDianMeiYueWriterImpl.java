package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a13powercompany.PowerPlantMonth;

@Component
public class GDJFaDianMeiYueWriterImpl extends BaseImpl<PowerPlantMonth> {

	@Override
	public String getJpql() {
		return "select loading from PowerPlantMonth loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
