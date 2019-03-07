package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.breakdownservice.BreakdownServiceBase;

@Component
public class BreakdownServiceBaseWriterImpl extends BaseImpl<BreakdownServiceBase> {

	@Override
	public String getJpql() {
		return "select breakdownServiceBase from BreakdownServiceBase breakdownServiceBase where breakdownServiceBase.project.id=:projectId and breakdownServiceBase.enterprise.id=:enterpriseId";
	}

}
