package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a14caa.CivilAviation;

@Component
public class MHJQiLuoWriterImpl extends BaseImpl<CivilAviation> {

	@Override
	public String getJpql() {
		return "select loading from CivilAviation loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
