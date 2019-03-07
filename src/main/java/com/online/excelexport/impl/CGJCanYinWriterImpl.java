package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a2urbanmanagementbureau.Catering;

@Component
public class CGJCanYinWriterImpl extends BaseImpl<Catering> {

	@Override
	public String getJpql() {
		return "select loading from Catering loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
