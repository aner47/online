package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.dryclear.DryClearBase;

@Component
public class DryClearBaseWriterImpl extends BaseImpl<DryClearBase> {

	@Override
	public String getJpql() {
		return "select loading from DryClearBase loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
