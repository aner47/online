package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.GovernanceMeasures;

@Component
public class GovernanceWriterImpl extends BaseImpl<GovernanceMeasures> {

	@Override
	public String getJpql() {
		return "select loading from GovernanceMeasures loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
