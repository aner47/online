package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a10marineboard.TransportShipMonth;

@Component
public class HSJKeHuoChuanPoMeiYueWriterImpl extends BaseImpl<TransportShipMonth> {

	@Override
	public String getJpql() {
		return "select loading from TransportShipMonth loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
