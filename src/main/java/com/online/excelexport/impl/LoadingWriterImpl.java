package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.LoadingInformation;

@Component
public class LoadingWriterImpl extends BaseImpl<LoadingInformation> {

	@Override
	public String getJpql() {
		return "select loading from LoadingInformation loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
