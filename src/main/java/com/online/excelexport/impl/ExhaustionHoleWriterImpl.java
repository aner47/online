package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.ExhaustionHole;

@Component
public class ExhaustionHoleWriterImpl extends BaseImpl<ExhaustionHole> {

	@Override
	public String getJpql() {
		return "select loading from ExhaustionHole loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
