package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a3roadbureau.PassengerStation;

@Component
public class JTJKeYunZhanWriterImpl extends BaseImpl<PassengerStation> {

	@Override
	public String getJpql() {
		return "select loading from PassengerStation loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
