package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.catering.CateringBase;

@Component
public class CateringBaseWriterImpl extends BaseImpl<CateringBase> {

	@Override
	public String getJpql() {
		return "select loading from CateringBase loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
