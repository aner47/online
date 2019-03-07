package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a5businessbureau.OilStorage;

@Component
public class SWJChuYouKuWriterImpl extends BaseImpl<OilStorage> {

	@Override
	public String getJpql() {
		return "select loading from OilStorage loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
