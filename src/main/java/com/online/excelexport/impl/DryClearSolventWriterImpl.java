package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.dryclear.DryClearSolvent;

@Component
public class DryClearSolventWriterImpl extends BaseImpl<DryClearSolvent> {

	@Override
	public String getJpql() {
		return "select loading from DryClearSolvent loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
