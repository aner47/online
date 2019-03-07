package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.BaseInformation;

@Component
public class BaseInfoWriterImpl extends BaseImpl<BaseInformation> {

	@Override
	public String getJpql() {
		return "select loading from BaseInformation loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
