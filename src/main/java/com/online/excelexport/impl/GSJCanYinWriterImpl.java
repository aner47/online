package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a11industrialcommercial.InCoCatering;

@Component
public class GSJCanYinWriterImpl extends BaseImpl<InCoCatering> {

	@Override
	public String getJpql() {
		return "select loading from InCoCatering loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
