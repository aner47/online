package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.PowerPlant;

@Component
public class PowerPlantWriterImpl extends BaseImpl<PowerPlant> {

	@Override
	public String getJpql() {
		return "select powerPlant from PowerPlant powerPlant where powerPlant.project.id=:projectId and powerPlant.enterprise.id=:enterpriseId";
	}

}
