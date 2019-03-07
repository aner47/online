package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.GasStove;

@Component
public class GasStoveWriterImpl extends BaseImpl<GasStove> {

	@Override
	public String getJpql() {
		return "select boiler from GasStove boiler where boiler.project.id=:projectId and boiler.emissionsManagement.enterprise.id=:enterpriseId";
	}

}
