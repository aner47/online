package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a1constructioncommittee.BetonStirStation;

@Component
public class ZJWJiaoBanZhanWriterImpl extends BaseImpl<BetonStirStation> {

	@Override
	public String getJpql() {
		return "select loading from BetonStirStation loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
