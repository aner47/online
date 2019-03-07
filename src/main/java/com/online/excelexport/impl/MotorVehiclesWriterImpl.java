package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.motorvehicles.MotorVehicles;

@Component
public class MotorVehiclesWriterImpl extends BaseImpl<MotorVehicles> {

	@Override
	public String getJpql() {
		return "select loading from MotorVehicles loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
