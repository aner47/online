package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.constructionsite.ConstructionPeriod;

@Component
public class ConstructionPeriodWriterImpl extends BaseImpl<ConstructionPeriod> {

	@Override
	public String getJpql() {
		return "select loading from ConstructionPeriod loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
