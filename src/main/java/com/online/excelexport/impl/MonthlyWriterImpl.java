package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.MonthlyInformation;

@Component
public class MonthlyWriterImpl extends BaseImpl<MonthlyInformation> {

	@Override
	public String getJpql() {
		return "select loading from MonthlyInformation loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
