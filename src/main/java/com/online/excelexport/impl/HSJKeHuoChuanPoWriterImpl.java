package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a10marineboard.TransportShip;

@Component
public class HSJKeHuoChuanPoWriterImpl extends BaseImpl<TransportShip> {

	@Override
	public String getJpql() {
		return "select loading from TransportShip loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
