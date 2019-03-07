package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.SingleBoilerInformation;

@Component
public class SingleBoilerInformationWriterImpl extends BaseImpl<SingleBoilerInformation> {

	@Override
	public String getJpql() {
		return "select loading from SingleBoilerInformation loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
