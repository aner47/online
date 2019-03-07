package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.breakdownservice.BreakdownServiceSolvent;

@Component
public class BreakdownServiceSolventWriterImpl extends BaseImpl<BreakdownServiceSolvent> {

	@Override
	public String getJpql() {
		return "select breakdownServiceSolvent from BreakdownServiceSolvent breakdownServiceSolvent where breakdownServiceSolvent.project.id=:projectId and breakdownServiceSolvent.enterprise.id=:enterpriseId";
	}

}
