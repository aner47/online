package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.constructionsite.ConstructionSiteBase;

@Component
public class ConstructionSiteBaseWriterImpl extends BaseImpl<ConstructionSiteBase> {

	@Override
	public String getJpql() {
		return "select loading from ConstructionSiteBase loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
