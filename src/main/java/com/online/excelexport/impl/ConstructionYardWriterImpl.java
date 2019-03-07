package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.constructionsite.ConstructionYard;

@Component
public class ConstructionYardWriterImpl extends BaseImpl<ConstructionYard> {

	@Override
	public String getJpql() {
		return "select loading from ConstructionYard loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
