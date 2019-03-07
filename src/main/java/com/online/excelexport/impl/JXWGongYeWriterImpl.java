package com.online.excelexport.impl;

import org.springframework.stereotype.Component;

import com.online.entity.online.a12ceit.CeitEnterprise;

@Component
public class JXWGongYeWriterImpl extends BaseImpl<CeitEnterprise> {

	@Override
	public String getJpql() {
		return "select loading from CeitEnterprise loading where loading.project.id=:projectId and loading.enterprise.id=:enterpriseId";
	}

}
