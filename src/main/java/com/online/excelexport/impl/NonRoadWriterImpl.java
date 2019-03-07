package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.general.NonRoad;

@Component
public class NonRoadWriterImpl extends BaseImpl<NonRoad> {

	@Override
	public String getJpql() {
		return "select loading from NonRoad loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
