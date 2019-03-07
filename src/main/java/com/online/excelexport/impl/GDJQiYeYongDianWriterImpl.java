package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a13powercompany.EnterprisePower;

@Component
public class GDJQiYeYongDianWriterImpl extends BaseImpl<EnterprisePower> {

	@Override
	public String getJpql() {
		return "select loading from EnterprisePower loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
