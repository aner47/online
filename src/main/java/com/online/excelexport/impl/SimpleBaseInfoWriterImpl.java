package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.SimpleBaseInformation;

@Component
public class SimpleBaseInfoWriterImpl extends BaseImpl<SimpleBaseInformation> {

	@Override
	public String getJpql() {
		return "select loading from SimpleBaseInformation loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
